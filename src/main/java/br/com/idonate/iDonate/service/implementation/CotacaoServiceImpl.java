package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Enum.PeopleType;
import br.com.idonate.iDonate.model.Cotacao;
import br.com.idonate.iDonate.model.Perfil;
import br.com.idonate.iDonate.repository.CotacaoRepository;
import br.com.idonate.iDonate.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CotacaoServiceImpl implements CotacaoService {

    @Autowired
    CotacaoRepository cotacaoRepository;


    @Override
    public Cotacao save(Cotacao cotacao) {
        cotacao.setDateStart(LocalDateTime.now());
        cotacao.setDateEnd(LocalDateTime.now());
        Cotacao cotacao1Saved = cotacaoRepository.save(cotacao);
        return cotacao1Saved;
    }

    @Override
    public Cotacao edit(Long id, Cotacao cotacao) {
        Optional<Cotacao> optionalCotacao = cotacaoRepository.findById(id);

        if (!optionalCotacao.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        Cotacao cotacaoSaved = optionalCotacao.get();
        cotacaoSaved.setDateEnd(cotacao.getDateEnd());
        cotacaoSaved.setPricePoint(cotacao.getPricePoint());

        return cotacaoRepository.save(cotacaoSaved);
    }


    @Override
    public Optional<Cotacao> searchById(Long id) {
        return cotacaoRepository.findById(id);
    }

}
