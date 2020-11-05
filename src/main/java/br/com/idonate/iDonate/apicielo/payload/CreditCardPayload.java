package br.com.idonate.iDonate.apicielo.payload;

import br.com.idonate.iDonate.model.CreditCard;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditCardPayload {
    //private String CardToken;
    private String CardNumber;
    private String Holder;
    private String ExpirationDate;
    private String SecurityCode;
    private String Brand;

    public static CreditCardPayload of(CreditCard creditCard) {
        //return CreditCardPayload.builder().CardToken(creditCard.getCardToken()).build();
        return CreditCardPayload.builder().CardNumber(creditCard.getCardNumber()).Holder(creditCard.getPrintedName())
                .ExpirationDate(creditCard.getExpirtationDate()).SecurityCode(creditCard.getDigit())
                .Brand(creditCard.getBrandCard().name()).build();
    }
}
