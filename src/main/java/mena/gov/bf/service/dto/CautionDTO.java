package mena.gov.bf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Caution} entity.
 */
public class CautionDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private Double montant;

    private Integer validite;

    private Boolean deleted;

    private Long lotId;

    private Long typeCautionId;
    
     private TypeCautionDTO typeCaution;
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public TypeCautionDTO getTypeCaution() {
        return typeCaution;
    }

    public void setTypeCaution(TypeCautionDTO typeCaution) {
        this.typeCaution = typeCaution;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Integer getValidite() {
        return validite;
    }

    public void setValidite(Integer validite) {
        this.validite = validite;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getTypeCautionId() {
        return typeCautionId;
    }

    public void setTypeCautionId(Long typeCautionId) {
        this.typeCautionId = typeCautionId;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CautionDTO cautionDTO = (CautionDTO) o;
        if (cautionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cautionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CautionDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", montant=" + getMontant() +
            ", validite=" + getValidite() +
            ", lot=" + getLotId() +
            ", typeCaution=" + getTypeCautionId() +
            ", deleted=" + isDeleted() +
            "}";
    }

}
