package mena.gov.bf.service;

import java.util.ArrayList;
import mena.gov.bf.repository.AvisDacRepository;
import mena.gov.bf.service.mapper.AvisDacMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import mena.gov.bf.domain.AvisDac;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.repository.LotRepository;
import mena.gov.bf.service.dto.AvisDacDTO;
import mena.gov.bf.service.dto.LotDTO;
import mena.gov.bf.service.mapper.LotMapper;

/**
 * service Implementation for managing {@link AvisDac}.
 */
@Service
@Transactional
public class AvisDacService {

    private final Logger log = LoggerFactory.getLogger(AvisDacService.class);

    private final AvisDacRepository avisDacRepository;

    private final AvisDacMapper avisDacMapper;

    private final LotRepository lotRepository;
    
    private final LotMapper lotMapper;

    public AvisDacService(AvisDacRepository avisDacRepository, AvisDacMapper avisDacMapper, LotRepository lotRepository, LotMapper lotMapper) {
        this.avisDacRepository = avisDacRepository;
        this.avisDacMapper = avisDacMapper;
        this.lotRepository = lotRepository;
        this.lotMapper = lotMapper;
    }
    

    /**
     * Save a candidat.
     *
     * @param avisDacDTO
     * @return the persisted entity.
     */
    public AvisDacDTO save(AvisDacDTO avisDacDTO) {
        log.debug("Request to save AvisDac : {}", avisDacDTO);
        AvisDac avisDac = avisDacMapper.toEntity(avisDacDTO);
        avisDac = avisDacRepository.save(avisDac);
        if (!avisDacDTO.getLots().isEmpty()) {
            List<Lot> savList = new ArrayList<>();
            lotRepository.deleteAll(this.actualiseList(avisDacDTO.getLots(), avisDac.getId()));
            for (LotDTO lotDTO : avisDacDTO.getLots()) {
                lotDTO.setAvisDacId(avisDac.getId());
                savList.add(lotMapper.toEntity(lotDTO));
            }
            this.lotRepository.saveAll(savList);
        }
        return avisDacMapper.toDto(avisDac);
    }

    /**
     * Actualiser la liste des lots associés à un avis dac.
     * @param newList
     * @param avisDacId
     * @return List<Lot>
     */
private List<Lot> actualiseList(Set<LotDTO> newList, Long avisDacId) {
    List<Lot> lots = lotRepository.findAll()
            .stream().filter(v -> v.getAvisDac()!=null && v.getAvisDac().getId().equals(avisDacId))
            .collect(Collectors.toList());
    List<Lot> suppList = new ArrayList<>();
    if (!lots.isEmpty()) {
        lots.stream().filter(lot -> (!newList.stream().filter(v -> v.getId() != null && v.getId().equals(lot.getId())).findFirst().isPresent()))
                .forEachOrdered(lot -> {
                    suppList.add(lot);
                });
    }
    return suppList;
}
    /**
     * Get all the avisDac.
     *
     * @param activiteId
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AvisDacDTO> findAll(Long activiteId) {
        log.debug("Request to get all AvisDac");
        return avisDacRepository.findAll().stream()
                .filter(va  -> va.getActiviteId() != null && va.getActiviteId().equals(activiteId))
                .map(avisDacMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get one candidat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AvisDacDTO> findOne(Long id) {
        log.debug("Request to get AvisDac : {}", id);
        return avisDacRepository.findById(id)
                .map(avisDacMapper::toDto);
    }

    /**
     * Delete the candidat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Candidat : {}", id);
        avisDacRepository.deleteById(id);
    }




    public List<AvisDacDTO> listAvisDacByExercice (Long exerciceId) {
        return avisDacRepository.findAll().stream().filter(avisDac ->
            avisDac.getExerciceId() != null && avisDac.getExerciceId().equals(exerciceId)).map(avisDacMapper::toDto).collect(Collectors.toList());
    }

   public List<AvisDacDTO> listAvisDacByActivite(Long activiteId){
        return avisDacRepository.findAll().stream().filter(avisDac ->
            avisDac.getActiviteId() != null && avisDac.getActiviteId().equals(activiteId)).map(avisDacMapper::toDto).collect(Collectors.toList());
    }


    public List<AvisDacDTO> updateAll (List<AvisDacDTO> avisDacDTOS) {
        avisDacDTOS.forEach(avisDacDTO -> avisDacDTO.setDeleted(true));

        avisDacRepository.saveAll(avisDacDTOS.stream().map(avisDacMapper::toEntity).collect(Collectors.toList()));
        List<AvisDacDTO> avisDacDTOS1 = avisDacRepository.findAll().stream().map(avisDacMapper::toDto).filter(avisDacDTO ->
            avisDacDTO.getDeleted() != null && !avisDacDTO.getDeleted()).collect(Collectors.toList());
        return avisDacDTOS1;
    }

}
