package br.com.idonate.iDonate.apicielo.payload;

import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Recharge;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleTransactionRequest {
    private String MerchantOrderId;
    private CustomerPayload Customer;
    private PaymentPayload Payment;

    public static SimpleTransactionRequest of(Recharge recharge, CreditCard creditCard) {
        return SimpleTransactionRequest.builder().MerchantOrderId(recharge.getId().toString())
                .Customer(CustomerPayload.of(recharge.getProfile().getName()))
                .Payment(PaymentPayload.of(recharge, creditCard))
                .build();
    }
}
