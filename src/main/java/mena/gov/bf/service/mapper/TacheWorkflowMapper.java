package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.TacheWorkflowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TacheWorkflow} and its DTO {@link TacheWorkflowDTO}.
 */
@Mapper(componentModel = "spring", uses = {TacheMapper.class, WorkflowMapper.class})
public interface TacheWorkflowMapper extends EntityMapper<TacheWorkflowDTO, TacheWorkflow> {

    @Mapping(source = "tache.id", target = "tacheId")
    @Mapping(source = "workflow.id", target = "workflowId")
    @Mapping(source = "workflow", target = "workflow")

    @Mapping(source = "workflow.libelle", target = "workflowLibelle")
    @Mapping(source = "workflow.etat", target = "etat")

    @Mapping(source = "tache.libelle", target = "tacheLibelle")
    @Mapping(source = "tache.description", target = "description")
    @Mapping(source = "tache.dateCreation", target = "dateCreation")


    TacheWorkflowDTO toDto(TacheWorkflow tacheWorkflow);

    @Mapping(source = "tacheId", target = "tache")
    @Mapping(source = "workflowId", target = "workflow")
    TacheWorkflow toEntity(TacheWorkflowDTO tacheWorkflowDTO);

    default TacheWorkflow fromId(Long id) {
        if (id == null) {
            return null;
        }
        TacheWorkflow tacheWorkflow = new TacheWorkflow();
        tacheWorkflow.setId(id);
        return tacheWorkflow;
    }
}
