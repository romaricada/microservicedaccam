package mena.gov.bf.service.dto;
import mena.gov.bf.domain.CandidatLot;
import mena.gov.bf.model.DataFile;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.CandidatCautionLot} entity.
 */
public class CandidatCautionLotDTO implements Serializable {

    private Long id;

    private String description;

    private String institutionFinanciere;

    private String typeDeMontant;

    private LocalDate dateDebut;

    private Double montantCandidat;

    private Integer validationCandidat;

    private Long candidatLotId;

    private Long cautionId;

    private Long depoullementId;

    private CautionDTO caution;

    private Boolean depoullement;

    private Boolean deleted;
    private CandidatLot candidatLot;

    private List<DataFile> files = new ArrayList<>();

    public List<DataFile> getFiles() {
        return files;
    }

    public void setFiles(List<DataFile> files) {
        this.files = files;
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


    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Double getMontantCandidat() {
        return montantCandidat;
    }

    public void setMontantCandidat(Double montantCandidat) {
        this.montantCandidat = montantCandidat;
    }

    public Integer getValidationCandidat() {
        return validationCandidat;
    }

    public void setValidationCandidat(Integer validationCandidat) {
        this.validationCandidat = validationCandidat;
    }

    public Long getCandidatLotId() {
        return candidatLotId;
    }

    public void setCandidatLotId(Long candidatLotId) {
        this.candidatLotId = candidatLotId;
    }

    public Long getCautionId() {
        return cautionId;
    }

    public void setCautionId(Long cautionId) {
        this.cautionId = cautionId;
    }

    public Boolean isDepoullement() {
        return depoullement;
    }

    public void setDepoullement(Boolean depoullement) {
        this.depoullement = depoullement;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public CautionDTO getCaution() {
        return caution;
    }

    public void setCaution(CautionDTO caution) {
        this.caution = caution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CandidatCautionLotDTO candidatCautionLotDTO = (CandidatCautionLotDTO) o;
        if (candidatCautionLotDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidatCautionLotDTO.getId());
    }

    public Long getDepoullementId() {
        return depoullementId;
    }

    public void setDepoullementId(Long depoullementId) {
        this.depoullementId = depoullementId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CandidatCautionLotDTO{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", institutionFinanciere='" + institutionFinanciere + '\'' +
            ", dateDebut=" + dateDebut +
            ", montantCandidat=" + montantCandidat +
            ", validationCandidat=" + validationCandidat +
            ", candidatLotId=" + candidatLotId +
            ", cautionId=" + cautionId +
            ", caution=" + caution +
            ", depoullement=" + depoullement +
            ", deleted=" + deleted +
            ", files=" + files +
            '}';
    }

    public String getInstitutionFinanciere() {
        return institutionFinanciere;
    }

    public void setInstitutionFinanciere(String institutionFinanciere) {
        this.institutionFinanciere = institutionFinanciere;
    }

    /**
     * Gets candidatLot.
     *
     * @return Value of candidatLot.
     */
    public CandidatLot getCandidatLot() {
        return candidatLot;
    }

    /**
     * Sets new candidatLot.
     *
     * @param candidatLot New value of candidatLot.
     */
    public void setCandidatLot(CandidatLot candidatLot) {
        this.candidatLot = candidatLot;
    }

    /**
     * Gets typeDeMontant.
     *
     * @return Value of typeDeMontant.
     */
    public String getTypeDeMontant() {
        return typeDeMontant;
    }

    /**
     * Sets new typeDeMontant.
     *
     * @param typeDeMontant New value of typeDeMontant.
     */
    public void setTypeDeMontant(String typeDeMontant) {
        this.typeDeMontant = typeDeMontant;
    }
}
