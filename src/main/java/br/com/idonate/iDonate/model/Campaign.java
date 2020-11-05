package br.com.idonate.iDonate.model;

import br.com.idonate.iDonate.model.Enum.Evaluation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "campaign")
@Data
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "end_forecast_date")
    private LocalDateTime endForecastDate;

    @NotNull
    @Size(min = 4, max = 100)
    private String name;

    @NotNull
    @Size(min = 4)
    private String description;

    private String logo;

    @Min(0)
    @Column(name = "goal_points")
    private Integer goalPoints;

    @Min(0)
    @Column(name = "points_received")
    private Integer pointsReceived;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Transient
    private BigDecimal targetPercentage;

    @Transient
    private Long likes;

    @Transient
    private Long dislikes;

    @OneToMany(mappedBy = "campaign")
    private List<Comment> comment;

    @JsonProperty
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @JsonProperty
    public BigDecimal getTargetPercentage() {
        BigDecimal goalPoints = new BigDecimal(ObjectUtils.defaultIfNull(this.goalPoints, 0)).setScale(5, RoundingMode.HALF_EVEN);
        BigDecimal pointsReceived = new BigDecimal(ObjectUtils.defaultIfNull(this.pointsReceived, 0)).setScale(5, RoundingMode.HALF_EVEN);
        this.targetPercentage = (pointsReceived.compareTo(BigDecimal.ZERO) != 0 ?
                (pointsReceived.divide(goalPoints, RoundingMode.HALF_EVEN)).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN) : BigDecimal.ZERO);
        return this.targetPercentage;
    }

    @JsonProperty
    public Long getLikes() {
        return (this.comment == null ?
                0 : this.comment.stream().filter(c -> c.getEvaluation().equals(Evaluation.LIKE)).count());
    }

    @JsonProperty
    public Long getDislikes() {
        return (this.comment == null ?
                0 : this.comment.stream().filter(c -> c.getEvaluation().equals(Evaluation.DISLIKE)).count());
    }

    @JsonIgnore
    public void setTargetPercentage(BigDecimal targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    @JsonIgnore
    public List<Comment> getComment() {
        return this.comment;
    }

}
