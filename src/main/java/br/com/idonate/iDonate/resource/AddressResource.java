package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Address;
import br.com.idonate.iDonate.model.Perfil;
import br.com.idonate.iDonate.service.AddressService;
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
    public ResponseEntity<Address> edit(@PathVariable Long id, @Valid @RequestBody Address address) {
        Address updateAddress = addressService.edit(id, address);
        return new ResponseEntity<>(updateAddress, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> searchById(@PathVariable Long id) {
        Optional<Address> address = addressService.searchById(id);
        return (address.isPresent() ? ResponseEntity.ok(address.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Address>> searchByPerfil(@RequestBody Perfil perfil) {
        return new ResponseEntity<>(addressService.searchByPerfil(perfil), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable Long id) {
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
