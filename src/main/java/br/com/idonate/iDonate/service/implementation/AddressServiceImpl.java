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

        //address.setDateStart(LocalDateTime.now());
        return addressRepository.save(address);
    }


    @Override
    public Address edit(Long id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        if (!optionalAddress.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        Address addressSaved = optionalAddress.get();
        addressSaved.setStreetAddress(address.getStreetAddress());
        addressSaved.setNumberAddress(address.getNumberAddress());
        addressSaved.setComplementAddress(address.getComplementAddress());
        addressSaved.setCep(address.getCep());
        addressSaved.setNeighborhood(address.getNeighborhood());
        addressSaved.setCity(address.getCity());
        addressSaved.setUf(address.getUf());

        return addressRepository.save(addressSaved);
    }

    @Override
    public void delete(Long id){
        addressRepository.delete(addressRepository.findById(id).get());
    }

    @Override
    public Optional<Address> searchById(Long id) {
        return addressRepository.findById(id);
    }

    //CRIAR FUNÇÃO PARA TRAZER OS ENDEREÇOS DO PERFIL LOGADO
}
