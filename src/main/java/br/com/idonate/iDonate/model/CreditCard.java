package br.com.idonate.iDonate.model;

import br.com.idonate.iDonate.model.Enum.BrandCard;
import br.com.idonate.iDonate.model.Enum.CreditCardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "credit_card")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @NotNull
    @Size(min = 10, max = 50)
    @Column(name = "card_number")
    private String cardNumber;

    @NotNull
    @Size(min = 3, max = 150)
    @Column(name = "printed_name")
    private String printedName;

    @NotNull
    @Size(min = 4, max = 10)
    @Column(name = "expirtation_date")
    private String expirtationDate;

    @NotNull
    @Column(name = "brand")
    @Enumerated(EnumType.STRING)
    private BrandCard brandCard;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CreditCardStatus status;

    @Column(name = "card_token")
    private String cardToken;

    @Column(name = "digit")
    private String digit;

    @JsonIgnore
    public Profile getProfile() {
        return this.profile;
    }

    @JsonProperty
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
