package br.com.idonate.iDonate.model;

import br.com.idonate.iDonate.model.Enum.Evaluation;
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

@Entity(name = "comment")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_comment")
    private LocalDateTime dateComment;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Profile author;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @Column(name = "title")
    @Size(min = 1, max = 256)
    private String title;

    @NotNull
    @Column(name = "description")
    @Size(min = 1, max = 512)
    private String description;

    @NotNull
    @Column(name = "evaluation")
    @Enumerated(EnumType.STRING)
    private Evaluation evaluation;

    @JsonProperty
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @JsonProperty
    public void setAuthor(Profile author) {
        this.author = author;
    }

    @JsonProperty
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    @JsonIgnore
    public String getTitle() {
        return this.title;
    }
}












