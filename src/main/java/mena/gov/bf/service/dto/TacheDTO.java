package mena.gov.bf.service.dto;

import mena.gov.bf.domain.enumeration.Etat;
import mena.gov.bf.domain.enumeration.TypeTache;
import mena.gov.bf.model.DataFile;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link mena.gov.bf.domain.Tache} entity.
 */
public class TacheDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    private String description;

    private LocalDate dateCreation;

    private TypeTache typeTache;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @NotNull
    private Boolean deleted;

    @NotNull
    private Long avisDacId;

    private Long lotId;

    private String objectId;

    private LotDTO lot = new LotDTO();

    private Double etatAvancement;

    private Etat etat;

    private TypeCommissionDTO typeCommission;

    private Set<MembreCommissionDTO> membreCommissions = new HashSet<>();

    private Set<TacheEtapeDTO> tacheEtapes = new HashSet<>();

    private Set<TacheWorkflowDTO> tacheWorkflows = new HashSet<>();

    private List<DataFile> files = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
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

        TacheDTO tacheDTO = (TacheDTO) o;
        if (tacheDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tacheDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TacheDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", dateCreation='" + getDateCreation() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }

    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MembreCommissionDTO> getMembreCommissions() {
        return membreCommissions;
    }

    public void setMembreCommissions(Set<MembreCommissionDTO> membreCommissions) {
        this.membreCommissions = membreCommissions;
    }

    public Set<TacheEtapeDTO> getTacheEtapes() {
        return tacheEtapes;
    }

    public void setTacheEtapes(Set<TacheEtapeDTO> tacheEtapes) {
        this.tacheEtapes = tacheEtapes;
    }

    public TypeTache getTypeTache() {
        return typeTache;
    }

    public void setTypeTache(TypeTache typeTache) {
        this.typeTache = typeTache;
    }

    public Set<TacheWorkflowDTO> getTacheWorkflows() {
        return tacheWorkflows;
    }

    public void setTacheWorkflows(Set<TacheWorkflowDTO> tacheWorkflows) {
        this.tacheWorkflows = tacheWorkflows;
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

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public LotDTO getLot() {
        return lot;
    }

    public void setLot(LotDTO lot) {
        this.lot = lot;
    }

    public TypeCommissionDTO getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(TypeCommissionDTO typeCommission) {
        this.typeCommission = typeCommission;
    }

    public Double getEtatAvancement() {
        return etatAvancement;
    }

    public void setEtatAvancement(Double etatAvancement) {
        this.etatAvancement = etatAvancement;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public List<DataFile> getFiles() {
        return files;
    }

    public void setFiles(List<DataFile> files) {
        this.files = files;
    }
}
