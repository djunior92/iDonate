package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Cotacao;
import br.com.idonate.iDonate.service.exception.CotacaoNotRegisteredException;
import br.com.idonate.iDonate.service.exception.InvalidValueException;

import java.util.Optional;

public interface CotacaoService {

    Cotacao save(Cotacao cotacao) throws CotacaoNotRegisteredException, InvalidValueException;
    Optional<Cotacao> searchOpen();
    Optional<Cotacao> searchById(Long id);

}