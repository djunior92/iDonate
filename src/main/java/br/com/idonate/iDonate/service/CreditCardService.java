package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Enum.CreditCardStatus;
import br.com.idonate.iDonate.service.exception.CreditCardNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {

    CreditCard save(CreditCard creditCard) throws CreditCardNotRegisteredException;
    CreditCard inactive(Long id) throws RegisterNotFoundException;
    Optional<CreditCard> searchById(Long id);
    List<CreditCard> searchByProfile(Long id, CreditCardStatus status) throws RegisterNotFoundException;

}
