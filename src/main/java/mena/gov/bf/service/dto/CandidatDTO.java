package mena.gov.bf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Candidat} entity.
 */
public class CandidatDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomStructure;

    private String adresse;

    private String email;

    private Long avisdacId;

    @NotNull
    private Boolean deleted;

    private List<CandidatLotDTO> soumisionniares = new ArrayList<>();

    private List<LotDTO> lots = new ArrayList<>();

    public List<LotDTO> getLots() {return lots;}

    public void setLots(List<LotDTO> lots) {this.lots = lots;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomStructure() {
        return nomStructure;
    }

    public void setNomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<CandidatLotDTO> getSoumisionniares() {
        return soumisionniares;
    }

    public void setSoumisionniares(List<CandidatLotDTO> soumisionniares) {
        this.soumisionniares = soumisionniares;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CandidatDTO candidatDTO = (CandidatDTO) o;
        if (candidatDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidatDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CandidatDTO{" +
            "id=" + getId() +
            ", nomStructure='" + getNomStructure() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", email='" + getEmail() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", CandidatLotDTO='" + getSoumisionniares() + "'" +
            ", lot1='" + getLots() + "'" +
            ", candidatlot='" + getSoumisionniares() + "'" +
            "}";
    }

    /**
     * Sets new avisdacId.
     *
     * @param avisdacId New value of avisdacId.
     */
    public void setAvisdacId(Long avisdacId) {
        this.avisdacId = avisdacId;
    }

    /**
     * Gets avisdacId.
     *
     * @return Value of avisdacId.
     */
    public Long getAvisdacId() {
        return avisdacId;
    }
}
