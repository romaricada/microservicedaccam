package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Lot.
 */
@Entity
@Table(name = "lot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Lot extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    /*@Column(name = "activite_id")
    private Long activiteId;*/

    @Column(name = "caution_id")
    private Long cautionId;

    @Column(name = "chiffre_d_affaire")
    private Double chiffreAffaire;

    @Column(name = "caution_soumission")
    private Double cautionSoumission;

    @Column(name = "montant_estime")
    private Double montantEstime;

    @Column(name = "montant_ligne_credit")
    private Double montanLigneCredit;

    @Column(name = "deleted")
    private Boolean deleted;

    @NotNull
    @Column(name = "infructueux", nullable = false)
    private Boolean infructueux;

    @Column(name = "description")
    private String description;



    @OneToMany(mappedBy = "lot")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CandidatLot> candidatLots = new HashSet<>();

    @OneToMany(mappedBy = "lot")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tache> taches = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private AvisDac avisDac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("lots")
    private Reclamation reclamation;

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    //    @OneToMany(mappedBy = "lot")
//   @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    private Set<Caution> cautionslots = new HashSet<>();
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

    public Lot libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }



    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Boolean isDeleted() {
        return deleted;
    }

    public Lot deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isInfructueux() {
        return infructueux;
    }

    public Lot infructueux(Boolean infructueux) {
        this.infructueux = infructueux;
        return this;
    }

    public void setInfructueux(Boolean infructueux) {
        this.infructueux = infructueux;
    }

    public Set<CandidatLot> getCandidatLots() {
        return candidatLots;
    }

    public Lot candidatLots(Set<CandidatLot> candidatLots) {
        this.candidatLots = candidatLots;
        return this;
    }

    public Lot addCandidatLots(CandidatLot candidatLot) {
        this.candidatLots.add(candidatLot);
        candidatLot.setLot(this);
        return this;
    }

    public Lot removeCandidatLots(CandidatLot candidatLot) {
        this.candidatLots.remove(candidatLot);
        candidatLot.setLot(null);
        return this;
    }

    public void setCandidatLots(Set<CandidatLot> candidatLots) {
        this.candidatLots = candidatLots;
    }

//    public Set<Caution> getCautionslots() {
//        return cautionslots;
//    }
//
//    public void setCautionslots(Set<Caution> cautionslots) {
//        this.cautionslots = cautionslots;
//    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lot)) {
            return false;
        }
        return id != null && id.equals(((Lot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Lot{"
                + "id=" + getId()
                + ", libelle='" + getLibelle() + "'"
                + ", activiteId=" + getAvisDac()
                + ", deleted='" + isDeleted() + "'"
                + ", infructueux='" + isInfructueux() + "'"
                + "}";
    }

    public Set<Tache> getTaches() {
        return taches;
    }

    public void setTaches(Set<Tache> taches) {
        this.taches = taches;
    }

    public Lot taches(Set<Tache> taches) {
        this.taches = taches;
        return this;
    }

    public Long getCautionId() {
        return cautionId;
    }

    public void setCautionId(Long cautionId) {
        this.cautionId = cautionId;
    }

    public Lot addTaches(Tache tache) {
        this.taches.add(tache);
        tache.setLot(this);
        return this;
    }

    public Lot removeTache(Tache tache) {
        this.taches.remove(tache);
        tache.setLot(null);
        return this;
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

    public AvisDac getAvisDac() {
        return avisDac;
    }

    public void setAvisDac(AvisDac avisDac) {
        this.avisDac = avisDac;
    }


    /**
     * Gets montantEstime.
     *
     * @return Value of montantEstime.
     */
    public Double getMontantEstime() {
        return montantEstime;
    }

    /**
     * Sets new montantEstime.
     *
     * @param montantEstime New value of montantEstime.
     */
    public void setMontantEstime(Double montantEstime) {
        this.montantEstime = montantEstime;
    }
}
