package br.com.idonate.iDonate.service;

        import br.com.idonate.iDonate.model.Address;
        import br.com.idonate.iDonate.model.Profile;

        import java.util.List;
        import java.util.Optional;

public interface AddressService {

    Address save(Address address);
    Address edit(Long id, Address address);
    void delete(Long id);
    Optional<Address> searchById(Long id);
    List<Address> searchByProfile(Profile profile);
}