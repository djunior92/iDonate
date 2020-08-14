package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.Recharge;
import br.com.idonate.iDonate.service.RechargeService;
import br.com.idonate.iDonate.service.exception.RechargeNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/recharge")
public class RechargeResource {

    @Autowired
    RechargeService rechargeService;

    @PostMapping
    public ResponseEntity<Recharge> save(@Valid @RequestBody Recharge recharge) throws RechargeNotRegisteredException {
        Recharge savedRecharge = rechargeService.save(recharge);
        return new ResponseEntity<>(savedRecharge, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recharge> searchById(@PathVariable Long id) {
        Optional<Recharge> recharge = rechargeService.searchById(id);
        return (recharge.isPresent() ? ResponseEntity.ok(recharge.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Recharge>> searchByProfile(@RequestBody Profile profile) {
        return new ResponseEntity<>(rechargeService.searchByProfile(profile), HttpStatus.OK);
    }

}
