package br.com.idonate.iDonate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity(name = "payment")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "proof_of_sale")
    private String proofOfSale;

    @Size(max = 50)
    @Column(name = "t_id")
    private String tId;

    @Size(max = 100)
    @Column(name = "authorization_code")
    private String authorizationCode;

    @Size(max = 150)
    @Column(name = "payment_id")
    private String paymentId;

    private BigDecimal amount;

    private Integer status;

    @Size(max = 10)
    @Column(name = "return_code")
    private String returnCode;

    @Size(max = 150)
    @Column(name = "return_message")
    private String returnMessage;

    @Size(max = 150)
    @Column(name = "card_token")
    private String cardToken;

    @ManyToOne
    @JoinColumn(name = "recharge_id")
    private Recharge recharge;

}
