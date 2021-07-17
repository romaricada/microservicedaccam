package mena.gov.bf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Membre.
 */
@Entity
@Table(name = "membre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Membre extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "direction_id")
    private Long directionId;

    @Column(name = "type_commission_id")
    private Long typeCommissionId;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @OneToMany(mappedBy = "membre")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MembreCommission> commissions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Membre nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Membre prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Membre telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public Membre email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDirectionId() {
        return directionId;
    }

    public Membre directionId(Long directionId) {
        this.directionId = directionId;
        return this;
    }

    public void setDirectionId(Long directionId) {
        this.directionId = directionId;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Membre deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<MembreCommission> getCommissions() {
        return commissions;
    }

    public Membre commissions(Set<MembreCommission> membreCommissions) {
        this.commissions = membreCommissions;
        return this;
    }

    public Membre addCommissions(MembreCommission membreCommission) {
        this.commissions.add(membreCommission);
        membreCommission.setMembre(this);
        return this;
    }

    public Membre removeCommissions(MembreCommission membreCommission) {
        this.commissions.remove(membreCommission);
        membreCommission.setMembre(null);
        return this;
    }

    public Long getTypeCommissionId() {
        return typeCommissionId;
    }

    public void setTypeCommissionId(Long typeCommissionId) {
        this.typeCommissionId = typeCommissionId;
    }

    public void setCommissions(Set<MembreCommission> membreCommissions) {
        this.commissions = membreCommissions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Membre)) {
            return false;
        }
        return id != null && id.equals(((Membre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Membre{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", directionId=" + getDirectionId() +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
