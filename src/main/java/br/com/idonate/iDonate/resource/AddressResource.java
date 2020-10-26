package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Address;
import br.com.idonate.iDonate.service.AddressService;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/address")
public class AddressResource {

    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> save(@Valid @RequestBody Address address){
        Address savedAddress = addressService.save(address);
        return new ResponseEntity<>(savedAddress, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> edit(@PathVariable Long id, @Valid @RequestBody Address address) throws RegisterNotFoundException {
        Address updateAddress = addressService.edit(id, address);
        return new ResponseEntity<>(updateAddress, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> searchById(@PathVariable Long id) {
        Optional<Address> address = addressService.searchById(id);
        return (address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    //Alterado Djalma
    @GetMapping("/profile/{id}")
    public ResponseEntity<List<Address>> searchByProfile(@PathVariable Long id) throws RegisterNotFoundException {
        return new ResponseEntity<>(addressService.searchByProfile(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable Long id) {
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
