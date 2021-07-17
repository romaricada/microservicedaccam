package mena.gov.bf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mena.gov.bf.domain.enumeration.Etat;
import mena.gov.bf.domain.enumeration.TypeTache;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tache.
 */
@Entity
@Table(name = "tache")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tache extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_tache")
    private TypeTache typeTache;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @NotNull
    @Column(name = "avis_dac_id", nullable = false)
    private Long avisDacId;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "object_Id")
    private String objectId;

    @Column(name= "etat_avancement")
    private Double etatAvancement;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;

    @OneToMany(mappedBy = "tache")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TacheWorkflow> tacheWorkflows = new HashSet<>();

    @OneToMany(mappedBy = "tache")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TacheEtape> tacheEtapes = new HashSet<>();

    @OneToMany(mappedBy = "tache")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MembreCommission> membreCommissions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("taches")
    private Lot lot;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Tache libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Tache dateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Tache deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<TacheWorkflow> getTacheWorkflows() {
        return tacheWorkflows;
    }

    public Tache tacheWorkflows(Set<TacheWorkflow> tacheWorkflows) {
        this.tacheWorkflows = tacheWorkflows;
        return this;
    }

    public Tache addTacheWorkflows(TacheWorkflow tacheWorkflow) {
        this.tacheWorkflows.add(tacheWorkflow);
        tacheWorkflow.setTache(this);
        return this;
    }

    public Tache removeTacheWorkflows(TacheWorkflow tacheWorkflow) {
        this.tacheWorkflows.remove(tacheWorkflow);
        tacheWorkflow.setTache(null);
        return this;
    }

    public void setTacheWorkflows(Set<TacheWorkflow> tacheWorkflows) {
        this.tacheWorkflows = tacheWorkflows;
    }

    public Set<TacheEtape> getTacheEtapes() {
        return tacheEtapes;
    }

    public Tache tacheEtapes(Set<TacheEtape> tacheEtapes) {
        this.tacheEtapes = tacheEtapes;
        return this;
    }

    public Tache addTacheEtapes(TacheEtape tacheEtape) {
        this.tacheEtapes.add(tacheEtape);
        tacheEtape.setTache(this);
        return this;
    }

    public Tache removeTacheEtapes(TacheEtape tacheEtape) {
        this.tacheEtapes.remove(tacheEtape);
        tacheEtape.setTache(null);
        return this;
    }

    public void setTacheEtapes(Set<TacheEtape> tacheEtapes) {
        this.tacheEtapes = tacheEtapes;
    }

    public Set<MembreCommission> getMembreCommissions() {
        return membreCommissions;
    }

    public Tache membreCommissions(Set<MembreCommission> membreCommissions) {
        this.membreCommissions = membreCommissions;
        return this;
    }

    public Tache addMembreCommissions(MembreCommission membreCommission) {
        this.membreCommissions.add(membreCommission);
        membreCommission.setTache(this);
        return this;
    }

    public Tache removeMembreCommissions(MembreCommission membreCommission) {
        this.membreCommissions.remove(membreCommission);
        membreCommission.setTache(null);
        return this;
    }

    public void setMembreCommissions(Set<MembreCommission> membreCommissions) {
        this.membreCommissions = membreCommissions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tache)) {
            return false;
        }
        return id != null && id.equals(((Tache) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tache{" +
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

    public TypeTache getTypeTache() {
        return typeTache;
    }

    public void setTypeTache(TypeTache typeTache) {
        this.typeTache = typeTache;
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

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Tache lot(Lot lot) {
        this.lot = lot;
        return this;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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
}
