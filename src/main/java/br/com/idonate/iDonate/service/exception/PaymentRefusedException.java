package br.com.idonate.iDonate.service.exception;

public class PaymentRefusedException extends Exception {
    public PaymentRefusedException(String message) {
        super(message);
    }
}
