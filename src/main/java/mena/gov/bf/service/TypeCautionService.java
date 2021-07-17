package mena.gov.bf.service;

import mena.gov.bf.domain.TypeCaution;
import mena.gov.bf.repository.TypeCautionRepository;
import mena.gov.bf.service.dto.TypeCautionDTO;
import mena.gov.bf.service.mapper.TypeCautionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * service Implementation for managing {@link TypeCaution}.
 */
@Service
@Transactional
public class TypeCautionService {

    private final Logger log = LoggerFactory.getLogger(TypeCautionService.class);

    private final TypeCautionRepository typeCautionRepository;

    private final TypeCautionMapper typeCautionMapper;

    public TypeCautionService(TypeCautionRepository typeCautionRepository, TypeCautionMapper typeCautionMapper) {
        this.typeCautionRepository = typeCautionRepository;
        this.typeCautionMapper = typeCautionMapper;
    }

    /**
     * Save a typeCaution.
     *
     * @param typeCautionDTO the entity to save.
     * @return the persisted entity.
     */
    public TypeCautionDTO save(TypeCautionDTO typeCautionDTO) {
        log.debug("Request to save TypeCaution : {}", typeCautionDTO);
        TypeCaution typeCaution = typeCautionMapper.toEntity(typeCautionDTO);
        typeCaution = typeCautionRepository.save(typeCaution);
        return typeCautionMapper.toDto(typeCaution);
    }

    /**
     * Get all the typeCautions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeCautionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeCautions");
        return typeCautionRepository.findAll(pageable)
            .map(typeCautionMapper::toDto);
    }


    /**
     * Get one typeCaution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TypeCautionDTO> findOne(Long id) {
        log.debug("Request to get TypeCaution : {}", id);
        return typeCautionRepository.findById(id)
            .map(typeCautionMapper::toDto);
    }

    /**
     * Delete the typeCaution by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TypeCaution : {}", id);
        typeCautionRepository.deleteById(id);
    }
}
