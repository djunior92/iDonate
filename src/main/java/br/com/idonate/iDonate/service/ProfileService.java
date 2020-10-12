package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.view.ProfileView;
import br.com.idonate.iDonate.service.exception.ProfileNotRegisteredException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    Profile save(String login, Profile profile) throws ProfileNotRegisteredException;
    Profile edit(Long id, Profile profile) throws EmptyResultDataAccessException;
    ProfileView searchById(Long id);
    Optional<Profile> searchLogin(String login);
    List<Profile> searchByName(String name);
    void recharge(Long id, Integer points);
    void redeem(Long id, Integer points);
    void donation(Donation donation);

}
