package mena.gov.bf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import mena.gov.bf.domain.enumeration.Poste;

/**
 * A DTO for the {@link mena.gov.bf.domain.MembreCommission} entity.
 */
public class MembreCommissionDTO implements Serializable {

    private Long id;

    private Long avisDacId;

    private String referenceArrete;

    @NotNull
    private Poste poste;

    @NotNull
    private Boolean deleted;

    private Long membreId;

    private Long typeCommissionId;

    private Long tacheId;

    private MembreDTO membre = new MembreDTO();

    private TypeCommissionDTO typeCommission= new TypeCommissionDTO();

    private List<MembreDTO> membreses = new ArrayList<>();

    public List<MembreDTO> getMembreses() {
        return membreses;
    }

    public void setMembreses(List<MembreDTO> membreses) {
        this.membreses = membreses;
    }

    public TypeCommissionDTO getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(TypeCommissionDTO typeCommission) {
        this.typeCommission = typeCommission;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceArrete() {
        return referenceArrete;
    }

    public void setReferenceArrete(String referenceArrete) {
        this.referenceArrete = referenceArrete;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getMembreId() {
        return membreId;
    }

    public void setMembreId(Long membreId) {
        this.membreId = membreId;
    }

    public Long getTypeCommissionId() {
        return typeCommissionId;
    }

    public void setTypeCommissionId(Long typeCommissionId) {
        this.typeCommissionId = typeCommissionId;
    }

    public Long getTacheId() {
        return tacheId;
    }

    public void setTacheId(Long tacheId) {
        this.tacheId = tacheId;
    }

    public MembreDTO getMembre() {
        return membre;
    }

    public void setMembre(MembreDTO membre) {
        this.membre = membre;
    }

    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MembreCommissionDTO membreCommissionDTO = (MembreCommissionDTO) o;
        if (membreCommissionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), membreCommissionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MembreCommissionDTO{" +
            "id=" + getId() +
            ", avisDacId=" + getAvisDacId() +
            ", referenceArrete='" + getReferenceArrete() + "'" +
            ", poste='" + getPoste() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", membre=" + getMembreId() +
            ", typeCommission=" + getTypeCommissionId() +
            ", tache=" + getTacheId() +
            "}";
    }



}
