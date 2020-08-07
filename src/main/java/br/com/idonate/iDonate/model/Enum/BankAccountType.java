package br.com.idonate.iDonate.model.Enum;

public enum BankAccountType {

    P("Conta Poupança"),
    C("Conta Corrente");

    private final String descricao;

    BankAccountType(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return descricao; }

}
