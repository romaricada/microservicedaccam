package mena.gov.bf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Candidat.
 */
@Entity
@Table(name = "candidat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Candidat extends  AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom_structure", nullable = false)
    private String nomStructure;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "email")
    private String email;

    @Column(name = "avisdac_id")
    private Long avisdacId;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @OneToMany(mappedBy = "candidat", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CandidatLot> candidatLots = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomStructure() {
        return nomStructure;
    }

    public Candidat nomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
        return this;
    }

    public void setNomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
    }

    public String getAdresse() {
        return adresse;
    }

    public Candidat adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public Candidat email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Candidat deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<CandidatLot> getCandidatLots() {
        return candidatLots;
    }

    public Candidat candidatLots(Set<CandidatLot> candidatLots) {
        this.candidatLots = candidatLots;
        return this;
    }

    public Candidat addCandidatLots(CandidatLot candidatLot) {
        this.candidatLots.add(candidatLot);
        candidatLot.setCandidat(this);
        return this;
    }

    public Candidat removeCandidatLots(CandidatLot candidatLot) {
        this.candidatLots.remove(candidatLot);
        candidatLot.setCandidat(null);
        return this;
    }

    public void setCandidatLots(Set<CandidatLot> candidatLots) {
        this.candidatLots = candidatLots;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Candidat)) {
            return false;
        }
        return id != null && id.equals(((Candidat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Candidat{" +
            "id=" + getId() +
            ", nomStructure='" + getNomStructure() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", email='" + getEmail() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }

    /**
     * Gets avisdacId.
     *
     * @return Value of avisdacId.
     */
    public Long getAvisdacId() {
        return avisdacId;
    }

    /**
     * Sets new avisdacId.
     *
     * @param avisdacId New value of avisdacId.
     */
    public void setAvisdacId(Long avisdacId) {
        this.avisdacId = avisdacId;
    }
}
