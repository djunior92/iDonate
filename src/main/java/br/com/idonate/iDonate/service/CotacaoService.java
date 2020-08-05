package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Cotacao;
import br.com.idonate.iDonate.service.exception.InvalidValueException;

import java.util.Optional;

public interface CotacaoService {

    Cotacao save(Cotacao cotacao) throws InvalidValueException;
    Cotacao edit(Long id, Cotacao cotacao) throws InvalidValueException;
    Optional<Cotacao> searchById(Long id);

}