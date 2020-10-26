package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.view.ProfileView;
import br.com.idonate.iDonate.service.exception.ProfileNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    Profile save(String login, Profile profile) throws ProfileNotRegisteredException, RegisterNotFoundException;
    Profile edit(Long id, Profile profile) throws RegisterNotFoundException;
    ProfileView searchById(Long id) throws RegisterNotFoundException;
    Optional<Profile> searchLogin(String login) throws RegisterNotFoundException;
    List<Profile> searchByName(String name);
    void recharge(Long id, Integer points) throws RegisterNotFoundException;
    void redeem(Long id, Integer points) throws RegisterNotFoundException;
    void donation(Donation donation) throws RegisterNotFoundException;

}
