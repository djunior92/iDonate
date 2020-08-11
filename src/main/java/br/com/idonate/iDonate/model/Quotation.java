package br.com.idonate.iDonate.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "quotation")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_start")
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @NotNull
    @Column(name = "price_point")
    private BigDecimal pricePoint;
}
