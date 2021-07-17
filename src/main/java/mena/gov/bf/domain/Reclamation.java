package mena.gov.bf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mena.gov.bf.domain.enumeration.TypeRecours;

/**
 * A Reclamation.
 */
@Entity
@Table(name = "reclamation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reclamation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "description")
    private String description;

   /* @Column(name = "lot_id")
    private Long lotId;*/
    @Column(name = "type_recours")
    private TypeRecours typeRecours;

    @OneToMany(mappedBy = "reclamation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ReclamationCandidatLot> reclamationCandidatLots = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Lot lot;

    @ManyToOne(fetch = FetchType.LAZY)
    private AvisDac avisDac;

    public AvisDac getAvisDac() {
        return avisDac;
    }

    public void setAvisDac(AvisDac avisDac) {
        this.avisDac = avisDac;
    }

 @OneToMany(mappedBy = "reclamation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Decision> decisions = new HashSet<>();

    @OneToMany(mappedBy = "reclamation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Lot> lots  = new HashSet<>();


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public Set<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(Set<Decision> decisions) {
        this.decisions = decisions;
    }

    public Lot getLot() {
        return lot;
    }
    public void setLot(Lot lot) {
        this.lot = lot;
    }

  /*  public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }*/
      public Long getLotId() {
          return getLot().getId();
      }

        public void setLotId(Long lotId) {
            this.lot.setId(lotId);
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Reclamation date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Reclamation deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public Reclamation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ReclamationCandidatLot> getReclamationCandidatLots() {
        return reclamationCandidatLots;
    }

    public void setReclamationCandidatLots(Set<ReclamationCandidatLot> reclamationCandidatLots) {
        this.reclamationCandidatLots = reclamationCandidatLots;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reclamation)) {
            return false;
        }
        return id != null && id.equals(((Reclamation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", lotId=" + getLotId() +
            ", deleted='" + isDeleted() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

    public TypeRecours getTypeRecours() {
        return typeRecours;
    }

    public void setTypeRecours(TypeRecours typeRecours) {
        this.typeRecours = typeRecours;
    }

}
