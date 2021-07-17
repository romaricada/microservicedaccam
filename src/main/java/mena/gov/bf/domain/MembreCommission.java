package mena.gov.bf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import mena.gov.bf.domain.enumeration.Poste;

/**
 * A MembreCommission.
 */
@Entity
@Table(name = "membre_commission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MembreCommission extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "avis_dac_id")
    private Long avisDacId;

    @Column(name = "reference_arrete")
    private String referenceArrete;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "poste", nullable = false)
    private Poste poste;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("commissions")
    private Membre membre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("commissions")
    private TypeCommission typeCommission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("membreCommissions")
    private Tache tache;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceArrete() {
        return referenceArrete;
    }

    public MembreCommission referenceArrete(String referenceArrete) {
        this.referenceArrete = referenceArrete;
        return this;
    }

    public void setReferenceArrete(String referenceArrete) {
        this.referenceArrete = referenceArrete;
    }

    public Poste getPoste() {
        return poste;
    }

    public MembreCommission poste(Poste poste) {
        this.poste = poste;
        return this;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public MembreCommission deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Membre getMembre() {
        return membre;
    }

    public MembreCommission membre(Membre membre) {
        this.membre = membre;
        return this;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public TypeCommission getTypeCommission() {
        return typeCommission;
    }

    public MembreCommission typeCommission(TypeCommission typeCommission) {
        this.typeCommission = typeCommission;
        return this;
    }

    public void setTypeCommission(TypeCommission typeCommission) {
        this.typeCommission = typeCommission;
    }

    public Tache getTache() {
        return tache;
    }

    public MembreCommission tache(Tache tache) {
        this.tache = tache;
        return this;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
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
        if (!(o instanceof MembreCommission)) {
            return false;
        }
        return id != null && id.equals(((MembreCommission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MembreCommission{" +
            "id=" + getId() +
            ", avisDacId=" + getAvisDacId() +
            ", referenceArrete='" + getReferenceArrete() + "'" +
            ", poste='" + getPoste() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
