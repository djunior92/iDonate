package br.com.idonate.iDonate.model;

import br.com.idonate.iDonate.model.Enum.Evaluation;
import br.com.idonate.iDonate.model.Enum.PeopleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @NotNull
    @Size(min = 2, max = 512)
    private String description;

    @Transient
    private Long likes;

    @Transient
    private Long dislikes;

    @OneToMany(mappedBy = "profile")
    private List<Address> addresses;

    @OneToMany(mappedBy = "profile")
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "profile")
    private List<Campaign> campaigns;

    @OneToMany(mappedBy = "profile")
    private List<Comment> comment;

    @JsonProperty
    public Long getLikes() {
        return this.comment.stream().filter(c -> c.getEvaluation().equals(Evaluation.LIKE)).count();
    }

    @JsonProperty
    public Long getDislikes() {
        return this.comment.stream().filter(c -> c.getEvaluation().equals(Evaluation.DISLIKE)).count();
    }

    @JsonIgnore
    public List<Address> getAddresses() {
        return this.addresses;
    }

    @JsonIgnore
    public List<BankAccount> getBankAccounts() {
        return this.bankAccounts;
    }

    @JsonIgnore
    public List<Campaign> getCampaigns() {
        return this.campaigns;
    }

    @JsonIgnore
    public List<Comment> getComment() {
        return this.comment;
    }

}
