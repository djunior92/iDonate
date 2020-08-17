package br.com.idonate.iDonate.model;

import br.com.idonate.iDonate.model.Enum.PeopleType;
import br.com.idonate.iDonate.model.Enum.StatusUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "profile")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Profile {

    @Id
    private Long id;

    @NotNull
    @Size(min = 8, max = 15)
    private String phone;

    @NotNull
    @Size(min = 4, max = 255)
    private String name;

    private String image;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    private String facebook;

    private String instagram;

    private String youtube;

    private String website;

    @Column(name = "my_points")
    private Integer myPoints;

    @Column(name = "points_received")
    private Integer pointsReceived;

    @Enumerated(EnumType.STRING)
    @Column(name = "people_type")
    private PeopleType peopleType;

    @NotNull
    @Size(min = 11, max = 14)
    private String document;

    @Column(name = "date_birth")
    private LocalDateTime dateBirth;

    @OneToMany(mappedBy = "profile")
    private List<Address> addresses;

    @OneToMany(mappedBy = "profile")
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "profile")
    private List<Campaign> campaigns;

    //@OneToMany(mappedBy = "profile")
    //private List<Comment> comment;

}
