package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.apicielo.payload.SimpleTransactionRequest;
import br.com.idonate.iDonate.apicielo.service.ApiCieloService;
import br.com.idonate.iDonate.core.IDonateUtils;
import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Payment;
import br.com.idonate.iDonate.model.Recharge;
import br.com.idonate.iDonate.repository.CreditCardRepository;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.repository.RechargeRepository;
import br.com.idonate.iDonate.service.PaymentService;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.QuotationService;
import br.com.idonate.iDonate.service.RechargeService;
import br.com.idonate.iDonate.service.exception.NumberOfPointsInvalidException;
import br.com.idonate.iDonate.service.exception.PaymentRefusedException;
import br.com.idonate.iDonate.service.exception.RechargeNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    RechargeRepository rechargeRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ProfileService profileService;

    @Autowired
    QuotationService quotationService;

    @Autowired
    ApiCieloService apiCieloService;

    @Override
    @Transactional(rollbackOn = RechargeNotRegisteredException.class)
    public Recharge save(Recharge recharge) throws RechargeNotRegisteredException, RegisterNotFoundException,
            PaymentRefusedException, NumberOfPointsInvalidException {
        if (recharge.getValueRecharged().compareTo(BigDecimal.ZERO) != 1) {
            throw new NumberOfPointsInvalidException("Número de pontos solicitado para recarga inválido.");
        }

        if (recharge.getCreditCard() == null) {
            throw new RegisterNotFoundException("Cartão de crédito não informado.");
        }

        try {
            Payment payment = new Payment();
            paymentService.save(payment);

            recharge.setDateRecharge(LocalDateTime.now());
            recharge.setValueRate(BigDecimal.ZERO);
            recharge.setQuotation(quotationService.searchOpen());
            recharge.setPointsRecharged(calculatePoints(recharge));
            recharge.setProfile(profileRepository.findById(recharge.getProfile().getId()).orElseThrow(() ->
                    new RegisterNotFoundException("Perfil " + recharge.getProfile().getId() + " não encontrado.")));
            if (recharge.getCreditCard().getId() != null)
                recharge.setCreditCard(creditCardRepository.findById(recharge.getCreditCard().getId()).orElseThrow(() ->
                        new RegisterNotFoundException("Cartão de Crédito " + recharge.getCreditCard().getId() + " não encontrado.")));

            Payment paymentReturn = apiCieloService.paymentRequest(SimpleTransactionRequest.of(recharge, payment.getId()));
            if (ObjectUtils.isEmpty(paymentReturn) || paymentReturn.getStatus() == 0) {
                throw new PaymentRefusedException("Pagamento não autorizado.");
            }
            if (recharge.getCreditCard().getId() == null)
                recharge.setCreditCard(null);
            IDonateUtils.copyNonNullProperties(paymentReturn, payment, "id");
            paymentService.save(payment);

            profileService.recharge(recharge.getProfile().getId(), recharge.getPointsRecharged());

            return rechargeRepository.save(recharge);
        } catch (RegisterNotFoundException r) {
            throw new RegisterNotFoundException(r.getMessage());
        } catch (Exception e) {
            throw new RechargeNotRegisteredException("Erro ao realizar recarga.");
        }
    }

    @Override
    public Optional<Recharge> searchById(Long id) {
        return rechargeRepository.findById(id);
    }

    @Override
    public List<Recharge> searchByProfile(Long profileId) throws RegisterNotFoundException {
        return rechargeRepository.findByProfile(profileRepository.findById(profileId).orElseThrow(() -> new RegisterNotFoundException("Perfil " + profileId + " não encontrado.")));
    }

    private Integer calculatePoints(Recharge recharge) {
        return recharge.getValueRecharged().multiply(recharge.getQuotation().getPricePoint()).intValue();
    }

}
