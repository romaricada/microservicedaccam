package mena.gov.bf.service.dto;
import mena.gov.bf.domain.TypeCommission;
import mena.gov.bf.domain.enumeration.Poste;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Membre} entity.
 */
public class MembreDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    private String telephone;

    private String email;

    private Long directionId;

    private Long typeCommissionId;

    @NotNull
    private Boolean deleted;

    private Poste post;
    private Boolean cases;
    private TypeCommission typeCommission;
    private Long activiteId;

    public TypeCommission getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(TypeCommission typeCommission) {
        this.typeCommission = typeCommission;
    }

    public Boolean getCases() {
        return cases;
    }

    public void setCases(Boolean cases) {
        this.cases = cases;
    }

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

    public Long getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Long directionId) {
        this.directionId = directionId;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Poste getPost() {
        return post;
    }

    public void setPost(Poste post) {
        this.post = post;
    }

    public Long getTypeCommissionId() {
        return typeCommissionId;
    }

    public void setTypeCommissionId(Long typeCommissionId) {
        this.typeCommissionId = typeCommissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MembreDTO membreDTO = (MembreDTO) o;
        if (membreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), membreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MembreDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", directionId=" + getDirectionId() +
            ", typeCommissionId=" + getTypeCommissionId() +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
