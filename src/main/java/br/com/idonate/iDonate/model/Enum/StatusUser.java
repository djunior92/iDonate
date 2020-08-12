package br.com.idonate.iDonate.model.Enum;

public enum StatusUser {

    AGUARDANDO_VALIDACAO("Aguardando Validação"),
    VALIDADO("Validado"),
    EMAIL_NAO_ENVIADO("Email não enviado");

    private final String descricao;

    StatusUser(String descricao) { this.descricao = descricao; }

    public String getDescricao() { return descricao; }

}
