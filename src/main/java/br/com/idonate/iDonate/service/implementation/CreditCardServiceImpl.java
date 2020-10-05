package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.apicielo.payload.CreatingTokenizedCardRequest;
import br.com.idonate.iDonate.apicielo.service.ApiCieloService;
import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Enum.CreditCardStatus;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.repository.CreditCardRepository;
import br.com.idonate.iDonate.service.CreditCardService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    ApiCieloService apiCieloService;

    @Override
    public CreditCard save(CreditCard creditCard) throws Exception {
        try {
            String cardToken = apiCieloService.createCreditCard(CreatingTokenizedCardRequest.of(creditCard));
            if (StringUtils.isEmpty(cardToken)) {
                creditCard.setStatus(CreditCardStatus.NOT_VALIDATED);
            } else {
                creditCard.setCardToken(cardToken);
                if (ObjectUtils.isEmpty(cardToken)) {
                    creditCard.setStatus(CreditCardStatus.INCOMPLETE);
                } else {
                    creditCard.setStatus(CreditCardStatus.VALITED);
                }
            }
            return creditCardRepository.save(creditCard);
        } catch (NoSuchFieldException e) {
            throw new Exception(e);
        }
    }

    @Override
    public CreditCard inactive(Long id) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(id);

        if (!optionalCreditCard.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        CreditCard creditCard = optionalCreditCard.get();
        creditCard.setStatus(CreditCardStatus.INACTIVE);
        return creditCardRepository.save(creditCard);
    }

    @Override
    public Optional<CreditCard> searchById(Long id) {
        return creditCardRepository.findById(id);
    }

    @Override
    public List<CreditCard> searchByProfile(Profile profile) {
        return creditCardRepository.findByProfile(profile);
    }
}
