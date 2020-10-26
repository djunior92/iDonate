package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Enum.CreditCardStatus;
import br.com.idonate.iDonate.service.CreditCardService;
import br.com.idonate.iDonate.service.exception.CreditCardNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/creditcard")
public class CreditCardResource {

    @Autowired
    CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<CreditCard> save(@Valid @RequestBody CreditCard creditCard) throws CreditCardNotRegisteredException {
        CreditCard savedCreditCard = creditCardService.save(creditCard);
        return new ResponseEntity<>(savedCreditCard, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> inactive(@PathVariable Long id) throws RegisterNotFoundException {
        CreditCard updateCreditCard = creditCardService.inactive(id);
        return new ResponseEntity<>(updateCreditCard, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> searchById(@PathVariable Long id) {
        Optional<CreditCard> creditCard = creditCardService.searchById(id);
        return (creditCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    //Alterado Gustavo
    @GetMapping(value = {"/profile/{id}", "/profile/{id}/status/{status}"})
    public ResponseEntity<List<CreditCard>> searchByProfile(@PathVariable Long id, @PathVariable(required = false) CreditCardStatus status) throws RegisterNotFoundException {
        return new ResponseEntity<>(creditCardService.searchByProfile(id, status), HttpStatus.OK);
    }

}
