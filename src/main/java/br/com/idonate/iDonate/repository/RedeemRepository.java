package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.Redeem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RedeemRepository extends JpaRepository<Redeem, Long> {

    Optional<Redeem> findById(Long id);
    List<Redeem> findByProfile(Profile profile);

}
