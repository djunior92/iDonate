package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findById(Long id);
    List<Campaign> findByProfile(Profile profile);
    List<Campaign> findByNameContaining(String name);

}
