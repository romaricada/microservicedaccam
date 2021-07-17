package mena.gov.bf.beans;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A  for the {@link mena.gov.bf.domain.StatutExecution} entity.
 */
public class StatutExecution implements Serializable {

    private Long id;

    @NotNull
    private String motif;

    @NotNull
    private Boolean suspendu;

    @NotNull
    private Boolean reprise;

    @NotNull
    private LocalDate dateSuspendu;

    @NotNull
    private LocalDate dateReprise;

    @NotNull
    private Boolean deleted;


    private Long contratId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Boolean isSuspendu() {
        return suspendu;
    }

    public void setSuspendu(Boolean suspendu) {
        this.suspendu = suspendu;
    }

    public Boolean isReprise() {
        return reprise;
    }

    public void setReprise(Boolean reprise) {
        this.reprise = reprise;
    }

    public LocalDate getDateSuspendu() {
        return dateSuspendu;
    }

    public void setDateSuspendu(LocalDate dateSuspendu) {
        this.dateSuspendu = dateSuspendu;
    }

    public LocalDate getDateReprise() {
        return dateReprise;
    }

    public void setDateReprise(LocalDate dateReprise) {
        this.dateReprise = dateReprise;
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

        StatutExecution statutExecution = (StatutExecution) o;
        if (statutExecution.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statutExecution.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatutExecution{" +
            "id=" + getId() +
            ", motif='" + getMotif() + "'" +
            ", suspendu='" + isSuspendu() + "'" +
            ", reprise='" + isReprise() + "'" +
            ", dateSuspendu='" + getDateSuspendu() + "'" +
            ", dateReprise='" + getDateReprise() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", contrat=" + getContratId() +
            "}";
    }
}
