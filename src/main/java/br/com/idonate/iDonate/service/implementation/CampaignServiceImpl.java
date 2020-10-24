package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.repository.CampaignRepository;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public Campaign edit(Long id, Campaign campaign) {
        Campaign campaignEditing = campaignExist(id);

        campaignEditing.setName(campaign.getName());
        campaignEditing.setDescription(campaign.getDescription());
        campaignEditing.setLogo(campaign.getLogo());
        campaignEditing.setGoalPoints(campaign.getGoalPoints());
        campaignEditing.setEndDate(campaign.getEndDate());

        return campaignRepository.save(campaignEditing);
    }

    @Override
    public void shutdown(Long id) {
        Campaign campaignEditing = campaignExist(id);

        campaignEditing.setEndDate(LocalDateTime.now());
        campaignRepository.save(campaignEditing);
    }

    @Override
    public Campaign addPoints(Long id, Integer points) {
        Campaign campaignEditing = campaignExist(id);

        campaignEditing.setPointsReceived(campaignEditing.getPointsReceived() + points);
        return campaignRepository.save(campaignEditing);
    }

    @Override
    public Optional<Campaign> searchById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public List<Campaign> searchByPerfil(Long id) {
        Profile optionalBankAccount = profileRepository.findById(id).get();
        return campaignRepository.findByProfile(optionalBankAccount);
    }



    @Override
    public List<Campaign> searchByName(String name) {
        return campaignRepository.findByNameContaining(name);
    }

    @Override
    public void receiveCampaign(Campaign campaign, Integer points) {
        campaign.setPointsReceived(campaign.getPointsReceived() + points);
        campaignRepository.save(campaign);
    }

    private Campaign campaignExist(Long id) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(id);

        if (!optionalCampaign.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return optionalCampaign.get();
    }


}
