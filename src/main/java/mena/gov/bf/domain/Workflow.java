package mena.gov.bf.domain;
import mena.gov.bf.domain.enumeration.Etat;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Workflow.
 */
@Entity
@Table(name = "workflow")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Workflow extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private Etat etat;


    @OneToMany(mappedBy = "workflow")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TacheWorkflow> tacheWorkflows = new HashSet<>();

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

    public Workflow libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Workflow deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<TacheWorkflow> getTacheWorkflows() {
        return tacheWorkflows;
    }

    public Workflow tacheWorkflows(Set<TacheWorkflow> tacheWorkflows) {
        this.tacheWorkflows = tacheWorkflows;
        return this;
    }

    public Workflow addTacheWorkflows(TacheWorkflow tacheWorkflow) {
        this.tacheWorkflows.add(tacheWorkflow);
        tacheWorkflow.setWorkflow(this);
        return this;
    }

    public Workflow removeTacheWorkflows(TacheWorkflow tacheWorkflow) {
        this.tacheWorkflows.remove(tacheWorkflow);
        tacheWorkflow.setWorkflow(null);
        return this;
    }

    public void setTacheWorkflows(Set<TacheWorkflow> tacheWorkflows) {
        this.tacheWorkflows = tacheWorkflows;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Workflow)) {
            return false;
        }
        return id != null && id.equals(((Workflow) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Workflow{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
