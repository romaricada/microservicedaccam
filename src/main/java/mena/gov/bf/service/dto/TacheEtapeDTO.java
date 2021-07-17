package mena.gov.bf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import mena.gov.bf.domain.enumeration.Etat;
import mena.gov.bf.domain.enumeration.TypeDelai;

/**
 * A DTO for the {@link mena.gov.bf.domain.TacheEtape} entity.
 */
public class TacheEtapeDTO implements Serializable {

    private Long id;

    private Etat etat;

    private Long etapeActivitePpmId;

    private String etapeLibelle;

    @NotNull
    private Boolean deleted;

    private Long tacheId;

    private Boolean estRealise;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private TypeDelai typeDelai;

    private Long delai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Long getEtapeActivitePpmId() {
        return etapeActivitePpmId;
    }

    public void setEtapeActivitePpmId(Long etapeActivitePpmId) {
        this.etapeActivitePpmId = etapeActivitePpmId;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getTacheId() {
        return tacheId;
    }

    public void setTacheId(Long tacheId) {
        this.tacheId = tacheId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TacheEtapeDTO tacheEtapeDTO = (TacheEtapeDTO) o;
        if (tacheEtapeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tacheEtapeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TacheEtapeDTO{" +
            "id=" + getId() +
            ", etat='" + getEtat() + "'" +
            ", etapeActivitePpmId=" + getEtapeActivitePpmId() +
            ", deleted='" + isDeleted() + "'" +
            ", tache=" + getTacheId() +
            "}";
    }

    public String getEtapeLibelle() {
        return etapeLibelle;
    }

    public void setEtapeLibelle(String etapeLibelle) {
        this.etapeLibelle = etapeLibelle;
    }

    public Boolean getEstRealise() {
        return estRealise;
    }

    public void setEstRealise(Boolean estRealise) {
        this.estRealise = estRealise;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public TypeDelai getTypeDelai() {
        return typeDelai;
    }

    public void setTypeDelai(TypeDelai typeDelai) {
        this.typeDelai = typeDelai;
    }

    public Long getDelai() {
        return delai;
    }

    public void setDelai(Long delai) {
        this.delai = delai;
    }
}
