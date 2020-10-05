package br.com.idonate.iDonate.apicielo.payload;

import br.com.idonate.iDonate.model.CreditCard;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatingTokenizedCardRequest {
    private String CustomerName;
    private String CardNumber;
    private String Holder;
    private String ExpirationDate;
    private String Brand;

    public static CreatingTokenizedCardRequest of(CreditCard creditCard) {
        return CreatingTokenizedCardRequest.builder().CustomerName(creditCard.getPrintedName())
                .CardNumber(creditCard.getCardNumber()).Holder(creditCard.getPrintedName())
                .ExpirationDate(creditCard.getExpirtationDate()).Brand(creditCard.getBrandCard().name()).build();
    }
}
