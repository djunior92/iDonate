package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.service.exception.CampaignAndBenefitedNotInformedException;
import br.com.idonate.iDonate.service.exception.DonationNotRegisteredException;

import java.util.List;
import java.util.Optional;

public interface DonationService {

    Donation save(Donation donation) throws DonationNotRegisteredException, CampaignAndBenefitedNotInformedException;
    Optional<Donation> searchById(Long id);
    List<Donation> searchByDonor(Profile profile);
    List<Donation> searchByBenefited(Profile profile);
    List<Donation> searchByCampaign(Campaign campaign);

}
