package br.com.idonate.iDonate.model;

import br.com.idonate.iDonate.model.Enum.StatusRedeem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "redeem")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Redeem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_redeem")
    private LocalDateTime dateRedeem;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "quotation_id")
    private Quotation quotation;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @Column(name = "value_redeemed")
    private BigDecimal valueRedeemed;

    @Column(name = "value_rate")
    private BigDecimal valueRate;

    @Min(1)
    @NotNull
    @Column(name = "points_redeemed")
    private Integer pointsRedeemed;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusRedeem status;

}












