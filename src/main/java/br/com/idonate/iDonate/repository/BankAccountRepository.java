package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.BankAccount;
import br.com.idonate.iDonate.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findById(Long id);
    List<BankAccount> findByPerfil(Perfil perfil);
}


