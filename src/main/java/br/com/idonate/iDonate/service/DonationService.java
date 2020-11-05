package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.service.exception.CampaignAndBenefitedNotInformedException;
import br.com.idonate.iDonate.service.exception.DonationNotRegisteredException;
import br.com.idonate.iDonate.service.exception.NumberOfPointsInvalidException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DonationService {

    Donation save(Donation donation) throws DonationNotRegisteredException, CampaignAndBenefitedNotInformedException,
            RegisterNotFoundException, NumberOfPointsInvalidException;
    Optional<Donation> searchById(Long id);
    List<Donation> searchByDonor(Long donorId) throws RegisterNotFoundException;
    List<Donation> searchByBenefited(Long benefitedId) throws RegisterNotFoundException;
    List<Donation> searchByCampaign(Long campaignId) throws RegisterNotFoundException;
    List<Donation> searchByBenefitedAll(Long benefitedId) throws RegisterNotFoundException;

}
