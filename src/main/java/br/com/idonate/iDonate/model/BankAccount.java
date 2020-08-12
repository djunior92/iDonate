package br.com.idonate.iDonate.model;

import br.com.idonate.iDonate.model.Enum.BankAccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "bank_account")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @NotNull
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private BankAccountType accountType;

    @NotNull
    @Column(name = "bank")
    private String bank;

    @NotNull
    @Column(name = "agency")
    private String agency;

    @Column(name = "dg_agency")
    private String dgAgency;

    @NotNull
    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "dg_account")
    private String dgAccount;

    @JsonIgnore
    public Profile getProfile() {
        return this.profile;
    }

    @JsonProperty
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}

