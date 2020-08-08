package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Cotacao;
import br.com.idonate.iDonate.repository.CotacaoRepository;
import br.com.idonate.iDonate.service.CotacaoService;
import br.com.idonate.iDonate.service.exception.CotacaoNotRegisteredException;
import br.com.idonate.iDonate.service.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CotacaoServiceImpl implements CotacaoService {

    @Autowired
    CotacaoRepository cotacaoRepository;

    @Override
    @Transactional(rollbackOn = CotacaoNotRegisteredException.class)
    public Cotacao save(Cotacao cotacao) throws CotacaoNotRegisteredException, InvalidValueException {
        valueValidation(cotacao);
        try {
            encerrar();
            cotacao.setDateStart(LocalDateTime.now());
            return cotacaoRepository.save(cotacao);
        } catch (Exception e) {
            throw new CotacaoNotRegisteredException("Erro ao gravar cotação.");
        }
    }

    @Override
    public Optional<Cotacao> searchOpen() {
        return cotacaoRepository.findByDateEndIsNull();
    }

    @Override
    public Optional<Cotacao> searchById(Long id) {
        return cotacaoRepository.findById(id);
    }

    public void encerrar(){
        Optional<Cotacao> optionalCotacao = cotacaoRepository.findByDateEndIsNull();

        if (!optionalCotacao.isPresent()) {
            return;
        }
        Cotacao cotacaoSaved = optionalCotacao.get();
        cotacaoSaved.setDateEnd(LocalDateTime.now());

        cotacaoRepository.save(cotacaoSaved);
    }

    private void valueValidation(Cotacao cotacao) throws InvalidValueException {

        if (cotacao.getPricePoint().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidValueException("Valor: " + cotacao.getPricePoint() + " inválido. O valor deve ser maior que zero.");
        }
    }

}
