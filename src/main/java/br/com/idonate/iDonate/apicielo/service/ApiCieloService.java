package br.com.idonate.iDonate.apicielo.service;

import br.com.idonate.iDonate.apicielo.payload.CreatingTokenizedCardRequest;
import br.com.idonate.iDonate.apicielo.payload.SimpleTransactionRequest;
import br.com.idonate.iDonate.model.Payment;

public interface ApiCieloService {

    String createCreditCard(CreatingTokenizedCardRequest creatingTokenizedCardRequest) throws NoSuchFieldException;
    Payment paymentRequest(SimpleTransactionRequest simpleTransactionRequest);

}
