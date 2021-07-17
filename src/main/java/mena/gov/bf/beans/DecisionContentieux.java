package mena.gov.bf.beans;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A  for the {@link mena.gov.bf.domain.DecisionContentieux} entity.
 */
public class DecisionContentieux implements Serializable {

    private Long id;

    @NotNull
    private String decision;

    @NotNull
    private LocalDate date;

    @NotNull
    private String structure;

    @NotNull
    private String referenceDecision;

    @NotNull
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getReferenceDecision() {
        return referenceDecision;
    }

    public void setReferenceDecision(String referenceDecision) {
        this.referenceDecision = referenceDecision;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DecisionContentieux decisionContentieux = (DecisionContentieux) o;
        if (decisionContentieux.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), decisionContentieux.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DecisionContentieux{" +
            "id=" + getId() +
            ", decision='" + getDecision() + "'" +
            ", date='" + getDate() + "'" +
            ", structure='" + getStructure() + "'" +
            ", referenceDecision='" + getReferenceDecision() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
