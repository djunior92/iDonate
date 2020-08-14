package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.ApplicationContextLoad;
import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Enum.PeopleType;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.User;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.repository.UserRepository;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.UserService;
import br.com.idonate.iDonate.service.exception.ProfileNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;

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

    @Override
    public Optional<Profile> searchById(Long id) {
        return profileRepository.findById(id);
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
    public void donate(Long id, Integer points) {
        Profile profileEditing = profileExist(id);

        profileEditing.setMyPoints(profileEditing.getMyPoints() - points);
        profileRepository.save(profileEditing);
    }

    @Override
    public void receive(Long id, Integer points) {
        Profile profileEditing = profileExist(id);

        profileEditing.setPointsReceived(profileEditing.getPointsReceived() + points);
        profileRepository.save(profileEditing);
    }

    private Profile profileExist(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);

        if (!optionalProfile.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return optionalProfile.get();
    }
}
