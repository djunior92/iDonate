package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.BankAccount;
import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.service.CreditCardService;
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
    public ResponseEntity<CreditCard> save(@Valid @RequestBody CreditCard creditCard) throws Exception {
        CreditCard savedCreditCard = creditCardService.save(creditCard);
        return new ResponseEntity<>(savedCreditCard, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> inactive(@PathVariable Long id) {
        CreditCard updateCreditCard = creditCardService.inactive(id);
        return new ResponseEntity<>(updateCreditCard, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> searchById(@PathVariable Long id) {
        Optional<CreditCard> creditCard = creditCardService.searchById(id);
        return (creditCard.isPresent() ? ResponseEntity.ok(creditCard.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping("/profile/{id}")
    //public ResponseEntity<List<CreditCard>> searchByProfile(@RequestBody Profile profile) {
    public ResponseEntity<List<CreditCard>> searchByProfile(@PathVariable Long id) {
        return new ResponseEntity<>(creditCardService.searchByProfile(id), HttpStatus.OK);
    }

}
