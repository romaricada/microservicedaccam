package mena.gov.bf.service;

import mena.gov.bf.domain.DelaiMessage;
import mena.gov.bf.domain.enumeration.TypeMessage;
import mena.gov.bf.repository.DelaiMessageRepository;
import mena.gov.bf.service.dto.DelaiMessageDTO;
import mena.gov.bf.service.mapper.DelaiMessageMapper;
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
 * service Implementation for managing {@link DelaiMessage}.
 */
@Service
@Transactional
public class DelaiMessageService {

    private final Logger log = LoggerFactory.getLogger(DelaiMessageService.class);

    private final DelaiMessageRepository delaiMessageRepository;

    private final DelaiMessageMapper delaiMessageMapper;

    public DelaiMessageService(DelaiMessageRepository delaiMessageRepository, DelaiMessageMapper delaiMessageMapper) {
        this.delaiMessageRepository = delaiMessageRepository;
        this.delaiMessageMapper = delaiMessageMapper;
    }

    /**
     * Save a delaiMessage.
     *
     * @param delaiMessageDTO the entity to save.
     * @return the persisted entity.
     */
    public DelaiMessageDTO save(DelaiMessageDTO delaiMessageDTO) {
        log.debug("Request to save DelaiMessage : {}", delaiMessageDTO);
        DelaiMessage delaiMessage = delaiMessageMapper.toEntity(delaiMessageDTO);
        delaiMessage = delaiMessageRepository.save(delaiMessage);
        return delaiMessageMapper.toDto(delaiMessage);
    }

    /**
     * Get all the delaiMessages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DelaiMessageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DelaiMessages");
        List<DelaiMessageDTO> delaiMessageDTOS = delaiMessageRepository.findAll().stream()
            .map(delaiMessageMapper::toDto)
            .filter(delaiMessageDTO -> delaiMessageDTO.isDeleted() != null
                && !delaiMessageDTO.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(delaiMessageDTOS, pageable, delaiMessageDTOS.size());
    }


    /**
     * Get one delaiMessage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DelaiMessageDTO> findOne(Long id) {
        log.debug("Request to get DelaiMessage : {}", id);
        return delaiMessageRepository.findById(id)
            .map(delaiMessageMapper::toDto);
    }

    /**
     * Delete the delaiMessage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DelaiMessage : {}", id);
        delaiMessageRepository.deleteById(id);
    }

    public List<DelaiMessageDTO> findByType() {
        return  delaiMessageRepository.findAll().stream().map(delaiMessageMapper::toDto).filter(delaiMessageDTO -> delaiMessageDTO.isDeleted() != null
            && !delaiMessageDTO.isDeleted() && delaiMessageDTO.getTypeMessage().equals(TypeMessage.DELAI) ).collect(Collectors.toList());
    }

    public List<DelaiMessageDTO> findByMessage() {
        return  delaiMessageRepository.findAll().stream().map(delaiMessageMapper::toDto).filter(delaiMessageDTO -> delaiMessageDTO.isDeleted() != null
            && !delaiMessageDTO.isDeleted() && !delaiMessageDTO.getTypeMessage().equals(TypeMessage.DELAI)).collect(Collectors.toList());
    }

    public List<DelaiMessageDTO> getAllDelaiMassage() {
        return delaiMessageMapper.toDto(delaiMessageRepository.findDelaiMessageByDeletedIsFalse());
    }
}
