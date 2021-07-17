package mena.gov.bf.service;

import mena.gov.bf.domain.TypeCommission;
import mena.gov.bf.repository.MembreCommissionRepository;
import mena.gov.bf.repository.TypeCommissionRepository;
import mena.gov.bf.service.dto.MembreCommissionDTO;
import mena.gov.bf.service.dto.MembreDTO;
import mena.gov.bf.service.dto.TypeCommissionDTO;
import mena.gov.bf.service.mapper.MembreCommissionMapper;
import mena.gov.bf.service.mapper.TypeCommissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link TypeCommission}.
 */
@Service
@Transactional
public class TypeCommissionService {

    private final Logger log = LoggerFactory.getLogger(TypeCommissionService.class);

    private final TypeCommissionRepository typeCommissionRepository;

    private final TypeCommissionMapper typeCommissionMapper;

    @Autowired
    MembreCommissionRepository membreCommissionRepository;

    @Autowired
    MembreCommissionMapper membreCommissionMapper;

    public TypeCommissionService(TypeCommissionRepository typeCommissionRepository, TypeCommissionMapper typeCommissionMapper) {
        this.typeCommissionRepository = typeCommissionRepository;
        this.typeCommissionMapper = typeCommissionMapper;
    }

    /**
     * Save a typeCommission.
     *
     * @param typeCommissionDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeCommissionDTO save(TypeCommissionDTO typeCommissionDTO) {
        log.debug("Request to save TypeCommission : {}", typeCommissionDTO);
        List<MembreDTO> membreDTOS = typeCommissionDTO.getMembres();
        List<MembreCommissionDTO> membreCommissionDTOS = new ArrayList<>();
        final TypeCommission typeCommission = typeCommissionMapper.toEntity(typeCommissionDTO);

        if (!membreDTOS.isEmpty()) {
            membreDTOS.forEach(membreDTO -> {
                MembreCommissionDTO commissionDTO = new MembreCommissionDTO();
                if (typeCommissionDTO.getMembreCommission() != null){
                    commissionDTO.setId(typeCommissionDTO.getMembreCommission().getId());
                }
                commissionDTO.setMembreId(membreDTO.getId());
                commissionDTO.setTypeCommissionId(typeCommission.getId());
                commissionDTO.setAvisDacId(typeCommissionDTO.getAvisDacId());
                commissionDTO.setDeleted(false);
                commissionDTO.setReferenceArrete(typeCommissionDTO.getReferenceArrete());
                commissionDTO.setPoste(membreDTO.getPost());
                commissionDTO.setAvisDacId(typeCommissionDTO.getAvisDacId());

                membreCommissionDTOS.add(commissionDTO);
            });

            membreCommissionRepository.saveAll(membreCommissionMapper.toEntity(membreCommissionDTOS));
        }

        return typeCommissionMapper.toDto(typeCommission);
    }

    /**
     * Get all the typeCommissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeCommissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeCommissions");
        List<TypeCommissionDTO> typeCommissionDTOS = typeCommissionRepository.findAllByDeletedIsFalse().stream().map(typeCommissionMapper::toDto).collect(Collectors.toList());

        typeCommissionDTOS.forEach(typeCommissionDTO -> {
            typeCommissionDTO.setMembreCommissions(
                membreCommissionRepository.findAllByTypeCommissionIdAndDeletedIsFalse(typeCommissionDTO.getId())
                    .stream().map(membreCommissionMapper::toDto)
                    .collect(Collectors.toList()));
            if(!typeCommissionDTO.getMembreCommissions().isEmpty()) {
                MembreCommissionDTO membreCommission = typeCommissionDTO.getMembreCommissions().get(0);
                typeCommissionDTO.setReferenceArrete(membreCommission.getReferenceArrete());
                typeCommissionDTO.setAvisDacId(membreCommission.getAvisDacId());
                typeCommissionDTO.setAvisDacId(membreCommission.getAvisDacId());
            }
        });
        return new PageImpl<>(typeCommissionDTOS, pageable, typeCommissionDTOS.size());
    }


    /**
     * Get one typeCommission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeCommissionDTO> findOne(Long id) {
        log.debug("Request to get TypeCommission : {}", id);
        return typeCommissionRepository.findById(id)
            .map(typeCommissionMapper::toDto);
    }

    /**
     * Delete the typeCommission by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeCommission : {}", id);
        typeCommissionRepository.deleteById(id);
    }
    public List<TypeCommissionDTO> findAlltypeCommission() {
        List<TypeCommissionDTO> lisNature = typeCommissionRepository.findAll().stream()
            .filter(value -> value.isDeleted() != null && !value.isDeleted()).map(typeCommissionMapper::toDto)
            .collect(Collectors.toList());



        return lisNature;
    }

    public List<TypeCommissionDTO> findAllTypeCommissionByAvisDac (Long avisDacId) {
        return typeCommissionRepository.findAll().stream().filter(typeCommission -> typeCommission.getAvisDacId() != null &&
            typeCommission.getAvisDacId().equals(avisDacId)).map(typeCommissionMapper::toDto).collect(Collectors.toList());
    }
}
