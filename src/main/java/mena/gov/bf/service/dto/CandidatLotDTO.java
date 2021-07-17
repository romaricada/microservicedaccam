package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link mena.gov.bf.domain.CandidatLot} entity.
 */
public class CandidatLotDTO implements Serializable {

    private Long id;

    @NotNull
    private Boolean estCandidat;

    @NotNull
    private Boolean soumissionnaire;

    @NotNull
    private Boolean attributaire;

    @NotNull
    private Boolean titulaire;

    @NotNull
    private Boolean dossierPaye;

    private Double nombrePoint;

    @NotNull
    private Boolean deleted;

    private Long lotId;

    private Long candidatId;

    private Long deliberationId;

    private Long avisDacId;

    private Long depouillementId;

    private Boolean isCaution;
    @NotNull
    private Boolean retenu = Boolean.FALSE;

    public Long getAvisDacId() {
        return avisDacId;
    }

    public void setAvisDacId(Long avisDacId) {
        this.avisDacId = avisDacId;
    }

    private Long contratId;
    
    private Double montantLuHt;
    
    private Double montantCorrigeHt;
    
    private Double montantLuTtc;
    
    private Double montantCorrigeTtc;
    
    private String rang;
    
    private String observation;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    private String nomStructure;

    private String adresse;

    private String libelle;

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

    private String email;


    public String getNomStructure() {
        return nomStructure;
    }

    public void setNomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    private CandidatDTO candidat = new CandidatDTO();

    private CandidatCautionLotDTO candidatCautionLotDTO = new CandidatCautionLotDTO();

    private List<CandidatCautionLotDTO> candidatCautionLots = new ArrayList<>();

    private List<CandidatDTO> candidatDTOList = new ArrayList<>();

    private List<LotDTO> lots = new ArrayList<>();

    public List<LotDTO> getLots() {
        return lots;
    }

    public List<CandidatDTO> getCandidatDTOList() {
        return candidatDTOList;
    }

    public void setCandidatDTOList(List<CandidatDTO> candidatDTOList) {
        this.candidatDTOList = candidatDTOList;
    }

    public void setLots(List<LotDTO> lots) {
        this.lots = lots;
    }

    public List<CandidatCautionLotDTO> getCandidatCautionLots() {
        return candidatCautionLots;
    }

    private LotDTO lot = new LotDTO();

    public void setCandidatCautionLots(List<CandidatCautionLotDTO> candidatCautionLots) {
        this.candidatCautionLots = candidatCautionLots;
    }

    public CandidatDTO getCandidat() {
        return candidat;
    }

    public void setCandidat(CandidatDTO candidat) {
        this.candidat = candidat;
    }

    public LotDTO getLot() {
        return lot;
    }

    public void setLot(LotDTO lot) {
        this.lot = lot;
    }

    public Boolean getRetenu() {
        return retenu;
    }

    public void setRetenu(Boolean retenu) {
        this.retenu = retenu;
    }

    private Long reclamationId;

    private List<PieceCandidatDTO> pieceCandidats = new ArrayList<>();

    public Boolean isEstCandidat() {
        return estCandidat;
    }

    public void setEstCandidat(Boolean estCandidat) {
        this.estCandidat = estCandidat;
    }

    public Boolean isSoumissionnaire() {
        return soumissionnaire;
    }

    public void setSoumissionnaire(Boolean soumissionnaire) {
        this.soumissionnaire = soumissionnaire;
    }

    public Boolean isAttributaire() {
        return attributaire;
    }

    public void setAttributaire(Boolean attributaire) {
        this.attributaire = attributaire;
    }

    public Boolean isTitulaire() {
        return titulaire;
    }

    public void setTitulaire(Boolean titulaire) {
        this.titulaire = titulaire;
    }

    public Boolean isDossierPaye() {
        return dossierPaye;
    }

    public void setDossierPaye(Boolean dossierPaye) {
        this.dossierPaye = dossierPaye;
    }

    public Double getNombrePoint() {
        return nombrePoint;
    }

