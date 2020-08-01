package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Perfil;

import java.util.List;
import java.util.Optional;

public interface PerfilService {

    Perfil save(Perfil perfil);
    Perfil edit(Long id, Perfil perfil);
    Optional<Perfil> searchById(Long id);
    List<Perfil> searchByName(String name);

}
