package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Recharge;
import br.com.idonate.iDonate.service.exception.NumberOfPointsInvalidException;
import br.com.idonate.iDonate.service.exception.PaymentRefusedException;
import br.com.idonate.iDonate.service.exception.RechargeNotRegisteredException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RechargeService {

    Recharge save(Recharge recharge) throws RechargeNotRegisteredException, RegisterNotFoundException,
            PaymentRefusedException, NumberOfPointsInvalidException;
    Optional<Recharge> searchById(Long id);
    List<Recharge> searchByProfile(Long profileId) throws RegisterNotFoundException;

}
