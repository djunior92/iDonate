package br.com.idonate.iDonate.model.Enum;

public enum StatusUser {

    AGUARDANDO_VALIDACAO("Aguardando validação pelo e-mail."),
    VALIDADO("Validado.");

    private final String descricao;

    StatusUser(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return descricao; }

}
