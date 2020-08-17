package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.repository.DonateRepository;
import br.com.idonate.iDonate.service.DonationService;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.exception.CampaignAndBenefitedNotInformedException;
import br.com.idonate.iDonate.service.exception.DonationNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    DonateRepository donateRepository;

    @Autowired
    ProfileService profileService;

    @Override
    @Transactional(rollbackOn = DonationNotRegisteredException.class)
    public Donation save(Donation donation) throws DonationNotRegisteredException, CampaignAndBenefitedNotInformedException {


        if (Objects.isNull(donation.getBenefited()) && Objects.isNull(donation.getCampaign())) {
            throw new CampaignAndBenefitedNotInformedException("Não foi informado campanha e nem beneficiado para doar.");
        }

        try {
            donation.setDateDonation(LocalDateTime.now());

            profileService.donation(donation);

            return donateRepository.save(donation);
        } catch (Exception e) {
            throw new DonationNotRegisteredException("Erro ao realizar doação");
        }
    }

    @Override
    public Optional<Donation> searchById(Long id) {
        return donateRepository.findById(id);
    }

    @Override
    public List<Donation> searchByDonor(Profile profile) {
        return donateRepository.findByDonor(profile);
    }

    @Override
    public List<Donation> searchByBenefited(Profile profile) {
        return donateRepository.findByBenefited(profile);
    }

    @Override
    public List<Donation> searchByCampaign(Campaign campaign) {
        return donateRepository.findByCampaign(campaign);
    }
}
