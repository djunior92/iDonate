package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.service.DonationService;
import br.com.idonate.iDonate.service.exception.CampaignAndBenefitedNotInformedException;
import br.com.idonate.iDonate.service.exception.DonationNotRegisteredException;
import br.com.idonate.iDonate.service.exception.NumberOfPointsInvalidException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
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
            throws DonationNotRegisteredException, CampaignAndBenefitedNotInformedException,
            RegisterNotFoundException, NumberOfPointsInvalidException {
        Donation savedDonation = donationService.save(donation);
        return new ResponseEntity<>(savedDonation, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> searchById(@PathVariable Long id) {
        Optional<Donation> donation = donationService.searchById(id);
        return (donation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    //Alterado Gustavo
    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<Donation>> searchByDonor(@PathVariable Long donorId) throws RegisterNotFoundException {
        return new ResponseEntity<>(donationService.searchByDonor(donorId), HttpStatus.OK);
    }

    //Alterado Gustavo
    @GetMapping("/benefited/{benefitedId}")
    public ResponseEntity<List<Donation>> searchByBenefited(@PathVariable Long benefitedId) throws RegisterNotFoundException {
        return new ResponseEntity<>(donationService.searchByBenefited(benefitedId), HttpStatus.OK);
    }

    //Alterado Gustavo
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<Donation>> searchByCampaign(@PathVariable Long campaignId) throws RegisterNotFoundException {
        return new ResponseEntity<>(donationService.searchByCampaign(campaignId), HttpStatus.OK);
    }

    @GetMapping("/all/{benefitedId}")
    public ResponseEntity<List<Donation>> searchByBenefitedAll(@PathVariable Long benefitedId) throws RegisterNotFoundException {
        return new ResponseEntity<>(donationService.searchByBenefitedAll(benefitedId), HttpStatus.OK);
    }

}
