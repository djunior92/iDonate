package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Address;
import br.com.idonate.iDonate.model.Perfil;
import br.com.idonate.iDonate.service.exception.InvalidValueException;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Address save(Address address);
    Address edit(Long id, Address address);
    void delete(Long id);
    Optional<Address> searchById(Long id);
    //List<Address> searchByPerfil(Perfil perfil);  //CRIAR FUNÇÃO PARA TRAZER OS ENDEREÇOS DO PERFIL LOGADO
}