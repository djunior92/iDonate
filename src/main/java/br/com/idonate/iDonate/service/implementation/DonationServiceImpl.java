package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.repository.CampaignRepository;
import br.com.idonate.iDonate.repository.DonateRepository;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.service.DonationService;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.exception.CampaignAndBenefitedNotInformedException;
import br.com.idonate.iDonate.service.exception.DonationNotRegisteredException;
import br.com.idonate.iDonate.service.exception.NumberOfPointsInvalidException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
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
    ProfileRepository profileRepository;

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    ProfileService profileService;

    @Override
    @Transactional(rollbackOn = DonationNotRegisteredException.class)
    public Donation save(Donation donation) throws DonationNotRegisteredException, CampaignAndBenefitedNotInformedException,
            RegisterNotFoundException, NumberOfPointsInvalidException {
        if (Objects.isNull(donation.getBenefited()) && Objects.isNull(donation.getCampaign())) {
            throw new CampaignAndBenefitedNotInformedException("Não foi informado campanha e nem beneficiado para doar.");
        }

        Profile donor = profileService.searchProfileById(donation.getDonor().getId());
        if (donor.getMyPoints() < donation.getPointsDonationed()) {
            throw new NumberOfPointsInvalidException("Saldo não suficiente para realizar a doação. Saldo atual:" + donor.getMyPoints() + " pontos");
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
    public List<Donation> searchByDonor(Long donorId) throws RegisterNotFoundException {
        return donateRepository.findByDonor(profileRepository.findById(donorId).orElseThrow(() -> new RegisterNotFoundException("Perfil " + donorId + " do doador, não encontrado.")));
    }

    @Override
    public List<Donation> searchByBenefited(Long benefitedId) throws RegisterNotFoundException {
        return donateRepository.findByBenefited(profileRepository.findById(benefitedId).orElseThrow(() -> new RegisterNotFoundException("Perfil " + benefitedId + " do beneficiado, não encontrado.")));
    }

    @Override
    public List<Donation> searchByCampaign(Long campaignId) throws RegisterNotFoundException {
        return donateRepository.findByCampaign(campaignRepository.findById(campaignId).orElseThrow(() -> new RegisterNotFoundException("Campanha " + campaignId + " não encontrada.")));
    }
}
