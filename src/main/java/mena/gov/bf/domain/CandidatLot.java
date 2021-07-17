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
 * A CandidatLot.
 */
@Entity
@Table(name = "candidat_lot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CandidatLot extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "est_candidat", nullable = false)
    private Boolean estCandidat;

    @NotNull
    @Column(name = "soumissionnaire", nullable = false)
    private Boolean soumissionnaire;

    @NotNull
    @Column(name = "attributaire", nullable = false)
    private Boolean attributaire;

    @NotNull
    @Column(name = "titulaire", nullable = false)
    private Boolean titulaire;

    @Column(name = "montant_lu_ht")
    private Double montantLuHt;

    @Column(name = "montant_corrige_ht")
    private Double montantCorrigeHt;

    @Column(name = "montant_lu_ttc")
    private Double montantLuTtc;

    @Column(name = "montant_corrige_ttc")
    private Double montantCorrigeTtc;

    @Column(name = "rang")
    private String rang;

    @Column(name = "avis_dac_id")
    private Long avisDacId;

    @Column(name = "observation")
    private String observation;

    @NotNull
    @Column(name = "dossier_paye", nullable = false)
    private Boolean dossierPaye;

    @Column(name = "nombre_point")
    private Double nombrePoint;

    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @NotNull
    @Column(name = "retenu")
    private Boolean retenu;

    @Column(name = "contrat_id")
    private Long contratId;

    @OneToMany(mappedBy = "candidatLot")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PieceCandidat> pieceCandidats = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("candidatLots")
    private Lot lot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("candidatLots")
    private Candidat candidat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("candidatLots")
    private Deliberation deliberation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("candidatLots")
    private Depouillement depouillement;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("candidatLots")
    private Reclamation reclamation;*/
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isEstCandidat() {
        return estCandidat;
    }

    public CandidatLot estCandidat(Boolean estCandidat) {
        this.estCandidat = estCandidat;
        return this;
    }

    public void setEstCandidat(Boolean estCandidat) {
        this.estCandidat = estCandidat;
    }

    public Boolean isSoumissionnaire() {
        return soumissionnaire;
    }

    public CandidatLot soumissionnaire(Boolean soumissionnaire) {
        this.soumissionnaire = soumissionnaire;
        return this;
    }

    public void setSoumissionnaire(Boolean soumissionnaire) {
        this.soumissionnaire = soumissionnaire;
    }

    public Boolean isAttributaire() {
        return attributaire;
    }

    public CandidatLot attributaire(Boolean attributaire) {
        this.attributaire = attributaire;
        return this;
    }

    public void setAttributaire(Boolean attributaire) {
        this.attributaire = attributaire;
    }

    public Boolean isTitulaire() {
        return titulaire;
    }

    public CandidatLot titulaire(Boolean titulaire) {
        this.titulaire = titulaire;
        return this;
    }

    public void setTitulaire(Boolean titulaire) {
        this.titulaire = titulaire;
    }

    public Boolean isDossierPaye() {
        return dossierPaye;
    }

    public CandidatLot dossierPaye(Boolean dossierPaye) {
        this.dossierPaye = dossierPaye;
        return this;
    }

    public void setDossierPaye(Boolean dossierPaye) {
        this.dossierPaye = dossierPaye;
    }

    public Double getNombrePoint() {
        return nombrePoint;
    }

    public CandidatLot nombrePoint(Double nombrePoint) {
        this.nombrePoint = nombrePoint;
        return this;
    }

    public void setNombrePoint(Double nombrePoint) {
        this.nombrePoint = nombrePoint;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public CandidatLot deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isRetenu() {
        return retenu;
    }

    public CandidatLot retenu(Boolean retenu) {
        this.retenu = retenu;
        return this;
    }

    public void setRetenu(Boolean retenu) {
        this.retenu = retenu;
    }

    public Set<PieceCandidat> getPieceCandidats() {
        return pieceCandidats;
    }

    public CandidatLot pieceCandidats(Set<PieceCandidat> pieceCandidats) {
        this.pieceCandidats = pieceCandidats;
        return this;
    }

    public CandidatLot addPieceCandidats(PieceCandidat pieceCandidat) {
        this.pieceCandidats.add(pieceCandidat);
        pieceCandidat.setCandidatLot(this);
        return this;
    }

    public CandidatLot removePieceCandidats(PieceCandidat pieceCandidat) {
        this.pieceCandidats.remove(pieceCandidat);
        pieceCandidat.setCandidatLot(null);
        return this;
    }

    public void setPieceCandidats(Set<PieceCandidat> pieceCandidats) {
        this.pieceCandidats = pieceCandidats;
    }

    public Lot getLot() {
        return lot;
    }

    public CandidatLot lot(Lot lot) {
        this.lot = lot;
        return this;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public CandidatLot candidat(Candidat candidat) {
        this.candidat = candidat;
        return this;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Deliberation getDeliberation() {
        return deliberation;
    }

    public CandidatLot deliberation(Deliberation deliberation) {
        this.deliberation = deliberation;
        return this;
    }

    public void setDeliberation(Deliberation deliberation) {
        this.deliberation = deliberation;
    }

    public Depouillement getDepouillement() {
        return depouillement;
    }

    public CandidatLot depouillement(Depouillement depouillement) {
        this.depouillement = depouillement;
        return this;
    }

    public void setDepouillement(Depouillement depouillement) {
        this.depouillement = depouillement;
    }

    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CandidatLot)) {
            return false;
        }
        return id != null && id.equals(((CandidatLot) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public Double getMontantLuHt() {
        return montantLuHt;
    }

    public void setMontantLuHt(Double montantLuHt) {
        this.montantLuHt = montantLuHt;
    }

    public Double getMontantCorrigeHt() {
        return montantCorrigeHt;
    }

    public void setMontantCorrigeHt(Double montantCorrigeHt) {
        this.montantCorrigeHt = montantCorrigeHt;
    }

    public Double getMontantLuTtc() {
        return montantLuTtc;
    }

    public void setMontantLuTtc(Double montantLuTtc) {
        this.montantLuTtc = montantLuTtc;
    }

    public Double getMontantCorrigeTtc() {
        return montantCorrigeTtc;
    }

    public void setMontantCorrigeTtc(Double montantCorrigeTtc) {
        this.montantCorrigeTtc = montantCorrigeTtc;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "CandidatLot{" + "id=" + id + ", estCandidat=" + estCandidat + ", soumissionnaire=" + soumissionnaire + ", attributaire=" + attributaire + ", titulaire=" + titulaire + ", montantLuHt=" + montantLuHt + ", montantCorrigeHt=" + montantCorrigeHt + ", montantLuTtc=" + montantLuTtc + ", montantCorrigeTtc=" + montantCorrigeTtc + ", rang=" + rang + ", observation=" + observation + ", dossierPaye=" + dossierPaye + ", nombrePoint=" + nombrePoint + ", deleted=" + deleted + ", retenu=" + retenu + ", contratId=" + contratId + ", pieceCandidats=" + pieceCandidats + ", lot=" + lot + ", candidat=" + candidat + ", deliberation=" + deliberation + ", depouillement=" + depouillement + '}';
    }
}
