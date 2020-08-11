package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Quotation;
import br.com.idonate.iDonate.service.exception.QuotationNotRegisteredException;
import br.com.idonate.iDonate.service.exception.InvalidValueException;

import java.util.Optional;

public interface QuotationService {

    Quotation save(Quotation quotation) throws QuotationNotRegisteredException, InvalidValueException;
    Optional<Quotation> searchOpen();
    Optional<Quotation> searchById(Long id);

}