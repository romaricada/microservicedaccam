package mena.gov.bf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Decision} entity.
 */
public class DecisionDTO implements Serializable {

    private Long id;

    @NotNull
    private String structure;

    @NotNull
    private String decision;

    @NotNull
    private Boolean deleted;

    private String reference;

    private LocalDate date;

    private Long reclamationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getReclamationId() {
        return reclamationId;
    }

    public void setReclamationId(Long reclamationId) {
        this.reclamationId = reclamationId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DecisionDTO decisionDTO = (DecisionDTO) o;
        if (decisionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), decisionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DecisionDTO{" +
            "id=" + getId() +
            ", structure='" + getStructure() + "'" +
            ", decision='" + getDecision() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", reclamation=" + getReclamationId() +
            ", reference=" + getReference() +
            ", date=" + getDate() +
            "}";
    }
}
