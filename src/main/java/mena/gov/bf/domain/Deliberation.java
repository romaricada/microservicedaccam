package mena.gov.bf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Deliberation.
 */
@Entity
@Table(name = "deliberation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Deliberation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "lot_id", nullable = false)
    private Long lotId;

    @Column(name = "avis_dac_id", nullable = false)
    private Long avisDacId;

    @NotNull
    @Column(name = "valide", nullable = false)
    private Boolean valide;

    @NotNull
    @Column(name = "etat_marche", nullable = false)
    private Boolean etatMarche;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @OneToMany(mappedBy = "deliberation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CandidatLot> candidatLots = new HashSet<>();

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

    public Deliberation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public Deliberation date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean isValide() {
        return valide;
    }

    public Deliberation valide(Boolean valide) {
        this.valide = valide;
        return this;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Deliberation deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<CandidatLot> getCandidatLots() {
        return candidatLots;
    }

    public Deliberation candidatLots(Set<CandidatLot> candidatLots) {
        this.candidatLots = candidatLots;
        return this;
    }

    public Deliberation addCandidatLots(CandidatLot candidatLot) {
        this.candidatLots.add(candidatLot);
        candidatLot.setDeliberation(this);
        return this;
    }

    public Deliberation removeCandidatLots(CandidatLot candidatLot) {
        this.candidatLots.remove(candidatLot);
        candidatLot.setDeliberation(null);
        return this;
    }

    public void setCandidatLots(Set<CandidatLot> candidatLots) {
        this.candidatLots = candidatLots;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deliberation)) {
            return false;
        }
        return id != null && id.equals(((Deliberation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Deliberation{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", lotId=" + lotId +
                ", avisDacId=" + avisDacId +
                ", valide=" + valide +
                ", deleted=" + deleted +
                ", candidatLots=" + candidatLots +
                '}';
    }

    /**
     * Gets etatMarche.
     *
     * @return Value of etatMarche.
     */
    public Boolean getEtatMarche() {
        return etatMarche;
    }

    /**
     * Sets new etatMarche.
     *
     * @param etatMarche New value of etatMarche.
     */
    public void setEtatMarche(Boolean etatMarche) {
        this.etatMarche = etatMarche;
    }
}
