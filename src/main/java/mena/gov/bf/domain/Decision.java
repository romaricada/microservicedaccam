package mena.gov.bf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Decision.
 */
@Entity
@Table(name = "decision")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Decision extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "structure", nullable = false)
    private String structure;

    @Column(name = "reference")
    private String reference;

    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @Column(name = "decision", nullable = false)
    private String decision;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("decisions")
    private Reclamation reclamation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStructure() {
        return structure;
    }

    public Decision structure(String structure) {
        this.structure = structure;
        return this;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDecision() {
        return decision;
    }

    public Decision decision(String decision) {
        this.decision = decision;
        return this;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Decision deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public Decision reclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
        return this;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Decision)) {
            return false;
        }
        return id != null && id.equals(((Decision) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Decision{" +
            "id=" + getId() +
            ", structure='" + getStructure() + "'" +
            ", decision='" + getDecision() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
