package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.core.IDonateUtils;
import br.com.idonate.iDonate.ApplicationContextLoad;
import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.model.Enum.PeopleType;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.model.view.ProfileView;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.repository.UserRepository;
import br.com.idonate.iDonate.service.CampaignService;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.UserService;
import br.com.idonate.iDonate.service.exception.ProfileNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    CampaignService campaignService;

    @Autowired
    UserService userService;

    @Override
    @Transactional(rollbackOn = ProfileNotRegisteredException.class)
    public Profile save(String login, Profile profile) throws ProfileNotRegisteredException, RegisterNotFoundException {
        try {
            Optional<User> userOpt = ApplicationContextLoad.getApplicationContext()
                    .getBean(UserRepository.class).findByLogin(login);

            if (userOpt.isEmpty()) {
                throw new RegisterNotFoundException("Usuário " + login + " não encontrado.");
            }

            profile.setId(userOpt.get().getId());
            profile.setRegistrationDate(LocalDateTime.now());
            profile.setDateBirth(LocalDateTime.now());
            profile.setMyPoints(0);
            profile.setPointsReceived(0);
            profile.setPeopleType(PeopleType.F);
            Profile profile1Saved = profileRepository.save(profile);
            userService.linkProfile(userOpt.get(), profile1Saved);
            return profile1Saved;
        } catch (Exception e) {
            throw new ProfileNotRegisteredException("Erro ao gravar profile do usuário.");
        }
    }

    @Override
    public Profile edit(Long id, Profile profile) throws RegisterNotFoundException {
        Profile profileEdit = profileExist(id);
        IDonateUtils.copyNonNullProperties(profile, profileEdit, "id", "myPoints", "pointsReceived");

        return profileRepository.save(profileEdit);
    }

    @Override
    public ProfileView searchById(Long id) throws RegisterNotFoundException {
        User user = ApplicationContextLoad.getApplicationContext().getBean(UserRepository.class).findById(id).orElseThrow(() -> new RegisterNotFoundException("Usuário " + id + " não encontrado."));
        Profile profile = profileRepository.findById(user.getId()).orElseThrow(() -> new RegisterNotFoundException("Perfil " + id + " não encontrado."));

        ProfileView profileView = new ProfileView();
        profileView.id = profile.getId();
        profileView.login = user.getLogin();
        profileView.phone = profile.getPhone();
        profileView.name = profile.getName();
        profileView.image = profile.getImage();
        profileView.registrationDate = profile.getRegistrationDate();
        profileView.facebook = profile.getFacebook();
        profileView.instagram = profile.getInstagram();
        profileView.youtube = profile.getYoutube();
        profileView.website = profile.getWebsite();
        profileView.peopleType = profile.getPeopleType();
        profileView.document = profile.getDocument();
        profileView.dateBirth = profile.getDateBirth();
        profileView.description = profile.getDescription();

        return profileView;
    }

    @Override
    public Profile searchProfileById(Long id) throws RegisterNotFoundException {
        return profileRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Perfil " + id + " não encontrado."));
    }

    @Override
    public Optional<Profile> searchLogin(String login) throws RegisterNotFoundException {
        return profileRepository.findById(ApplicationContextLoad.getApplicationContext()
                .getBean(UserRepository.class).findByLogin(login).orElseThrow(() -> new RegisterNotFoundException("Perfil " + login + " não encontrado.")).getId());
    }

    @Override
    public List<Profile> searchByName(String name) {
        return profileRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void recharge(Long id, Integer points) throws RegisterNotFoundException {
        Profile profileEditing = profileExist(id);

        profileEditing.setMyPoints(profileEditing.getMyPoints() + points);
        profileRepository.save(profileEditing);
    }

    @Override
    public void redeem(Long id, Integer points) throws RegisterNotFoundException {
        Profile profileEditing = profileExist(id);

        profileEditing.setPointsReceived(profileEditing.getPointsReceived() - points);
        profileRepository.save(profileEditing);
    }

    @Override
    public void donation(Donation donation) throws RegisterNotFoundException {
        boolean verifyBenefitedProfile = true;
        Profile donorProfile = profileExist(donation.getDonor().getId());
        Profile benefitedProfile;
        Campaign benefitedCampaign;

        donate(donorProfile, donation.getPointsDonationed());

        if (!Objects.isNull(donation.getCampaign())) {
            benefitedCampaign = campaignExist(donation.getCampaign().getId());
            campaignService.receiveCampaign(benefitedCampaign, donation.getPointsDonationed());

            if (!Objects.isNull(benefitedCampaign.getProfile())) {
                receiveProfile(benefitedCampaign.getProfile(), donation.getPointsDonationed());
                verifyBenefitedProfile = false;
            }
        }

        if (verifyBenefitedProfile && !Objects.isNull(donation.getBenefited())) {
            benefitedProfile = profileExist(donation.getBenefited().getId());
            receiveProfile(benefitedProfile, donation.getPointsDonationed());
        }
    }

    private void donate(Profile donor, Integer points) {
        donor.setMyPoints(donor.getMyPoints() - points);
        profileRepository.save(donor);
    }

    private void receiveProfile(Profile benefited, Integer points) {
        benefited.setPointsReceived(benefited.getPointsReceived() + points);
        profileRepository.save(benefited);
    }

    private Profile profileExist(Long id) throws RegisterNotFoundException {
        return profileRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Perfil " + id + " não encontrado."));
    }

    private Campaign campaignExist(Long id) throws RegisterNotFoundException {
        return campaignService.searchById(id).orElseThrow(() -> new RegisterNotFoundException("Campanha " + id + " não encontrado."));
    }

}
