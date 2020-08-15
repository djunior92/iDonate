package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RechargeRepository extends JpaRepository<Recharge, Long> {

    Optional<Recharge> findById(Long id);
    List<Recharge> findByProfile(Profile profile);

}
