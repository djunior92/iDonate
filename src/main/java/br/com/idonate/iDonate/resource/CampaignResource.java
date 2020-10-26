package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.service.CampaignService;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
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
    public ResponseEntity<Campaign> edit(@PathVariable Long id, @Valid @RequestBody Campaign campaign) throws RegisterNotFoundException {
        Campaign updateCampaign = campaignService.edit(id, campaign);
        return new ResponseEntity<>(updateCampaign, HttpStatus.OK);
    }

    @PutMapping("/{id}/shutdown")
    public ResponseEntity<Campaign> shutdown(@PathVariable Long id) throws RegisterNotFoundException {
        campaignService.shutdown(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> searchById(@PathVariable Long id) {
        Optional<Campaign> campaign = campaignService.searchById(id);
        return (campaign.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    //Alterado Gustavo
    @GetMapping(value = {"/profile/{profileId}", "/profile/{profileId}/search/{name}", "/profile/{profileId}/search/{name}/active/{active}", "/profile/{profileId}/active/{active}"})
    public ResponseEntity<List<Campaign>> searchByProfile(@PathVariable Long profileId, @PathVariable(required = false) String name, @PathVariable(required = false) Boolean active)
            throws RegisterNotFoundException {
        return new ResponseEntity<>(campaignService.searchByPerfil(profileId, name, active), HttpStatus.OK);
    }

    //Alterado Gustavo
    @GetMapping(value = {"/search/{name}", "/search/{name}/active/{active}"})
    public ResponseEntity<List<Campaign>> searchByName(@PathVariable String name, @PathVariable(required = false) Boolean active) {
        List<Campaign> campaigns = campaignService.searchByName(name, active);
        return ResponseEntity.ok(campaigns);
    }

    @GetMapping(value = {"/all", "/all/active/{active}"})
    public ResponseEntity<List<Campaign>> searchAll(@PathVariable(required = false) Boolean active) {
        List<Campaign> campaigns = campaignService.searchAll(active);
        return ResponseEntity.ok(campaigns);
    }

}
