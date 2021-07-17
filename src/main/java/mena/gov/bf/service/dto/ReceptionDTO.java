package mena.gov.bf.service.dto;

import mena.gov.bf.domain.AvisDac;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.domain.enumeration.TypeReception;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Reception} entity.
 */
public class ReceptionDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    private String telephone;

    private Long nomAvisDacs;

    private String email;

    @NotNull
    private LocalDate date;

    @NotNull
    private String heure;

    @NotNull
    private String lieu;

    private Boolean retirer;

    @NotNull
    private TypeReception typeReception;

    private Long avisDacId;

    @NotNull
    private Boolean deleted;
/*

    private Lot lot;
    private  Long lotId;
*/

    /*public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }*/

    public Long getNomAvisDacs() {
        return nomAvisDacs;
    }

    public void setNomAvisDacs(Long nomAvisDacs) {
        this.nomAvisDacs = nomAvisDacs;
    }

    /*public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

   public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Boolean isRetirer() {
        return retirer;
    }

    public void setRetirer(Boolean retirer) {
        this.retirer = retirer;
    }

    public Boolean isDeleted() {
        return deleted;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReceptionDTO receptionDTO = (ReceptionDTO) o;
        if (receptionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), receptionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReceptionDTO{" +
            "id=" + id +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", telephone='" + telephone + '\'' +
            ", nomAvisDacs=" + nomAvisDacs +
            ", email='" + email + '\'' +
            ", date=" + date +
            ", heure='" + heure + '\'' +
            ", lieu='" + lieu + '\'' +
            ", retirer=" + retirer +
            ", typeReception=" + typeReception +
            ", avisDacId=" + avisDacId +
            ", deleted=" + deleted +
//            ", lotId=" + lotId +
            '}';
    }
}
