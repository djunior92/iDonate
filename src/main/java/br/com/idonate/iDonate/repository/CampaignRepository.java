package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findById(Long id);

    List<Campaign> findByProfile(Profile profile);

    List<Campaign> findByProfileAndEndDateIsNull(Profile profileId);

    List<Campaign> findByProfileAndEndDateIsNotNull(Profile profileId);

    List<Campaign> findByProfileAndNameContainingIgnoreCase(Profile profile, String name);

    List<Campaign> findByProfileAndNameContainingIgnoreCaseAndEndDateIsNull(Profile profileId, String name);

    List<Campaign> findByProfileAndNameContainingIgnoreCaseAndEndDateIsNotNull(Profile profileId, String name);

    List<Campaign> findByNameContainingIgnoreCase(String name);

    List<Campaign> findByNameContainingIgnoreCaseAndEndDateIsNull(String name);

    List<Campaign> findByNameContainingIgnoreCaseAndEndDateIsNotNull(String name);

    List<Campaign> findByEndDateIsNull();

    List<Campaign> findByEndDateIsNotNull();

}
