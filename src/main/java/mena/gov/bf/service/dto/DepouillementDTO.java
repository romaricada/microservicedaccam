package mena.gov.bf.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;

import mena.gov.bf.model.DataFile;

import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link mena.gov.bf.domain.Depouillement} entity.
 */
public class DepouillementDTO implements Serializable {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    private LocalDate date;

    private String heureDebut;

    private String heureFin;

    private String lieu;

    private String salle;

    private String institutionFinanciereTMP;

    @NotNull
    private Boolean deleted;

    @NotNull
    private Boolean invalide;

    private Long  avisDacId;

  //  private List<CandidatLotDTO> candidatLots = new ArrayList<>();

    private List<CandidatDTO> candidats = new ArrayList<>();

    private List<DataFile> files = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isInvalide() {
        return invalide;
    }

    public void setInvalide(Boolean invalide) {
        this.invalide = invalide;
    }


    public List<DataFile> getFiles() {
        return files;
    }

    public void setFiles(List<DataFile> files) {
        this.files = files;
    }

    public List<CandidatDTO> getCandidats() {
        return candidats;
    }

    public void setCandidats(List<CandidatDTO> candidats) {
        this.candidats = candidats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DepouillementDTO depouillementDTO = (DepouillementDTO) o;
        if (depouillementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), depouillementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    @Override
    public String toString() {
        return "DepouillementDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", date='" + getDate() + "'" +
            ", heureDebut='" + getHeureDebut() + "'" +
            ", heureFin='" + getHeureFin() + "'" +
            ", lieu='" + getLieu() + "'" +
            ", salle='" + getSalle() + "'" +
            ", invalide='" + isDeleted() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", candidat='" + getCandidats() + "'" +
            "}";
    }

    /**
     * Gets institutionFinanciereTMP.
     *
     * @return Value of institutionFinanciereTMP.
     */
    public String getInstitutionFinanciereTMP() {
        return institutionFinanciereTMP;
    }

    /**
     * Sets new institutionFinanciereTMP.
     *
     * @param institutionFinanciereTMP New value of institutionFinanciereTMP.
     */
    public void setInstitutionFinanciereTMP(String institutionFinanciereTMP) {
        this.institutionFinanciereTMP = institutionFinanciereTMP;
    }
}
