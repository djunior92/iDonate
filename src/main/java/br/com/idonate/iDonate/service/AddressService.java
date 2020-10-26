package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Address;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Address save(Address address);
    Address edit(Long id, Address address) throws RegisterNotFoundException;
    void delete(Long id);
    Optional<Address> searchById(Long id);
    List<Address> searchByProfile(Long id) throws RegisterNotFoundException;

}