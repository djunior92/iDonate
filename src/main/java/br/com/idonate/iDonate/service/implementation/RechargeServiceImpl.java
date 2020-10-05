package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.apicielo.payload.CreatingTokenizedCardRequest;
import br.com.idonate.iDonate.apicielo.payload.SimpleTransactionRequest;
import br.com.idonate.iDonate.apicielo.service.ApiCieloService;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.Recharge;
import br.com.idonate.iDonate.repository.RechargeRepository;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.QuotationService;
import br.com.idonate.iDonate.service.RechargeService;
import br.com.idonate.iDonate.service.exception.ProfileNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RechargeNotRegisteredException;
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
    ProfileService profileService;

    @Autowired
    QuotationService quotationService;

    @Autowired
    ApiCieloService apiCieloService;

    @Override
    @Transactional(rollbackOn = RechargeNotRegisteredException.class)
    public Recharge save(Recharge recharge) throws RechargeNotRegisteredException {
        try {
            recharge.setDateRecharge(LocalDateTime.now());
            recharge.setValueRate(BigDecimal.ZERO);
            recharge.setQuotation(quotationService.searchOpen().get());
            recharge.setPointsRecharged(calculatePoints(recharge));

            //String cardToken = apiCieloService.paymentRequest(SimpleTransactionRequest.of(recharge));

            profileService.recharge(recharge.getProfile().getId(), recharge.getPointsRecharged());

            return rechargeRepository.save(recharge);
        } catch (Exception e) {
            throw new RechargeNotRegisteredException("Erro ao realizar recarga.");
        }
    }

    @Override
    public Optional<Recharge> searchById(Long id) {
        return rechargeRepository.findById(id);
    }

    @Override
    public List<Recharge> searchByProfile(Profile profile) {
        return rechargeRepository.findByProfile(profile);
    }

    private Integer calculatePoints(Recharge recharge) {
        return recharge.getValueRecharged().multiply(recharge.getQuotation().getPricePoint()).intValue();
    }

}