    public void setNombrePoint(Double nombrePoint) {
        this.nombrePoint = nombrePoint;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public Long getDeliberationId() {
        return deliberationId;
    }

    public void setDeliberationId(Long deliberationId) {
        this.deliberationId = deliberationId;
    }

    public Long getDepouillementId() {
        return depouillementId;
    }

    public void setDepouillementId(Long depouillementId) {
        this.depouillementId = depouillementId;
    }

    public Long getReclamationId() {
        return reclamationId;
    }

    public void setReclamationId(Long reclamationId) {
        this.reclamationId = reclamationId;
    }

    public List<PieceCandidatDTO> getPieceCandidats() {
        return pieceCandidats;
    }

    public void setPieceCandidats(List<PieceCandidatDTO> pieceCandidats) {
        this.pieceCandidats = pieceCandidats;
    }

    public Boolean getCaution() {
        return isCaution;
    }

    public void setCaution(Boolean caution) {
        isCaution = caution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CandidatLotDTO candidatLotDTO = (CandidatLotDTO) o;
        if (candidatLotDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidatLotDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /**
     * Gets candidatCautionLotDTO.
     *
     * @return Value of candidatCautionLotDTO.
     */
    public CandidatCautionLotDTO getCandidatCautionLotDTO() {
        return candidatCautionLotDTO;
    }

    /**
     * Sets new candidatCautionLotDTO.
     *
     * @param candidatCautionLotDTO New value of candidatCautionLotDTO.
     */
    public void setCandidatCautionLotDTO(CandidatCautionLotDTO candidatCautionLotDTO) {
        this.candidatCautionLotDTO = candidatCautionLotDTO;
    }

    public Boolean getIsCaution() {
        return isCaution;
    }

    public void setIsCaution(Boolean isCaution) {
        this.isCaution = isCaution;
    }

    public Double getMontantLuHt() {
        return montantLuHt;
    }

    public void setMontantLuHt(Double montantLuHt) {
        this.montantLuHt = montantLuHt;
    }

    public Double getMontantCorrigeHt() {
        return montantCorrigeHt;
    }

    public void setMontantCorrigeHt(Double montantCorrigeHt) {
        this.montantCorrigeHt = montantCorrigeHt;
    }

    public Double getMontantLuTtc() {
        return montantLuTtc;
    }

    public void setMontantLuTtc(Double montantLuTtc) {
        this.montantLuTtc = montantLuTtc;
    }

    public Double getMontantCorrigeTtc() {
        return montantCorrigeTtc;
    }

    public void setMontantCorrigeTtc(Double montantCorrigeTtc) {
        this.montantCorrigeTtc = montantCorrigeTtc;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "CandidatLotDTO{" +
            "id=" + id +
            ", estCandidat=" + estCandidat +
            ", soumissionnaire=" + soumissionnaire +
            ", attributaire=" + attributaire +
            ", titulaire=" + titulaire +
            ", dossierPaye=" + dossierPaye +
            ", nombrePoint=" + nombrePoint +
            ", deleted=" + deleted +
            ", lotId=" + lotId +
            ", candidatId=" + candidatId +
            ", deliberationId=" + deliberationId +
            ", avisDacId=" + avisDacId +
            ", depouillementId=" + depouillementId +
            ", isCaution=" + isCaution +
            ", retenu=" + retenu +
            ", contratId=" + contratId +
            ", montantLuHt=" + montantLuHt +
            ", montantCorrigeHt=" + montantCorrigeHt +
            ", montantLuTtc=" + montantLuTtc +
            ", montantCorrigeTtc=" + montantCorrigeTtc +
            ", rang='" + rang + '\'' +
            ", observation='" + observation + '\'' +
            ", nomStructure='" + nomStructure + '\'' +
            ", adresse='" + adresse + '\'' +
            ", libelle='" + libelle + '\'' +
            ", email='" + email + '\'' +
            ", candidat=" + candidat +
            ", candidatCautionLotDTO=" + candidatCautionLotDTO +
            ", candidatCautionLots=" + candidatCautionLots +
            ", candidatDTOList=" + candidatDTOList +
            ", lots=" + lots +
            ", lot=" + lot +
            ", reclamationId=" + reclamationId +
            ", pieceCandidats=" + pieceCandidats +
            '}';
    }
}
