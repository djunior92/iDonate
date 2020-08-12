package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Profile;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    Campaign save(Campaign campaign);
    Campaign edit(Long id, Campaign campaign);
    void shutdown(Long id);
    Campaign addPoints(Long id, Integer points);
    Optional<Campaign> searchById(Long id);
    List<Campaign> searchByPerfil(Profile profile);
    List<Campaign> searchByName(String name);

}
