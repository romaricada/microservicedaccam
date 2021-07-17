package mena.gov.bf.service.dto;
import mena.gov.bf.domain.Workflow;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.TacheWorkflow} entity.
 */
public class TacheWorkflowDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Boolean deleted;

    private Long tacheId;

    private Long workflowId;

    private String workflowLibelle;

    private String etat;

    private String tacheLibelle;

    private String description;

    private String dateCreation;

    private WorkflowDTO workflow = new WorkflowDTO();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getTacheId() {
        return tacheId;
    }

    public void setTacheId(Long tacheId) {
        this.tacheId = tacheId;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowLibelle() {
        return workflowLibelle;
    }

    public void setWorkflowLibelle(String workflowLibelle) {
        this.workflowLibelle = workflowLibelle;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTacheLibelle() {
        return tacheLibelle;
    }

    public void setTacheLibelle(String tacheLibelle) {
        this.tacheLibelle = tacheLibelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TacheWorkflowDTO tacheWorkflowDTO = (TacheWorkflowDTO) o;
        if (tacheWorkflowDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tacheWorkflowDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TacheWorkflowDTO{" +
            "id=" + id +
            ", date=" + date +
            ", deleted=" + deleted +
            ", tacheId=" + tacheId +
            ", workflowId=" + workflowId +
            ", workflowLibelle='" + workflowLibelle + '\'' +
            ", etat='" + etat + '\'' +
            ", tacheLibelle='" + tacheLibelle + '\'' +
            ", description='" + description + '\'' +
            ", dateCreation='" + dateCreation + '\'' +
            ", workflow=" + workflow +
            '}';
    }

    public WorkflowDTO getWorkflow() {
        return workflow;
    }

    public void setWorkflow(WorkflowDTO workflow) {
        this.workflow = workflow;
    }
}
