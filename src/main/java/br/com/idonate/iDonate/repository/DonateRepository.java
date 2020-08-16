package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Donation;
import br.com.idonate.iDonate.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonateRepository extends JpaRepository<Donation, Long> {

    Optional<Donation> findById(Long id);
    List<Donation> findByDonor(Profile profile);
    List<Donation> findByBenefited(Profile profile);
    List<Donation> findByCampaign(Campaign campaign);

}
