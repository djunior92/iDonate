package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.BankAccount;
import br.com.idonate.iDonate.model.Profile;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {

    BankAccount save(BankAccount bankAccount);
    BankAccount edit(Long id, BankAccount bankAccount);
    void delete(Long id);
    Optional<BankAccount> searchById(Long id);
    List<BankAccount> searchByProfile(Long id);
}