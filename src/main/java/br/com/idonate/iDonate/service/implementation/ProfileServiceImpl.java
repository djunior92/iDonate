package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.ApplicationContextLoad;
import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.model.Enum.PeopleType;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.repository.UserRepository;
import br.com.idonate.iDonate.service.CampaignService;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.UserService;
import br.com.idonate.iDonate.service.exception.ProfileNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Profile save(String login, Profile profile) throws ProfileNotRegisteredException {
        try {
            Optional<User> userOpt = ApplicationContextLoad.getApplicationContext()
                    .getBean(UserRepository.class).findByLogin(login);

            if (!userOpt.isPresent()) {
                throw new Exception();
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
    public Profile edit(Long id, Profile profile) throws EmptyResultDataAccessException {
        profileExist(id);
        profile.setId(id);

        return profileRepository.save(profile);
    }

    /*@Override
    public Optional<Profile> searchById(Long id) {
        return profileRepository.findById(id);
    }*/

    @Override
    public Optional<Profile> searchLogin(String login) {
        Optional<User> userOpt = ApplicationContextLoad.getApplicationContext()
                .getBean(UserRepository.class).findByLogin(login);

        if (!userOpt.isPresent()) {
            return null;
        }

        return profileRepository.findById(userOpt.get().getId());
    }

    @Override
    public List<Profile> searchByName(String name) {

        return profileRepository.findByNameContaining(name);
    }

    @Override
    public void recharge(Long id, Integer points) {
        Profile profileEditing = profileExist(id);

        profileEditing.setMyPoints(profileEditing.getMyPoints() + points);
        profileRepository.save(profileEditing);
    }

    @Override
    public void redeem(Long id, Integer points) {
        Profile profileEditing = profileExist(id);

        profileEditing.setPointsReceived(profileEditing.getPointsReceived() - points);
        profileRepository.save(profileEditing);
    }

    @Override
    public void donation(Donation donation) {
        Boolean verifyBenefitedProfile = true;
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

    private Profile profileExist(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);

        if (!optionalProfile.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return optionalProfile.get();
    }

    private Campaign campaignExist(Long id) {
        Optional<Campaign> optionalCampaign = campaignService.searchById(id);

        if (!optionalCampaign.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return optionalCampaign.get();
    }
}
