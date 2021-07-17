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
 * A Depouillement.
 */
@Entity
@Table(name = "depouillement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Depouillement extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "heure_debut")
    private String heureDebut;

    @Column(name = "heure_fin")
    private String heureFin;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "salle")
    private String salle;

    @Column(name = "avis_dac_id")
    private Long  avisDacId;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @NotNull
    @Column(name = "invalide", nullable = false)
    private Boolean invalide;

    @OneToMany(mappedBy = "depouillement")
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

    public Depouillement description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public Depouillement date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public Depouillement heureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
        return this;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public Depouillement heureFin(String heureFin) {
        this.heureFin = heureFin;
        return this;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public String getLieu() {
        return lieu;
    }

    public Depouillement lieu(String lieu) {
        this.lieu = lieu;
        return this;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getSalle() {
        return salle;
    }

    public Depouillement salle(String salle) {
        this.salle = salle;
        return this;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Depouillement deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isInvalide() {
        return invalide;
    }

    public Depouillement invalide(Boolean invalide) {
        this.invalide = invalide;
        return this;
    }

    public void setInvalide(Boolean invalide) {
        this.invalide = invalide;
    }

    public Set<CandidatLot> getCandidatLots() {
        return candidatLots;
    }

    public Depouillement candidatLots(Set<CandidatLot> candidatLots) {
        this.candidatLots = candidatLots;
        return this;
    }

    public Depouillement addCandidatLots(CandidatLot candidatLot) {
        this.candidatLots.add(candidatLot);
        candidatLot.setDepouillement(this);
        return this;
    }

    public Depouillement removeCandidatLots(CandidatLot candidatLot) {
        this.candidatLots.remove(candidatLot);
        candidatLot.setDepouillement(null);
        return this;
    }

    public void setCandidatLots(Set<CandidatLot> candidatLots) {
        this.candidatLots = candidatLots;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Depouillement)) {
            return false;
        }
        return id != null && id.equals(((Depouillement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Depouillement{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", date='" + getDate() + "'" +
            ", heureDebut='" + getHeureDebut() + "'" +
            ", heureFin='" + getHeureFin() + "'" +
            ", lieu='" + getLieu() + "'" +
            ", salle='" + getSalle() + "'" +
            ", activiteId='" + getAvisDacId() + "'" +
            ", invalide='" + isInvalide() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
