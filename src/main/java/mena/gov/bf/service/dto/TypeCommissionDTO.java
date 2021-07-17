package mena.gov.bf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.TypeCommission} entity.
 */
public class TypeCommissionDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private Boolean deleted;

    private Long avisDacId;

    private String referenceArrete;

    private Long activiteId;

    private MembreCommissionDTO membreCommission;

    private List<MembreDTO> membres = new ArrayList<>();

    private List<MembreCommissionDTO> membreCommissions = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public MembreCommissionDTO getMembreCommission() {
        return membreCommission;
    }

    public void setMembreCommission(MembreCommissionDTO membreCommission) {
        this.membreCommission = membreCommission;
    }

    public List<MembreDTO> getMembres() {
        return membres;
    }

    public void setMembres(List<MembreDTO> membres) {
        this.membres = membres;
    }

    public List<MembreCommissionDTO> getMembreCommissions() {
        return membreCommissions;
    }

    public void setMembreCommissions(List<MembreCommissionDTO> membreCommissions) {
        this.membreCommissions = membreCommissions;
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

        TypeCommissionDTO typeCommissionDTO = (TypeCommissionDTO) o;
        if (typeCommissionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeCommissionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeCommissionDTO{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", deleted=" + deleted +
            ", membreCommission=" + membreCommission +
            ", membres=" + membres +
            ", avisDacId=" + getAvisDacId() +
            '}';
    }

    public String getReferenceArrete() {
        return referenceArrete;
    }

    public void setReferenceArrete(String referenceArrete) {
        this.referenceArrete = referenceArrete;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }
}
