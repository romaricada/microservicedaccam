package mena.gov.bf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mena.gov.bf.domain.enumeration.TypeReception;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Reception.
 */
@Entity
@Table(name = "reception")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reception extends AbstractAuditingEntity implements Serializable {

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

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "heure", nullable = false)
    private String heure;

    @NotNull
    @Column(name = "lieu", nullable = false)
    private String lieu;

    /*@Column(name = "activite_id", nullable = false)
    private Long activiteId;*/

    @Column(name = "avis_dac_id")
    private Long avisDacId;

    /* @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("receptions")
    private Lot lot; */

    @Column(name = "retirer")
    private Boolean retirer;

    @NotNull
    @Column(name = "type_reception")
    @Enumerated(EnumType.STRING)
    private TypeReception typeReception;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("reception")
    private Lot lot;*/

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    /*public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }*/

    public String getNom() {
        return nom;
    }

    public Reception nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Reception prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Reception telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public Reception email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public Reception date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public Reception heure(String heure) {
        this.heure = heure;
        return this;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

   /* public Long getAvisDacId() {
        return avisDacId;
    }*/

   /* public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    } */

    public String getLieu() {
        return lieu;
    }

    public Reception lieu(String lieu) {
        this.lieu = lieu;
        return this;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Boolean isRetirer() {
        return retirer;
    }

    public Reception retirer(Boolean retirer) {
        this.retirer = retirer;
        return this;
    }

    public void setRetirer(Boolean retirer) {
        this.retirer = retirer;
    }

    public Boolean isDeleted() {
        return deleted;
    }

   /* public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }*/

    public Reception deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public TypeReception getTypeReception() {
        return typeReception;
    }

    public void setTypeReception(TypeReception typeReception) {
        this.typeReception = typeReception;
    }

    /* public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }
*/
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reception)) {
            return false;
        }
        return id != null && id.equals(((Reception) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "Reception{" +
            "id=" + id +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", telephone='" + telephone + '\'' +
            ", email='" + email + '\'' +
            ", date=" + date +
            ", heure='" + heure + '\'' +
            ", lieu='" + lieu + '\'' +
            ", avisDacId=" + avisDacId +
            ", retirer=" + retirer +
            ", typeReception=" + typeReception +
            ", deleted=" + deleted +
            '}';
    }
}
