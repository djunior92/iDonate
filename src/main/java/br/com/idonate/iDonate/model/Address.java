package br.com.idonate.iDonate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "Address")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @NotNull
    @Column(name = "street_address")
    @Size(max = 100)
    private String streetAddress;

    @NotNull
    @Column(name = "number_address")
    @Size(max = 6)
    private String numberAddress;

    @Column(name = "complement_address")
    @Size(max = 50)
    private String complementAddress;

    @NotNull
    @Column(name = "cep")
    @Size(min = 8, max = 10)
    private String cep;

    @NotNull
    @Column(name = "neighborhood")
    @Size(max = 50)
    private String neighborhood;

    @NotNull
    @Column(name = "city")
    @Size(max = 100)
    private String city;

    @NotNull
    @Column(name = "uf")
    @Size(min = 2, max = 2)
    private String uf;

    @JsonIgnore
    public Profile getProfile() {
        return this.profile;
    }

    @JsonProperty
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
