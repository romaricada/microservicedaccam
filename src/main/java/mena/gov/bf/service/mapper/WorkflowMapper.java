package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.WorkflowDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Workflow} and its DTO {@link WorkflowDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkflowMapper extends EntityMapper<WorkflowDTO, Workflow> {


    @Mapping(target = "tacheWorkflows", ignore = true)
    @Mapping(target = "removeTacheWorkflows", ignore = true)
    Workflow toEntity(WorkflowDTO workflowDTO);

    default Workflow fromId(Long id) {
        if (id == null) {
            return null;
        }
        Workflow workflow = new Workflow();
        workflow.setId(id);
        return workflow;
    }
}
