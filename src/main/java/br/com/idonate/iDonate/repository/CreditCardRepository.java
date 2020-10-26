package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Enum.CreditCardStatus;
import br.com.idonate.iDonate.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    Optional<CreditCard> findById(Long id);
    List<CreditCard> findByProfile(Profile profile);
    List<CreditCard> findByProfileAndStatus(Profile profile, CreditCardStatus status);

}
