package mena.gov.bf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mena.gov.bf.domain.enumeration.TypeDelai;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import mena.gov.bf.domain.enumeration.Etat;

/**
 * A TacheEtape.
 */
@Entity
@Table(name = "tache_etape")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TacheEtape extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    @Column(name = "etape_activite_ppm_id")
    private Long etapeActivitePpmId;

    @Column(name = "etape_libelle")
    private String etapeLibelle;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "est_realise", nullable = false)
    private Boolean estRealise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("tacheEtapes")
    private Tache tache;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "delai")
    private Long delai;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_delai")
    private TypeDelai typeDelai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etat getEtat() {
        return etat;
    }

    public TacheEtape etat(Etat etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Long getEtapeActivitePpmId() {
        return etapeActivitePpmId;
    }

    public TacheEtape etapeActivitePpmId(Long etapeActivitePpmId) {
        this.etapeActivitePpmId = etapeActivitePpmId;
        return this;
    }

    public void setEtapeActivitePpmId(Long etapeActivitePpmId) {
        this.etapeActivitePpmId = etapeActivitePpmId;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public TacheEtape deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Tache getTache() {
        return tache;
    }

    public TacheEtape tache(Tache tache) {
        this.tache = tache;
        return this;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TacheEtape)) {
            return false;
        }
        return id != null && id.equals(((TacheEtape) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TacheEtape{" +
            "id=" + getId() +
            ", etat='" + getEtat() + "'" +
            ", etapeActivitePpmId=" + getEtapeActivitePpmId() +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }

    public String getEtapeLibelle() {
        return etapeLibelle;
    }

    public void setEtapeLibelle(String etapeLibelle) {
        this.etapeLibelle = etapeLibelle;
    }

    public Boolean getEstRealise() {
        return estRealise;
    }

    public void setEstRealise(Boolean estRealise) {
        this.estRealise = estRealise;
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

    public Long getDelai() {
        return delai;
    }

    public void setDelai(Long delai) {
        this.delai = delai;
    }

    public TypeDelai getTypeDelai() {
        return typeDelai;
    }

    public void setTypeDelai(TypeDelai typeDelai) {
        this.typeDelai = typeDelai;
    }
}
