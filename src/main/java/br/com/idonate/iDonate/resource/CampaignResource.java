package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/campaign")
public class CampaignResource {

    @Autowired
    CampaignService campaignService;

    @PostMapping
    public ResponseEntity<Campaign> save(@Valid @RequestBody Campaign campaign){
        Campaign savedCampaign = campaignService.save(campaign);
        return new ResponseEntity<>(savedCampaign, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campaign> edit(@PathVariable Long id, @Valid @RequestBody Campaign campaign) {
        Campaign updateCampaign = campaignService.edit(id, campaign);
        return new ResponseEntity<>(updateCampaign, HttpStatus.OK);
    }

    @PutMapping("/{id}/shutdown")
    public ResponseEntity shutdown(@PathVariable Long id) {
        campaignService.shutdown(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> searchById(@PathVariable Long id) {
        Optional<Campaign> campaign = campaignService.searchById(id);
        return (campaign.isPresent() ? ResponseEntity.ok(campaign.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Campaign>> searchByPerfil(@RequestBody Profile profile) {
        return new ResponseEntity<>(campaignService.searchByPerfil(profile), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Campaign>> searchByName(@PathVariable String name) {
        List<Campaign> campaigns = campaignService.searchByName(name);
        return ResponseEntity.ok(campaigns);
    }

}
