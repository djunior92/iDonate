package br.com.idonate.iDonate.apicielo.service;

import br.com.idonate.iDonate.apicielo.payload.CreatingTokenizedCardRequest;
import br.com.idonate.iDonate.apicielo.payload.SimpleTransactionRequest;

public interface ApiCieloService {

    String createCreditCard(CreatingTokenizedCardRequest creatingTokenizedCardRequest) throws NoSuchFieldException;
    //CreditCardPayload getCreditCardData(String cardNumber);
    String paymentRequest(SimpleTransactionRequest simpleTransactionRequest);
    //SimpleTransactionRequest getTransaction(String transaction);

}
