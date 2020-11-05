package br.com.idonate.iDonate.model.view;

import br.com.idonate.iDonate.model.Enum.PeopleType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ProfileView {
    @NotNull
    public Long id;

    @NotNull
    @Size(min = 2, max = 20)
    public String login;

    @NotNull
    @Size(min = 8, max = 15)
    public String phone;

    @NotNull
    @Size(min = 4, max = 255)
    public String name;

    public String image;

    public LocalDateTime registrationDate;

    public String facebook;

    public String instagram;

    public String youtube;

    public String website;

    @Enumerated(EnumType.STRING)
    public PeopleType peopleType;

    @NotNull
    @Size(min = 11, max = 14)
    public String document;

    public LocalDateTime dateBirth;

    @NotNull
    public String description;

    public Long likes;

    public Long dislikes;


}