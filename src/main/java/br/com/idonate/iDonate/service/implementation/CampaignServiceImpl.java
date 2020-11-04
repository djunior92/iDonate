package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.core.IDonateUtils;
import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.repository.CampaignRepository;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.service.CampaignService;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Campaign save(Campaign campaign) {
        campaign.setCreationDate(LocalDateTime.now());
        campaign.setPointsReceived(0);
        if (Objects.isNull(campaign.getGoalPoints())) {
            campaign.setGoalPoints(0);
        }
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign edit(Long id, Campaign campaign) throws RegisterNotFoundException {
        Campaign campaignEditing = campaignExist(id);
        IDonateUtils.copyNonNullProperties(campaign, campaignEditing, "id", "profile", "creationDate", "endDate", "targetPercentage", "pointsReceived");

        return campaignRepository.save(campaignEditing);
    }

    @Override
    public void shutdown(Long id) throws RegisterNotFoundException {
        Campaign campaignEditing = campaignExist(id);

        campaignEditing.setEndDate(LocalDateTime.now());
        campaignRepository.save(campaignEditing);
    }

    /*@Override
    public Campaign addPoints(Long id, Integer points) throws RegisterNotFoundException {
        Campaign campaignEditing = campaignExist(id);

        campaignEditing.setPointsReceived(campaignEditing.getPointsReceived() + points);
        return campaignRepository.save(campaignEditing);
    }*/

    @Override
    public Optional<Campaign> searchById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public List<Campaign> searchByPerfil(Long profileId, String name, Boolean active) throws RegisterNotFoundException {
        Profile profile = profileRepository.findById(profileId).orElseThrow(() -> new RegisterNotFoundException("Perfil " + profileId + " não encontrado."));
        List<Campaign> campaigns;
        if (name == null) {
            if (active == null) {
                campaigns = campaignRepository.findByProfile(profile);
            } else if (active) {
                campaigns = campaignRepository.findByProfileAndEndDateIsNull(profile);
            } else {
                campaigns = campaignRepository.findByProfileAndEndDateIsNotNull(profile);
            }
        } else {
            if (active == null) {
                campaigns = campaignRepository.findByProfileAndNameContainingIgnoreCase(profile, name);
            } else if (active) {
                campaigns = campaignRepository.findByProfileAndNameContainingIgnoreCaseAndEndDateIsNull(profile, name);
            } else {
                campaigns = campaignRepository.findByProfileAndNameContainingIgnoreCaseAndEndDateIsNotNull(profile, name);
            }
        }
        return campaigns;
    }

    @Override
    public List<Campaign> searchByName(String name, Boolean active) {
        List<Campaign> campaigns;
        if (active == null) {
            campaigns = campaignRepository.findByNameContainingIgnoreCase(name);
        } else if (active) {
            campaigns = campaignRepository.findByNameContainingIgnoreCaseAndEndDateIsNull(name);
        } else {
            campaigns = campaignRepository.findByNameContainingIgnoreCaseAndEndDateIsNotNull(name);
        }
        return campaigns;
    }

    @Override
    public List<Campaign> searchAll(Boolean active) {
        List<Campaign> campaigns;
        if (active == null) {
            campaigns = campaignRepository.findAll();
        } else if (active) {
            campaigns = campaignRepository.findByEndDateIsNull();
        } else {
            campaigns = campaignRepository.findByEndDateIsNotNull();
        }
        return campaigns;
    }

    @Override
    public void receiveCampaign(Campaign campaign, Integer points) {
        campaign.setPointsReceived(campaign.getPointsReceived() + points);
        campaignRepository.save(campaign);
    }

    private Campaign campaignExist(Long id) throws RegisterNotFoundException {
        return campaignRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Campanha " + id + " não encontrada."));
    }


}
