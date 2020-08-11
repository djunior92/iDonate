package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {

    Optional<Quotation> findById(Long id);
    Optional<Quotation> findByDateEndIsNull();

}
