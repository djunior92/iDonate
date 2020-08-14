package br.com.idonate.iDonate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "donation")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_donation")
    private LocalDateTime dateDonation;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Profile donor;

    @ManyToOne
    @JoinColumn(name = "benefited_id")
    private Profile benefited;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @NotNull
    @Min(1)
    @Column(name = "donated_points")
    private Integer pointsDonationed;

    @JsonIgnore
    public Profile getDonor() {
        return this.donor;
    }

    @JsonProperty
    public void setDonor(Profile donor) {
        this.donor = donor;
    }

    @JsonIgnore
    public Profile getBenefited() {
        return this.benefited;
    }

    @JsonProperty
    public void setBenefited(Profile benefited) {
        this.benefited = benefited;
    }

    @JsonIgnore
    public Campaign getCampaign() {
        return this.campaign;
    }

    @JsonProperty
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}












