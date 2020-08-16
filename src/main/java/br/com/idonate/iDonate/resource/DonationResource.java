package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.service.DonationService;
import br.com.idonate.iDonate.service.exception.CampaignAndBenefitedNotInformedException;
import br.com.idonate.iDonate.service.exception.DonationNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/donation")
public class DonationResource {

    @Autowired
    DonationService donationService;

    @PostMapping
    public ResponseEntity<Donation> save(@Valid @RequestBody Donation donation)
            throws DonationNotRegisteredException, CampaignAndBenefitedNotInformedException {
        Donation savedDonation = donationService.save(donation);
        return new ResponseEntity<>(savedDonation, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> searchById(@PathVariable Long id) {
        Optional<Donation> donation = donationService.searchById(id);
        return (donation.isPresent() ? ResponseEntity.ok(donation.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping("/donor")
    public ResponseEntity<List<Donation>> searchByDonor(@RequestBody Profile profile) {
        return new ResponseEntity<>(donationService.searchByDonor(profile), HttpStatus.OK);
    }

    @GetMapping("/benefited")
    public ResponseEntity<List<Donation>> searchByBenefited(@RequestBody Profile profile) {
        return new ResponseEntity<>(donationService.searchByBenefited(profile), HttpStatus.OK);
    }

    @GetMapping("/campaign")
    public ResponseEntity<List<Donation>> searchByCampaign(@RequestBody Campaign campaign) {
        return new ResponseEntity<>(donationService.searchByCampaign(campaign), HttpStatus.OK);
    }

}
