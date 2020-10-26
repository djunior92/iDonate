package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment save(Payment payment);
    Optional<Payment> searchById(Long id);
    List<Payment> searchByRecharge(Long id);

}
