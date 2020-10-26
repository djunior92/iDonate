package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    Campaign save(Campaign campaign);
    Campaign edit(Long id, Campaign campaign) throws RegisterNotFoundException;
    void shutdown(Long id) throws RegisterNotFoundException;
    //Campaign addPoints(Long id, Integer points) throws RegisterNotFoundException;
    Optional<Campaign> searchById(Long id);
    List<Campaign> searchByPerfil(Long profileId, String name, Boolean active) throws RegisterNotFoundException;
    List<Campaign> searchByName(String name, Boolean active);
    List<Campaign> searchAll(Boolean active);
    void receiveCampaign(Campaign campaign, Integer points);

}
