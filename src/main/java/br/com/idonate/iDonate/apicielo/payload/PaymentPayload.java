package br.com.idonate.iDonate.apicielo.payload;

import br.com.idonate.iDonate.model.CreditCard;
import br.com.idonate.iDonate.model.Recharge;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentPayload {
    private String Type;
    private BigDecimal Amount;
    private Integer Installments;
    private String SoftDescriptor;
    private CreditCardPayload CreditCard;

    public static PaymentPayload of(Recharge recharge, CreditCard creditCard) {
        return PaymentPayload.builder().Type("CreditCard").Amount(recharge.getValueRecharged()).Installments(1)
                .SoftDescriptor("iDonate Recharge").CreditCard(CreditCardPayload.of(creditCard)).build();
    }
}
