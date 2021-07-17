package mena.gov.bf.beans;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A  for the {@link mena.gov.bf.domain.Penalite} entity.
 */
public class Penalite implements Serializable {

    private Long id;

    @NotNull
    private String motifPenalite;

    @NotNull
    private Boolean deleted;


    private Long contratId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotifPenalite() {
        return motifPenalite;
    }

    public void setMotifPenalite(String motifPenalite) {
        this.motifPenalite = motifPenalite;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Penalite penalite = (Penalite) o;
        if (penalite.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), penalite.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Penalite{" +
            "id=" + getId() +
            ", motifPenalite='" + getMotifPenalite() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", contrat=" + getContratId() +
            "}";
    }
}
