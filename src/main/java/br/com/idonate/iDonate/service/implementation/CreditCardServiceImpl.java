package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.apicielo.payload.CreatingTokenizedCardRequest;
import br.com.idonate.iDonate.apicielo.service.ApiCieloService;
import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Enum.CreditCardStatus;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.repository.CreditCardRepository;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.service.CreditCardService;
import br.com.idonate.iDonate.service.exception.CreditCardNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ApiCieloService apiCieloService;

    @Override
    public CreditCard save(CreditCard creditCard) throws CreditCardNotRegisteredException {
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
        } catch (Exception e) {
            throw new CreditCardNotRegisteredException("Erro ao gravar cartão de crédito.");
        }
    }

    @Override
    public CreditCard inactive(Long id) throws RegisterNotFoundException {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Cartão de Crédito " + id + " não encontrado."));
        creditCard.setStatus(CreditCardStatus.INACTIVE);
        return creditCardRepository.save(creditCard);
    }

    @Override
    public Optional<CreditCard> searchById(Long id) {
        return creditCardRepository.findById(id);
    }

    @Override
    public List<CreditCard> searchByProfile(Long id, CreditCardStatus status) throws RegisterNotFoundException {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Perfil " + id + " não encontrado."));
        List<CreditCard> creditCards;
        if (status == null) {
            creditCards = creditCardRepository.findByProfile(profile);
        } else {
            creditCards = creditCardRepository.findByProfileAndStatus(profile, status);
        }
        return creditCards;
    }
}
