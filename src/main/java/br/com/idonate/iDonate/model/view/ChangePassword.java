package br.com.idonate.iDonate.model.view;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangePassword {

    @NotNull
    private String passwordOld;

    @NotNull
    private String passwordNew;

}
