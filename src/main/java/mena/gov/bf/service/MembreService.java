package mena.gov.bf.service;

import mena.gov.bf.domain.Membre;
import mena.gov.bf.domain.TypeCommission;
import mena.gov.bf.repository.MembreRepository;
import mena.gov.bf.repository.TypeCommissionRepository;
import mena.gov.bf.service.dto.MembreDTO;
import mena.gov.bf.service.mapper.MembreMapper;
import mena.gov.bf.service.mapper.TypeCommissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * service Implementation for managing {@link Membre}.
 */
@Service
@Transactional
public class MembreService {

    private final Logger log = LoggerFactory.getLogger(MembreService.class);

    private final MembreRepository membreRepository;

    private final MembreMapper membreMapper;
    @Autowired
    TypeCommissionRepository typeCommissionRepository;



    public MembreService(MembreRepository membreRepository, MembreMapper membreMapper) {
        this.membreRepository = membreRepository;
        this.membreMapper = membreMapper;
    }

    /**
     * Save a membre.
     *
     * @param membreDTO the entity to save.
     * @return the persisted entity.
     */
    public MembreDTO save(MembreDTO membreDTO) {

        log.debug("Request to save Membre : {}", membreDTO);
        System.out.println("======typeCom1===========");
        System.out.println(membreDTO.getTypeCommission());
        System.out.println("======typeCom1===========");
        if (membreDTO.getTypeCommission().getId()==null){

          TypeCommission typeCommission= typeCommissionRepository.save(membreDTO.getTypeCommission());
          System.out.println("======typeCom===========");
          System.out.println(typeCommission);
          System.out.println("======typeCom===========");
            membreDTO.setTypeCommissionId(typeCommission.getId());


        }
        else {
            membreDTO.setTypeCommissionId(membreDTO.getTypeCommission().getId());
        }

        Membre membre = membreMapper.toEntity(membreDTO);
        membre = membreRepository.save(membre);
        return membreMapper.toDto(membre);
    }

    /**
     * Get all the membres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MembreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Membres");
        List<MembreDTO> membreDTOS = membreRepository.findAll()
            .stream()
            .map(membreMapper::toDto)
            .filter(membreDTO -> membreDTO.isDeleted() != null && !membreDTO.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(membreDTOS, pageable, membreDTOS.size());
    }


    /**
     * Get one membre by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MembreDTO> findOne(Long id) {
        log.debug("Request to get Membre : {}", id);
        return membreRepository.findById(id)
            .map(membreMapper::toDto);
    }

    /**
     * Delete the membre by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Membre : {}", id);
        membreRepository.deleteById(id);
    }
   public List<MembreDTO> findAllMembreWithoutPage() {
        List<MembreDTO> membreDTOS = membreRepository.findAll().stream()
            .filter(value -> value.isDeleted() != null && !value.isDeleted()).map(membreMapper::toDto)
            .collect(Collectors.toList());
        return membreDTOS;
    }

   public List<MembreDTO> updateAall(List<MembreDTO> membreDTOS){
       membreDTOS.forEach(membreDTO -> membreDTO.setDeleted(true));
       membreRepository.saveAll(membreDTOS.stream().map(membreMapper::toEntity).collect(Collectors.toList()));
       List<MembreDTO> membreDTOS1=membreRepository.findAll().stream().map(membreMapper::toDto).filter(membreDTO ->
           membreDTO.isDeleted()!=null && !membreDTO.isDeleted()).collect(Collectors.toList());
       return membreDTOS;
   }
   public List<MembreDTO> findMembreByUnite (long uniteId){
        return membreRepository.findAll().stream().filter(membre -> membre.getDirectionId() != null && membre.getDirectionId().equals(uniteId))
            .map(membreMapper::toDto).collect(Collectors.toList());
   }
   public List<MembreDTO> findMembreByTypeCommission(Long typeCommissionId) {
        return membreRepository.findAll().stream().filter(membre -> membre.getTypeCommissionId() != null &&
            membre.getTypeCommissionId().equals(typeCommissionId)).map(membreMapper::toDto).collect(Collectors.toList());
   }
}
