package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

    Optional<Cotacao> findById(Long id);
}
