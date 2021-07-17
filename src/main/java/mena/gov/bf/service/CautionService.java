package mena.gov.bf.service;

import mena.gov.bf.domain.Caution;
import mena.gov.bf.domain.TypeCaution;
import mena.gov.bf.repository.CautionRepository;
import mena.gov.bf.repository.TypeCautionRepository;
import mena.gov.bf.service.dto.CautionDTO;
import mena.gov.bf.service.mapper.CautionMapper;
import mena.gov.bf.service.mapper.TypeCautionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link Caution}.
 */
@Service
@Transactional
public class CautionService {

    private final Logger log = LoggerFactory.getLogger(CautionService.class);

    private final CautionRepository cautionRepository;

    private final CautionMapper cautionMapper;
    private final TypeCautionRepository typeCautionRepository;
    private final TypeCautionMapper typeCautionMapper;

    public CautionService(CautionRepository cautionRepository, CautionMapper cautionMapper, TypeCautionRepository typeCautionRepository, TypeCautionMapper typeCautionMapper) {
        this.cautionRepository = cautionRepository;
        this.cautionMapper = cautionMapper;
        this.typeCautionRepository = typeCautionRepository;
        this.typeCautionMapper = typeCautionMapper;
    }

    /**
     * Save a caution.
     *
     * @param cautionDTO the entity to save.
     * @return the persisted entity.
     */
    public CautionDTO save(CautionDTO cautionDTO) {
        log.debug("Request to save Caution : {}", cautionDTO);
        TypeCaution typeCaution;
        if (cautionDTO.getTypeCautionId() == null) {
            typeCaution = typeCautionRepository.save(typeCautionMapper.toEntity(cautionDTO.getTypeCaution()));
            cautionDTO.setTypeCautionId(typeCaution.getId());
        }
        Caution caution = cautionMapper.toEntity(cautionDTO);
        caution = cautionRepository.save(caution);
        return cautionMapper.toDto(caution);
    }

    /**
     * Get all the cautions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CautionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cautions");
        List<CautionDTO> cautionDTOS = cautionRepository.findAll()
                .stream()
                .map(cautionMapper::toDto)
                .filter(cautionDTO -> cautionDTO.isDeleted() != null && !cautionDTO.isDeleted())
                .collect(Collectors.toList());
        return new PageImpl<>(cautionDTOS, pageable, cautionDTOS.size());
    }

    public List<CautionDTO> deleteAllCaution(List<CautionDTO> cautionDTOS) {
        cautionDTOS.forEach(cautionDTO -> {
            cautionDTO.setDeleted(true);
        });
        cautionRepository.deleteAll(cautionDTOS.stream().map(cautionMapper::toEntity).collect(Collectors.toList()));
        return cautionDTOS;
    }

    /**
     * Get one caution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CautionDTO> findOne(Long id) {
        log.debug("Request to get Caution : {}", id);
        return cautionRepository.findById(id)
                .map(cautionMapper::toDto);
    }

    /**
     * Delete the caution by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Caution : {}", id);
        cautionRepository.deleteById(id);
    }

    /**
     *
     * @param typeCaution
     * @return
     */
    public List<CautionDTO> findAllCautionByTypeCaution(Long typeCaution) {
        return cautionRepository.findAll().stream().filter(caution -> caution.getTypeCaution() != null
                && caution.getTypeCaution().getId().equals(typeCaution)).map(cautionMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param lotId
     * @return List<CautionDTO>
     */
    public List<CautionDTO> findAllCautionByLotId(Long lotId) {
        return cautionRepository.findAll().stream().filter(caution -> caution.getLot() != null && !caution.isDeleted()
                && caution.getLot().getId().equals(lotId)).map(cautionMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param lotId
     * @return List<CautionDTO>
     */
    public List<CautionDTO> findCautionByLot(Long lotId) {
        return cautionRepository.findAll().stream().filter(caution -> caution.getLot() != null && !caution.isDeleted() && caution.getLot().getId().equals(lotId)
        ).map(cautionMapper::toDto).collect(Collectors.toList());
    }
}
