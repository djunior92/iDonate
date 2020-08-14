package br.com.idonate.iDonate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "recharge")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Recharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_recharge")
    private LocalDateTime dateRecharge;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Min(1)
    @NotNull
    @Column(name = "value_recharged")
    private BigDecimal valueRecharged;

    @Min(0)
    @Column(name = "value_rate")
    private BigDecimal valueRate;

    @Min(0)
    @Column(name = "points_recharged")
    private Integer pointsRecharged;

    @ManyToOne
    @JoinColumn(name = "quotation_id")
    private Quotation quotation;

}
