package br.com.idonate.iDonate.service.implementation;


import br.com.idonate.iDonate.model.Address;
import br.com.idonate.iDonate.model.Perfil;
import br.com.idonate.iDonate.repository.AddressRepository;
import br.com.idonate.iDonate.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;


    @Override
    public Address save(Address address){
        return addressRepository.save(address);
    }


    @Override
    public Address edit(Long id, Address address) {
        if (!adrressExist(id)) {
            throw new EmptyResultDataAccessException(1);
        }
        address.setId(id);

        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id){
        addressRepository.delete(addressRepository.findById(id).get());
    }

    @Override
    public Optional<Address> searchById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> searchByPerfil(Perfil perfil) {
        return addressRepository.findByPerfil(perfil);
    }

    private Boolean adrressExist(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            return false;
        }
        return true;
    }
}
