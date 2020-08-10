package br.com.idonate.iDonate.service.exception;

public class LoginAlreadyValidatedException extends Exception {
    public LoginAlreadyValidatedException(String message) {
        super(message);
    }
}
