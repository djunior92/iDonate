package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Cotacao;
import br.com.idonate.iDonate.service.exception.InvalidEmailException;
import br.com.idonate.iDonate.service.exception.LoginUnavailableException;

import java.util.Optional;

public interface CotacaoService {

    Cotacao save(Cotacao cotacao);
    Cotacao edit(Long id, Cotacao cotacao);
    Optional<Cotacao> searchById(Long id);

}