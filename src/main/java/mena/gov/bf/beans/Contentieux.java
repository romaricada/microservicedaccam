package mena.gov.bf.beans;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A  for the {@link mena.gov.bf.domain.Contentieux} entity.
 */
public class Contentieux implements Serializable {

    private Long id;

    @NotNull
    private String objet;

    @NotNull
    private Boolean deleted;


    private Long contratId;

    private Long decisionContentieuxId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    public Long getDecisionContentieuxId() {
        return decisionContentieuxId;
    }

    public void setDecisionContentieuxId(Long decisionContentieuxId) {
        this.decisionContentieuxId = decisionContentieuxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contentieux contentieux = (Contentieux) o;
        if (contentieux.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contentieux.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contentieux{" +
            "id=" + getId() +
            ", objet='" + getObjet() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", contrat=" + getContratId() +
            ", decisionContentieux=" + getDecisionContentieuxId() +
            "}";
    }
}
