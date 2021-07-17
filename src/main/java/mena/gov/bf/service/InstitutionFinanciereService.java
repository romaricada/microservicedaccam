package mena.gov.bf.service;

import mena.gov.bf.domain.InstitutionFinanciere;
import mena.gov.bf.repository.InstitutionFinanciereRepository;
import mena.gov.bf.service.dto.InstitutionFinanciereDTO;
import mena.gov.bf.service.mapper.InstitutionFinanciereMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link InstitutionFinanciere}.
 */
@Service
@Transactional
public class InstitutionFinanciereService {

    private final Logger log = LoggerFactory.getLogger(InstitutionFinanciereService.class);

    private final InstitutionFinanciereRepository institutionFinanciereRepository;

    private final InstitutionFinanciereMapper institutionFinanciereMapper;

    public InstitutionFinanciereService(InstitutionFinanciereRepository institutionFinanciereRepository, InstitutionFinanciereMapper institutionFinanciereMapper) {
        this.institutionFinanciereRepository = institutionFinanciereRepository;
        this.institutionFinanciereMapper = institutionFinanciereMapper;
    }

    /**
     * Save a institutionFinanciere.
     *
     * @param institutionFinanciereDTO the entity to save.
     * @return the persisted entity.
     */
    public InstitutionFinanciereDTO save(InstitutionFinanciereDTO institutionFinanciereDTO) {
        log.debug("Request to save InstitutionFinanciere : {}", institutionFinanciereDTO);
        InstitutionFinanciere institutionFinanciere = institutionFinanciereMapper.toEntity(institutionFinanciereDTO);
        institutionFinanciere = institutionFinanciereRepository.save(institutionFinanciere);
        return institutionFinanciereMapper.toDto(institutionFinanciere);
    }

    /**
     * Get all the institutionFinancieres.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InstitutionFinanciereDTO> findAll() {
        log.debug("Request to get all InstitutionFinancieres");
        return institutionFinanciereRepository.findAll().stream()
            .map(institutionFinanciereMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one institutionFinanciere by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InstitutionFinanciereDTO> findOne(Long id) {
        log.debug("Request to get InstitutionFinanciere : {}", id);
        return institutionFinanciereRepository.findById(id)
            .map(institutionFinanciereMapper::toDto);
    }

    /**
     * Delete the institutionFinanciere by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InstitutionFinanciere : {}", id);

        institutionFinanciereRepository.deleteById(id);
    }

    public List<InstitutionFinanciereDTO> findInstitutionBancaire(){
        return institutionFinanciereRepository.findAll().stream().filter(institutionFinanciere -> institutionFinanciere.getLibelle()!=null).map(institutionFinanciereMapper::toDto).distinct().collect(Collectors.toList());
    }
}
