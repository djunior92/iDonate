package br.com.idonate.iDonate.resource;


import br.com.idonate.iDonate.model.BankAccount;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/bankaccount")
public class BankAccountResource {

    @Autowired
    BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<BankAccount> save(@Valid @RequestBody BankAccount bankAccount){
        BankAccount savedBankAccount = bankAccountService.save(bankAccount);
        return new ResponseEntity<>(savedBankAccount, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> edit(@PathVariable Long id, @Valid @RequestBody BankAccount bankAccount) {
        BankAccount updateBankAccount = bankAccountService.edit(id, bankAccount);
        return new ResponseEntity<>(updateBankAccount, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> searchById(@PathVariable Long id) {
        Optional<BankAccount> bankAccount = bankAccountService.searchById(id);
        return (bankAccount.isPresent() ? ResponseEntity.ok(bankAccount.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> searchByProfile(@RequestBody Profile profile) {
        return new ResponseEntity<>(bankAccountService.searchByProfile(profile), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BankAccount> delete(@PathVariable Long id) {
        bankAccountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
