package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.Recharge;
import br.com.idonate.iDonate.service.exception.RechargeNotRegisteredException;

import java.util.List;
import java.util.Optional;

public interface RechargeService {

    Recharge save(Recharge recharge) throws RechargeNotRegisteredException;
    Optional<Recharge> searchById(Long id);
    List<Recharge> searchByProfile(Profile profile);

}
