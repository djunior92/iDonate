package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Redeem;
import br.com.idonate.iDonate.service.exception.NumberOfPointsInvalidException;
import br.com.idonate.iDonate.service.exception.RedeemNotChangeDeposited;
import br.com.idonate.iDonate.service.exception.RedeemNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RedeemService {

    Redeem save(Redeem redeem) throws RedeemNotRegisteredException, RegisterNotFoundException, NumberOfPointsInvalidException;
    Redeem changeDeposited(Long id) throws RedeemNotChangeDeposited, RegisterNotFoundException;
    Optional<Redeem> searchById(Long id);
    List<Redeem> searchByProfile(Long profileId);

}
