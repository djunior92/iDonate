package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Perfil;
import br.com.idonate.iDonate.service.exception.PerfilNotRegisteredException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public interface PerfilService {

    Perfil save(String login, Perfil perfil) throws PerfilNotRegisteredException;
    Perfil edit(Long id, Perfil perfil) throws EmptyResultDataAccessException;
    Optional<Perfil> searchById(Long id);
    List<Perfil> searchByName(String name);

}
