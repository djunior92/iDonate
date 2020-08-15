package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.Redeem;
import br.com.idonate.iDonate.service.RedeemService;
import br.com.idonate.iDonate.service.exception.RedeemNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/redeem")
public class RedeemResource {

    @Autowired
    RedeemService redeemService;

    @PostMapping
    public ResponseEntity<Redeem> save(@Valid @RequestBody Redeem redeem) throws RedeemNotRegisteredException {
        Redeem savedRedeem = redeemService.save(redeem);
        return new ResponseEntity<>(savedRedeem, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Redeem> searchById(@PathVariable Long id) {
        Optional<Redeem> redeem = redeemService.searchById(id);
        return (redeem.isPresent() ? ResponseEntity.ok(redeem.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Redeem>> searchByProfile(@RequestBody Profile profile) {
        return new ResponseEntity<>(redeemService.searchByProfile(profile), HttpStatus.OK);
    }

}
