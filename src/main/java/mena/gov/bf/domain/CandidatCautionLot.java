package mena.gov.bf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CandidatCautionLot.
 */
@Entity
@Table(name = "candidat_caution_lot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CandidatCautionLot extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "institution_financiere")
    private String institutionFinanciere;

    @Column(name = "type_de_montant")
    private String typeDeMontant;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "montant_candidat")
    private Double montantCandidat;

    @Column(name = "validation_candidat")
    private Integer validationCandidat;

    @Column(name = "is_depoullement")
    private Boolean depoullement;

    @Column(name = "depoullement_id")
    private Long depoullementId;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("candidatCautionLots")
    private CandidatLot candidatLot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("candidatCautionLots")
    private Caution caution;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public CandidatCautionLot description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public CandidatCautionLot dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Double getMontantCandidat() {
        return montantCandidat;
    }

    public CandidatCautionLot montantCandidat(Double montantCandidat) {
        this.montantCandidat = montantCandidat;
        return this;
    }

    public void setMontantCandidat(Double montantCandidat) {
        this.montantCandidat = montantCandidat;
    }

    public Integer getValidationCandidat() {
        return validationCandidat;
    }

    public CandidatCautionLot validationCandidat(Integer validationCandidat) {
        this.validationCandidat = validationCandidat;
        return this;
    }

    public void setValidationCandidat(Integer validationCandidat) {
        this.validationCandidat = validationCandidat;
    }

    public CandidatLot getCandidatLot() {
        return candidatLot;
    }

    public CandidatCautionLot candidatLot(CandidatLot candidatLot) {
        this.candidatLot = candidatLot;
        return this;
    }

    public void setCandidatLot(CandidatLot candidatLot) {
        this.candidatLot = candidatLot;
    }

    public Caution getCaution() {
        return caution;
    }

    public CandidatCautionLot caution(Caution caution) {
        this.caution = caution;
        return this;
    }

    public void setCaution(Caution caution) {
        this.caution = caution;
    }

    public Boolean isDepoullement() {
        return depoullement;
    }

    public CandidatCautionLot depoullement(Boolean depoullement) {
        this.depoullement = depoullement;
        return this;
    }

    public void setDepoullement(Boolean depoullement) {
        this.depoullement = depoullement;
    }


    public Boolean isDeleted() {
        return deleted;
    }

    public CandidatCautionLot deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CandidatCautionLot)) {
            return false;
        }
        return id != null && id.equals(((CandidatCautionLot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    public String getInstitutionFinanciere() {
        return institutionFinanciere;
    }

    public void setInstitutionFinanciere(String institutionFinanciere) {
        this.institutionFinanciere = institutionFinanciere;
    }

    public Long getDepoullementId() {
        return depoullementId;
    }

    public void setDepoullementId(Long depoullementId) {
        this.depoullementId = depoullementId;
    }

    @Override
    public String toString() {
        return "CandidatCautionLot{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", institutionFinanciere='" + institutionFinanciere + '\'' +
                ", dateDebut=" + dateDebut +
                ", montantCandidat=" + montantCandidat +
                ", validationCandidat=" + validationCandidat +
                ", depoullement=" + depoullement +
                ", depoullementId=" + depoullementId +
                ", deleted=" + deleted +
                ", candidatLot=" + candidatLot +
                ", caution=" + caution +
                '}';
    }

    /**
     * Sets new typeDeMontant.
     *
     * @param typeDeMontant New value of typeDeMontant.
     */
    public void setTypeDeMontant(String typeDeMontant) {
        this.typeDeMontant = typeDeMontant;
    }

    /**
     * Gets typeDeMontant.
     *
     * @return Value of typeDeMontant.
     */
    public String getTypeDeMontant() {
        return typeDeMontant;
    }
}
