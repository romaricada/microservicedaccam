package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Lot} entity.
 */
public class LotDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    private String description;

    private String institutionFinanciere;

    private Long activiteId;

    private Long cautionId;

    private Double montantCandidat;

    private Double montantEstime;

    private Double chiffreAffaire;

    private Double cautionSoumission;

    private Double montanLigneCredit;
    
    private Boolean deleted;

    private Boolean dossierPaye;

    private CandidatLotDTO candidatLot;

    private Long ReclamationId;

    public Double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(Double montantEstime) {
        this.montantEstime = montantEstime;
    }

    private List<CautionDTO> cautions = new ArrayList<>();
    @NotNull
    private Boolean infructueux;


    public Double getMontantCandidat() {
        return montantCandidat;
    }

    public void setMontantCandidat(Double montantCandidat) {
        this.montantCandidat = montantCandidat;
    }

    public List<CautionDTO> getCautions() {
        return cautions;
    }

    public void setCautions(List<CautionDTO> cautions) {
        this.cautions = cautions;
    }

    private Long avisDacId;

    public Long getId() {
        return id;
    }

    public Boolean getDossierPaye() {
        return dossierPaye;
    }

    public void setDossierPaye(Boolean dossierPaye) {
        this.dossierPaye = dossierPaye;
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

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
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

    public Boolean isInfructueux() {
        return infructueux;
    }

    public void setInfructueux(Boolean infructueux) {
        this.infructueux = infructueux;
    }

    public Long getReclamationId() {
        return ReclamationId;
    }

    public void setReclamationId(Long reclamationId) {
        ReclamationId = reclamationId;
    }

    /**
     * Getter for property 'candidatLot'.
     *
     * @return Value for property 'candidatLot'.
     */
    public CandidatLotDTO getCandidatLot() {
        return candidatLot;
    }

    /**
     * Setter for property 'candidatLot'.
     *
     * @param candidatLot Value to set for property 'candidatLot'.
     */
    public void setCandidatLot(CandidatLotDTO candidatLot) {
        this.candidatLot = candidatLot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LotDTO lotDTO = (LotDTO) o;
        if (lotDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lotDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
    
    public Long getCautionId() {
        return cautionId;
    }

    public void setCautionId(Long cautionId) {
        this.cautionId = cautionId;
    }

    public Double getChiffreAffaire() {
        return chiffreAffaire;
    }

    public void setChiffreAffaire(Double chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }

    public Double getCautionSoumission() {
        return cautionSoumission;
    }

    public void setCautionSoumission(Double cautionSoumission) {
        this.cautionSoumission = cautionSoumission;
    }

    public Double getMontanLigneCredit() {
        return montanLigneCredit;
    }

    public void setMontanLigneCredit(Double montanLigneCredit) {
        this.montanLigneCredit = montanLigneCredit;
    }

    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    @Override
    public String toString() {
        return "LotDTO{" + "id=" + id +
            ", caution=" + getCautions() +
            ", libelle=" + libelle +
            ", description=" + description + ", activiteId=" + activiteId +
            ", cautionId=" + cautionId + ", chiffreAffaire=" + chiffreAffaire + ", cautionSoumission=" + cautionSoumission + ", montanLigneCredit=" + montanLigneCredit + ", deleted=" + deleted + ", dossierPaye=" + dossierPaye + ", candidatLot=" + candidatLot + ", infructueux=" + infructueux +
            ", avisDacId=" + avisDacId + '}';
    }

    /**
     * Gets institutionFinanciere.
     *
     * @return Value of institutionFinanciere.
     */
    public String getInstitutionFinanciere() {
        return institutionFinanciere;
    }

    /**
     * Sets new institutionFinanciere.
     *
     * @param institutionFinanciere New value of institutionFinanciere.
     */
    public void setInstitutionFinanciere(String institutionFinanciere) {
        this.institutionFinanciere = institutionFinanciere;
    }
}
