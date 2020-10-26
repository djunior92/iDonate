package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Payment;
import br.com.idonate.iDonate.model.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findById(Long id);
    List<Payment> findByRecharge(Recharge recharge);

}
