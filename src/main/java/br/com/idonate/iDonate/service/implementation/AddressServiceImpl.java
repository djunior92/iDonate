package br.com.idonate.iDonate.service.implementation;


import br.com.idonate.iDonate.core.IDonateUtils;
import br.com.idonate.iDonate.model.Address;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.repository.AddressRepository;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.service.AddressService;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Address save(Address address){
        return addressRepository.save(address);
    }


    @Override
    public Address edit(Long id, Address address) throws RegisterNotFoundException {
        Address addressEdit = addressRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Endereço " + id + " não encontrado."));
        IDonateUtils.copyNonNullProperties(address, addressEdit, "id", "profile");

        return addressRepository.save(addressEdit);
    }

    @Override
    public void delete(Long id){
        addressRepository.findById(id).ifPresentOrElse((address) -> addressRepository.delete(address),
                () -> { throw new EmptyResultDataAccessException(1); });
    }

    @Override
    public Optional<Address> searchById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> searchByProfile(Long id) throws RegisterNotFoundException {
        return addressRepository.findByProfile(profileRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Perfil " + id + " não encontrado.")));
    }

}
