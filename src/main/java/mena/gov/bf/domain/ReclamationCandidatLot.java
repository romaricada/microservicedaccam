package mena.gov.bf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A ReclamationCandidatLot.
 */
@Entity
@Table(name = "reclamation_candidat_lot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReclamationCandidatLot extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "motif")
    private String motif;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("reclamationCandidatLots")
    private Reclamation reclamation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("reclamationCandidatLots")
    private CandidatLot candidatLot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("reclamationCandidatLots")
    private Decision decision;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public ReclamationCandidatLot motif(String motif) {
        this.motif = motif;
        return this;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public LocalDate getDate() {
        return date;
    }

    public ReclamationCandidatLot date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public ReclamationCandidatLot deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public ReclamationCandidatLot reclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
        return this;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public CandidatLot getCandidatLot() {
        return candidatLot;
    }

    public ReclamationCandidatLot candidatLot(CandidatLot candidatLot) {
        this.candidatLot = candidatLot;
        return this;
    }

    public void setCandidatLot(CandidatLot candidatLot) {
        this.candidatLot = candidatLot;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReclamationCandidatLot)) {
            return false;
        }
        return id != null && id.equals(((ReclamationCandidatLot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ReclamationCandidatLot{" +
            "id=" + getId() +
            ", motif='" + getMotif() + "'" +
            ", date='" + getDate() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
