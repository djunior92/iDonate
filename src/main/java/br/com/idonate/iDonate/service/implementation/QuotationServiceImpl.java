package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Quotation;
import br.com.idonate.iDonate.repository.QuotationRepository;
import br.com.idonate.iDonate.service.QuotationService;
import br.com.idonate.iDonate.service.exception.QuotationNotRegisteredException;
import br.com.idonate.iDonate.service.exception.InvalidValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class QuotationServiceImpl implements QuotationService {

    @Autowired
    QuotationRepository quotationRepository;

    @Override
    @Transactional(rollbackOn = QuotationNotRegisteredException.class)
    public Quotation save(Quotation quotation) throws QuotationNotRegisteredException, InvalidValueException {
        valueValidation(quotation);
        try {
            encerrar();
            quotation.setDateStart(LocalDateTime.now());
            return quotationRepository.save(quotation);
        } catch (Exception e) {
            throw new QuotationNotRegisteredException("Erro ao gravar cotação.");
        }
    }

    @Override
    public Optional<Quotation> searchOpen() {
        return quotationRepository.findByDateEndIsNull();
    }

    @Override
    public Optional<Quotation> searchById(Long id) {
        return quotationRepository.findById(id);
    }

    public void encerrar(){
        Optional<Quotation> optionalQuotation = quotationRepository.findByDateEndIsNull();

        if (!optionalQuotation.isPresent()) {
            return;
        }
        Quotation quotationSaved = optionalQuotation.get();
        quotationSaved.setDateEnd(LocalDateTime.now());

        quotationRepository.save(quotationSaved);
    }

    private void valueValidation(Quotation quotation) throws InvalidValueException {

        if (quotation.getPricePoint().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidValueException("Valor: " + quotation.getPricePoint() + " inválido. O valor deve ser maior que zero.");
        }
    }

}
