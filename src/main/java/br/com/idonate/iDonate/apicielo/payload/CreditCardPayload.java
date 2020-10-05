package br.com.idonate.iDonate.apicielo.payload;

import br.com.idonate.iDonate.model.CreditCard;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCardPayload {
    private String CardToken;

    public static CreditCardPayload of(CreditCard creditCard) {
        return CreditCardPayload.builder().CardToken(creditCard.getCardToken()).build();
    }
}
