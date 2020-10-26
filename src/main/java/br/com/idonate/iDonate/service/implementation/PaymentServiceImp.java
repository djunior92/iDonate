package br.com.idonate.iDonate.service.implementation;

import br.com.idonate.iDonate.model.Payment;
import br.com.idonate.iDonate.model.Recharge;
import br.com.idonate.iDonate.repository.PaymentRepository;
import br.com.idonate.iDonate.repository.RechargeRepository;
import br.com.idonate.iDonate.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RechargeRepository rechargeRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> searchById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> searchByRecharge(Long id) {
        Recharge recharge = rechargeRepository.findById(id).get();
        return paymentRepository.findByRecharge(recharge);
    }

}
