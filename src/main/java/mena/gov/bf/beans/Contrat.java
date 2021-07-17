package mena.gov.bf.beans;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A  for the {@link mena.gov.bf.domain.Contrat} entity.
 */
public class Contrat implements Serializable {

    private Long id;

    @NotNull
    private String reference;

    @NotNull
    private LocalDate dateSignature;

    @NotNull
    private LocalDate dateDebutPrevu;

    @NotNull
    private LocalDate dateFinPrevu;

    @NotNull
    private Long candidatLotId;

    private Long cautionCandidatLotId;

    @NotNull
    private Boolean deleted;

    private Boolean resilierContrat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(LocalDate dateSignature) {
        this.dateSignature = dateSignature;
    }

    public LocalDate getDateDebutPrevu() {
        return dateDebutPrevu;
    }

    public void setDateDebutPrevu(LocalDate dateDebutPrevu) {
        this.dateDebutPrevu = dateDebutPrevu;
    }

    public LocalDate getDateFinPrevu() {
        return dateFinPrevu;
    }

    public void setDateFinPrevu(LocalDate dateFinPrevu) {
        this.dateFinPrevu = dateFinPrevu;
    }

    public Long getCandidatLotId() {
        return candidatLotId;
    }

    public void setCandidatLotId(Long candidatLotId) {
        this.candidatLotId = candidatLotId;
    }

    public Long getCautionCandidatLotId() {
        return cautionCandidatLotId;
    }

    public void setCautionCandidatLotId(Long cautionCandidatLotId) {
        this.cautionCandidatLotId = cautionCandidatLotId;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Getter for property 'resilierContrat'.
     *
     * @return Value for property 'resilierContrat'.
     */
    public Boolean getResilierContrat() {
        return resilierContrat;
    }

    /**
     * Setter for property 'resilierContrat'.
     *
     * @param resilierContrat Value to set for property 'resilierContrat'.
     */
    public void setResilierContrat(Boolean resilierContrat) {
        this.resilierContrat = resilierContrat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contrat contrat = (Contrat) o;
        if (contrat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contrat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contrat{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", dateSignature='" + getDateSignature() + "'" +
            ", dateDebutPrevu='" + getDateDebutPrevu() + "'" +
            ", dateFinPrevu='" + getDateFinPrevu() + "'" +
            ", candidatLotId=" + getCandidatLotId() +
            ", cautionCandidatLotId=" + getCautionCandidatLotId() +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
