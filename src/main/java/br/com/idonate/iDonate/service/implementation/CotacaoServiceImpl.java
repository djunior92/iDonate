package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Cotacao;
import br.com.idonate.iDonate.repository.CotacaoRepository;
import br.com.idonate.iDonate.service.CotacaoService;
import br.com.idonate.iDonate.service.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CotacaoServiceImpl implements CotacaoService {

    @Autowired
    CotacaoRepository cotacaoRepository;


    @Override
    public Cotacao save(Cotacao cotacao) throws InvalidValueException {
        valueValidation(cotacao);

        cotacao.setDateStart(LocalDateTime.now());
        cotacao.setDateEnd(LocalDateTime.now());
        Cotacao cotacao1Saved = cotacaoRepository.save(cotacao);
        return cotacao1Saved;
    }

    @Override
    public Cotacao encerrar(Long id, Cotacao cotacao){
        Optional<Cotacao> optionalCotacao = cotacaoRepository.findById(id);

        if (!optionalCotacao.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        Cotacao cotacaoSaved = optionalCotacao.get();
        cotacaoSaved.setDateEnd(cotacao.getDateEnd());

        return cotacaoRepository.save(cotacaoSaved);
    }

    @Override
    public Optional<Cotacao> searchById(Long id) {
        return cotacaoRepository.findById(id);
    }


    private void valueValidation(Cotacao cotacao) throws InvalidValueException {

        if (cotacao.getPricePoint().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidValueException("Valor: " + cotacao.getPricePoint() + " inválido. O valor deve ser maior que zero.");
        }
    }

}
