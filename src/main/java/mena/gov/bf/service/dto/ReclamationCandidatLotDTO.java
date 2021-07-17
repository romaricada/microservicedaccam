package mena.gov.bf.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.ReclamationCandidatLot} entity.
 */
public class ReclamationCandidatLotDTO implements Serializable {

    private Long id;

    private String motif;



    private String structure;;

    private String decisionReclam;

    private String referenceReclam;

    private LocalDate dateReclam;

    private String description;

    private String libelle;






    private LocalDate date;

    private Boolean deleted;

    private Long reclamationId;

    private Long candidatLotId;

    private Long decisionId;

    private DecisionDTO decision = new DecisionDTO();

    private List<DecisionDTO> decisions = new ArrayList<>();

    private CandidatLotDTO candidatLot = new CandidatLotDTO();

    public List<DecisionDTO> getDecisions() {
        return decisions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getDecisionReclam() {
        return decisionReclam;
    }

    public void setDecisionReclam(String decisionReclam) {
        this.decisionReclam = decisionReclam;
    }

    public String getReferenceReclam() {
        return referenceReclam;
    }

    public void setReferenceReclam(String referenceReclam) {
        this.referenceReclam = referenceReclam;
    }

    public LocalDate getDateReclam() {
        return dateReclam;
    }

    public void setDateReclam(LocalDate dateReclam) {
        this.dateReclam = dateReclam;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getReclamationId() {
        return reclamationId;
    }

    public void setReclamationId(Long reclamationId) {
        this.reclamationId = reclamationId;
    }

    public Long getCandidatLotId() {
        return candidatLotId;
    }

    public void setCandidatLotId(Long candidatLotId) {
        this.candidatLotId = candidatLotId;
    }

    public Long getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(Long decisionId) {
        this.decisionId = decisionId;
    }

    public DecisionDTO getDecision() {
        return decision;
    }

    public void setDecision(DecisionDTO decision) {
        this.decision = decision;
    }

    public void setDecisions(List<DecisionDTO> decisions) {
        this.decisions = decisions;
    }

    public CandidatLotDTO getCandidatLot() {
        return candidatLot;
    }

    public void setCandidatLot(CandidatLotDTO candidatLot) {
        this.candidatLot = candidatLot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReclamationCandidatLotDTO reclamationCandidatLotDTO = (ReclamationCandidatLotDTO) o;
        if (reclamationCandidatLotDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reclamationCandidatLotDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReclamationCandidatLotDTO{" +
            "id=" + id +
            ", motif='" + motif + '\'' +
            ", structure='" + structure + '\'' +
            ", decisionReclam='" + decisionReclam + '\'' +
            ", referenceReclam='" + referenceReclam + '\'' +
            ", dateReclam=" + dateReclam +
            ", date=" + date +
            ", deleted=" + deleted +
            ", reclamationId=" + reclamationId +
            ", candidatLotId=" + candidatLotId +
            ", decisionId=" + decisionId +
            ", decision=" + decision +
            ", decisions=" + decisions +
            ", candidatLot=" + candidatLot +
            '}';
    }
}
