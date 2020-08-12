package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.service.exception.ProfileNotRegisteredException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

    Profile save(String login, Profile profile) throws ProfileNotRegisteredException;
    Profile edit(Long id, Profile profile) throws EmptyResultDataAccessException;
    Optional<Profile> searchById(Long id);
    List<Profile> searchByName(String name);

}
