package mena.gov.bf.service;

import mena.gov.bf.domain.Reception;
import mena.gov.bf.repository.LotRepository;
import mena.gov.bf.repository.ReceptionRepository;
import mena.gov.bf.service.dto.LotDTO;
import mena.gov.bf.service.dto.ReceptionDTO;
import mena.gov.bf.service.mapper.LotMapper;
import mena.gov.bf.service.mapper.ReceptionMapper;
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
 * service Implementation for managing {@link Reception}.
 */
@Service
@Transactional
public class ReceptionService {

    private final Logger log = LoggerFactory.getLogger(ReceptionService.class);

    private final ReceptionRepository receptionRepository;

    private final ReceptionMapper receptionMapper;

    private final LotRepository lotRepository;

    private final LotMapper lotMapper;

    public ReceptionService(ReceptionRepository receptionRepository, ReceptionMapper receptionMapper, LotRepository lotRepository, LotMapper lotMapper) {
        this.receptionRepository = receptionRepository;
        this.receptionMapper = receptionMapper;
        this.lotRepository = lotRepository;
        this.lotMapper = lotMapper;
    }

    /**
     * Save a reception.
     *
     * @param receptionDTO the entity to save.
     * @return the persisted entity.
     */
    public ReceptionDTO save(ReceptionDTO receptionDTO) {
        log.debug("Request to save Reception : {}", receptionDTO);
        Reception reception = receptionMapper.toEntity(receptionDTO);
        reception = receptionRepository.save(reception);
        return receptionMapper.toDto(reception);
    }

    /**
     * Get all the receptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Receptions");
        List<ReceptionDTO> receptionDTOS = receptionRepository.findAll()
            .stream()
            .map(receptionMapper::toDto)
            .filter(receptionDTO-> receptionDTO.isDeleted() != null && !receptionDTO.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(receptionDTOS, pageable, receptionDTOS.size());
    }


    /**
     * Get one reception by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReceptionDTO> findOne(Long id) {
        log.debug("Request to get Reception : {}", id);
        return receptionRepository.findById(id)
            .map(receptionMapper::toDto);
    }

    /**
     * Delete the reception by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Reception : {}", id);
        receptionRepository.deleteById(id);
    }


    public List<ReceptionDTO> updateAall(List<ReceptionDTO> receptionDTOS){
      receptionDTOS.forEach(receptionDTO -> {
            receptionDTO.setDeleted(true);
        });
        receptionRepository.saveAll(receptionDTOS.stream().map(receptionMapper::toEntity).collect(Collectors.toList()));
        List<ReceptionDTO> receptionDTOS1=receptionRepository.findAll().stream().map(receptionMapper::toDto).filter ( receptionDTO ->
            receptionDTO.isDeleted()!=null && !receptionDTO.isDeleted()).collect(Collectors.toList());
        return receptionDTOS;
    }




/*
    public List<ReceptionDTO> findReceptionByNumeroDac(Long exerciceId,Long aviDacId) {
        return receptionRepository.findAll().stream()
            .filter(reception ->  !reception.isDeleted() && reception.getAvisDac().getExerciceId().equals(exerciceId) && reception.getAvisDac().getId().equals(aviDacId))
            .map(receptionMapper::toDto)
            .collect(Collectors.toList());
    }*/

   /* public List<ReceptionDTO> findReceptionByAvisDac(Long avisDacId) {
        System.out.println("================avisDacId==============" +avisDacId);
        List<ReceptionDTO> receptionDTOS =receptionRepository.findAll().stream()
            .map(receptionMapper::toDto)
            .filter(receptionDTO -> !receptionDTO.isDeleted() && receptionDTO.getAvisDac().getId().equals(avisDacId))
            .collect(Collectors.toList());
        if (!receptionDTOS.isEmpty()){
            return receptionDTOS;
        }
        return null;
    }
*/
   public List<ReceptionDTO> findRecpetionByDac(Long avisDacId){

       List<ReceptionDTO> receptionDTOS = receptionRepository.findAll().stream().filter(reception -> !reception.isDeleted()
           && reception.getAvisDacId()!=null && reception.getAvisDacId().equals(avisDacId))
           .map(receptionMapper::toDto).collect(Collectors.toList());
       log.debug("========reception{}",receptionDTOS);
       return receptionDTOS;
   }


    public List<ReceptionDTO> findReceptionByExercice(Long avisDacId) {
        return receptionRepository.findAll().stream()
            .filter(reception ->  !reception.isDeleted() && reception.getAvisDacId().equals(avisDacId))
            .map(receptionMapper::toDto)
            .collect(Collectors.toList());
    }



}
