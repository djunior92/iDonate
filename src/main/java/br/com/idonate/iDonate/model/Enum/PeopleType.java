package br.com.idonate.iDonate.model.Enum;

public enum PeopleType {

    F("Física"),
    J("Jurídica");

    private final String descricao;

    PeopleType(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return descricao; }

}
