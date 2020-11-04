package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Enum.StatusRedeem;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.Redeem;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.repository.RedeemRepository;
import br.com.idonate.iDonate.service.ProfileService;
import br.com.idonate.iDonate.service.QuotationService;
import br.com.idonate.iDonate.service.RedeemService;
import br.com.idonate.iDonate.service.exception.NumberOfPointsInvalidException;
import br.com.idonate.iDonate.service.exception.RedeemNotChangeDeposited;
import br.com.idonate.iDonate.service.exception.RedeemNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
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
    ProfileRepository profileRepository;

    @Autowired
    ProfileService profileService;

    @Autowired
    QuotationService quotationService;

    @Override
    @Transactional(rollbackOn = RedeemNotRegisteredException.class)
    public Redeem save(Redeem redeem) throws RedeemNotRegisteredException, RegisterNotFoundException, NumberOfPointsInvalidException {
        Profile profile = profileService.searchProfileById(redeem.getProfile().getId());
        if (profile.getPointsReceived() < redeem.getPointsRedeemed()) {
            throw new NumberOfPointsInvalidException("Saldo não suficiente para realizar o resgate. Saldo atual:" + profile.getPointsReceived() + " pontos");
        }

        try {
            redeem.setDateRedeem(LocalDateTime.now());
            redeem.setValueRate(BigDecimal.ZERO);
            redeem.setQuotation(quotationService.searchOpen());
            redeem.setPointsRedeemed(calculatePoints(redeem));
            redeem.setStatus(StatusRedeem.REQUESTED);

            profileService.redeem(redeem.getProfile().getId(), redeem.getPointsRedeemed());

            return redeemRepository.save(redeem);
        } catch (Exception e) {
            throw new RedeemNotRegisteredException("Erro ao fazer o resgate.");
        }
    }

    @Override
    public Redeem changeDeposited(Long id) throws RedeemNotChangeDeposited, RegisterNotFoundException {
        try {
            Redeem redeem = redeemRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Resgate " + id + " não encontrado."));
            redeem.setStatus(StatusRedeem.DEPOSITED);

            return redeemRepository.save(redeem);
        } catch (Exception e) {
            throw new RedeemNotChangeDeposited("Erro ao alterar o resgate para depositado.");
        }
    }

    @Override
    public Optional<Redeem> searchById(Long id) {
        return redeemRepository.findById(id);
    }

    @Override
    public List<Redeem> searchByProfile(Long profileId) {
        return redeemRepository.findByProfile(profileRepository.findById(profileId).orElseThrow());
    }

    private Integer calculatePoints(Redeem redeem) {
        return redeem.getValueRedeemed().multiply(redeem.getQuotation().getPricePoint()).intValue();
    }

}
