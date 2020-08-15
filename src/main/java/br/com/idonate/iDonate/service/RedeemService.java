package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.model.Redeem;
import br.com.idonate.iDonate.service.exception.RedeemNotRegisteredException;

import java.util.List;
import java.util.Optional;

public interface RedeemService {

    Redeem save(Redeem redeem) throws RedeemNotRegisteredException;
    Optional<Redeem> searchById(Long id);
    List<Redeem> searchByProfile(Profile profile);

}
