package mena.gov.bf.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;

import mena.gov.bf.domain.AvisDac;
import mena.gov.bf.domain.Candidat;
import mena.gov.bf.domain.CandidatLot;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.model.DataFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import mena.gov.bf.domain.enumeration.TypeRecours;

/**
 * A DTO for the {@link mena.gov.bf.domain.Reclamation} entity.
 */
public class ReclamationDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Boolean deleted;

    private String description;

    private Long avisDacId;

    private String libelle;

    private String numeroAvis;



    private TypeRecours typeRecours;

    private ReclamationCandidatLotDTO reclamationCandidatLot;

    private List<ReclamationCandidatLotDTO> reclamationCandidatLots = new ArrayList<>();
    private Set<LotDTO> lots= new HashSet<>();
    private AvisDac avisDac;
    private CandidatDTO candidatDTO;
    private CandidatLotDTO candidatLotDTO;
    private Candidat candidat;
    private CandidatLot candidatLot;


    private Set<DecisionDTO> decisions = new HashSet<>();

    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    public AvisDac getAvisDac() {
        return avisDac;
    }

    public void setAvisDac(AvisDac avisDac) {
        this.avisDac = avisDac;
    }

    private List<DataFile> files = new ArrayList<>();

    public List<DataFile> getFiles() {
        return files;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public String getNumeroAvis() {
        return numeroAvis;
    }

    public void setNumeroAvis(String numeroAvis) {
        this.numeroAvis = numeroAvis;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public CandidatLot getCandidatLot() {
        return candidatLot;
    }

    public void setCandidatLot(CandidatLot candidatLot) {
        this.candidatLot = candidatLot;
    }

    public void setFiles(List<DataFile> files) {
        this.files = files;
    }


    public CandidatDTO getCandidatDTO() {
        return candidatDTO;
    }

    public void setCandidatDTO(CandidatDTO candidatDTO) {
        this.candidatDTO = candidatDTO;
    }

    public CandidatLotDTO getCandidatLotDTO() {
        return candidatLotDTO;
    }

    public void setCandidatLotDTO(CandidatLotDTO candidatLotDTO) {
        this.candidatLotDTO = candidatLotDTO;
    }


    public Long getId() {
        return id;
    }


    public List<ReclamationCandidatLotDTO> getReclamationCandidatLots() {
        return reclamationCandidatLots;
    }

    public void setReclamationCandidatLots(List<ReclamationCandidatLotDTO> reclamationCandidatLots) {
        this.reclamationCandidatLots = reclamationCandidatLots;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<LotDTO> getLots() {
        return lots;
    }

    public void setLots(Set<LotDTO> lots) {
        this.lots = lots;
    }

    public Set<DecisionDTO> getDecisions() {
        return decisions;
    }

    public void setDecisions(Set<DecisionDTO> decisions) {
        this.decisions = decisions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReclamationDTO reclamationDTO = (ReclamationDTO) o;
        if (reclamationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reclamationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReclamationDTO{"
                + "id=" + getId()
                + ", date='" + getDate() + "'"
                + ", deleted='" + isDeleted() + "'"
                + ", description='" + getDescription() + "'"
                + ", list='" + reclamationCandidatLots + "'"
                + "}";
    }

    /**
     * Sets new reclamationCandidatLot.
     *
     * @param reclamationCandidatLot New value of reclamationCandidatLot.
     */
    public void setReclamationCandidatLot(ReclamationCandidatLotDTO reclamationCandidatLot) {
        this.reclamationCandidatLot = reclamationCandidatLot;
    }

    /**
     * Gets reclamationCandidatLot.
     *
     * @return Value of reclamationCandidatLot.
     */
    public ReclamationCandidatLotDTO getReclamationCandidatLot() {
        return reclamationCandidatLot;
    }
}
