package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Profile;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {

    CreditCard save(CreditCard creditCard) throws Exception;
    CreditCard inactive(Long id);
    Optional<CreditCard> searchById(Long id);
    List<CreditCard> searchByProfile(Profile profile);

}
