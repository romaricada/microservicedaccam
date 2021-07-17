package mena.gov.bf.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;

import mena.gov.bf.model.DataFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Deliberation} entity.
 */
public class DeliberationDTO implements Serializable {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long lotId;

    @NotNull
    private Boolean valide;

    @NotNull
    private Boolean etatMarche;

    @NotNull
    private Boolean deleted;

    @NotNull
    private Long avisDacId;

    private String libelle;

    private String nomAvis;

    private List<CandidatLotDTO> candidatLots = new ArrayList<>();

    private List<DataFile> files = new ArrayList<>();


    public String getNumeroAvis() {
        return nomAvis;
    }

    public void setNumeroAvis(String nomAvis) {
        this.nomAvis = nomAvis;
    }

    public void setCandidatLots(List<CandidatLotDTO> candidatLots) {
        this.candidatLots = candidatLots;
    }

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

    /*public DeliberationDTO getDate() {
            return date;
        }
    */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean isValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public List<CandidatLotDTO> getCandidatLots() {
        return candidatLots;
    }

    public List<DataFile> getFiles() {
        return files;
    }

    public void setFiles(List<DataFile> files) {
        this.files = files;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public Boolean getEtatMarche() {
        return etatMarche;
    }

    public void setEtatMarche(Boolean etatMarche) {
        this.etatMarche = etatMarche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeliberationDTO deliberationDTO = (DeliberationDTO) o;
        if (deliberationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliberationDTO.getId());
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
        return "DeliberationDTO{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", date=" + date +
            ", lotId=" + lotId +
            ", valide=" + valide +
            ", deleted=" + deleted +
            ", avisDacId=" + avisDacId +
            ", libelle='" + libelle + '\'' +
            ", nomAvis='" + nomAvis + '\'' +
            ", candidatLots=" + candidatLots +
            ", files=" + files +
            '}';
    }
}
