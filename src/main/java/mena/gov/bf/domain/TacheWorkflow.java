package mena.gov.bf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A TacheWorkflow.
 */
@Entity
@Table(name = "tache_workflow")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TacheWorkflow extends AbstractAuditingEntity implements Serializable {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("tacheWorkflows")
    private Tache tache;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("tacheWorkflows")
    private Workflow workflow;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public TacheWorkflow date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public TacheWorkflow deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Tache getTache() {
        return tache;
    }

    public TacheWorkflow tache(Tache tache) {
        this.tache = tache;
        return this;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Workflow getWorkflow() {
        return workflow;
    }

    public TacheWorkflow workflow(Workflow workflow) {
        this.workflow = workflow;
        return this;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TacheWorkflow)) {
            return false;
        }
        return id != null && id.equals(((TacheWorkflow) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TacheWorkflow{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
