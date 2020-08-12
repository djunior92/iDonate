package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Address;
import br.com.idonate.iDonate.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findById(Long id);
    List<Address> findByProfile(Profile profile);

}



