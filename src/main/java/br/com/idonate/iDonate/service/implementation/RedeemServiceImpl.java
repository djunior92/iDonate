package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.Redeem;
import br.com.idonate.iDonate.repository.RedeemRepository;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.QuotationService;
import br.com.idonate.iDonate.service.RedeemService;
import br.com.idonate.iDonate.service.exception.RedeemNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RedeemServiceImpl implements RedeemService {

    @Autowired
    RedeemRepository redeemRepository;

    @Autowired
    ProfileService profileService;

    @Autowired
    QuotationService quotationService;

    @Override
    @Transactional(rollbackOn = RedeemNotRegisteredException.class)
    public Redeem save(Redeem redeem) throws RedeemNotRegisteredException {
        try {
            redeem.setDateRedeem(LocalDateTime.now());
            redeem.setValueRate(BigDecimal.ZERO);
            redeem.setQuotation(quotationService.searchOpen().get());
            redeem.setPointsRedeemed(calculatePoints(redeem));

            profileService.redeem(redeem.getProfile().getId(), redeem.getPointsRedeemed());

            return redeemRepository.save(redeem);
        } catch (Exception e) {
            throw new RedeemNotRegisteredException("Erro ao fazer o resgate.");
        }
    }

    @Override
    public Optional<Redeem> searchById(Long id) {
        return redeemRepository.findById(id);
    }

    @Override
    public List<Redeem> searchByProfile(Profile profile) {
        return redeemRepository.findByProfile(profile);
    }

    private Integer calculatePoints(Redeem redeem) {
        return redeem.getValueRedeemed().multiply(redeem.getQuotation().getPricePoint()).intValue();
    }

}
