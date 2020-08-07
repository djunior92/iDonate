package br.com.idonate.iDonate.model;

import br.com.idonate.iDonate.model.Enum.BankAccountType;
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
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

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
}

