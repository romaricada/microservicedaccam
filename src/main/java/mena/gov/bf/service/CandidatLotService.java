package mena.gov.bf.service;

import mena.gov.bf.domain.Candidat;
import mena.gov.bf.domain.CandidatLot;
import mena.gov.bf.domain.ReclamationCandidatLot;
import mena.gov.bf.repository.CandidatLotRepository;
import mena.gov.bf.repository.CandidatRepository;
import mena.gov.bf.repository.LotRepository;
import mena.gov.bf.service.dto.CandidatLotDTO;
import mena.gov.bf.service.dto.LotDTO;
import mena.gov.bf.service.mapper.CandidatLotMapper;
import mena.gov.bf.service.mapper.CandidatMapper;
import mena.gov.bf.service.mapper.LotMapper;
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
 * service Implementation for managing {@link CandidatLot}.
 */
@Service
@Transactional
public class CandidatLotService {

    private final Logger log = LoggerFactory.getLogger( CandidatLotService.class );

    private final CandidatLotRepository candidatLotRepository;

    private final CandidatLotMapper candidatLotMapper;

    private final CandidatMapper candidatMapper;

    private final CandidatRepository candidatRepository;

    @Autowired
    LotService lotService;

    @Autowired
    CandidatService candidatService;

    @Autowired
    LotRepository lotRepository;

    @Autowired
    LotMapper lotMapper;

    public CandidatLotService(CandidatLotRepository candidatLotRepository, CandidatLotMapper candidatLotMapper, CandidatMapper candidatMapper, CandidatRepository candidatRepository) {
        this.candidatLotRepository = candidatLotRepository;
        this.candidatLotMapper = candidatLotMapper;
        this.candidatMapper = candidatMapper;
        this.candidatRepository = candidatRepository;
    }

    /**
     * Save a candidatLot.
     *
     * @param candidatLotDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public CandidatLotDTO save(CandidatLotDTO candidatLotDTO) {
        log.debug( "Request to save CandidatLot : {}", candidatLotDTO );
         List<CandidatLotDTO> candidatLots = new ArrayList<>();
        Candidat candidat =new Candidat();
         if (candidatLotDTO.getCandidat().getId()==null){
              candidat = candidatMapper.toEntity( candidatLotDTO.getCandidat() );
             candidat = candidatRepository.save( candidat );
         }
         else {
             candidat = candidatMapper.toEntity(candidatLotDTO.getCandidat());
         }

        List<LotDTO> lotDTOS = candidatLotDTO.getLots();
        List<LotDTO> lotDTOS1 = new ArrayList<>();
        if (!lotDTOS.isEmpty()) {
            for (LotDTO lot : lotDTOS){
                List<CandidatLotDTO> candidatLots1 = candidatLotRepository.findAll().stream()
                        .map(candidatLotMapper::toDto).
                                filter(candidatLotDTO1 ->candidatLotDTO1.getId()!=null && candidatLotDTO1.getLot()
                                        .equals(lot)  && candidatLotDTO1.getCandidat().getId().equals(candidatLotDTO.getCandidat().getId())
                                        && candidatLotDTO.isDeleted() != null && !candidatLotDTO.isDeleted())
                        .collect(Collectors.toList());
                if(candidatLots1.isEmpty()){
                    lotDTOS1.add(lot);
                }
            }
        }
        if(lotDTOS1.isEmpty()){
            lotDTOS1 = candidatLotDTO.getLots();
        }
         if(!lotDTOS1.isEmpty()) {
            candidatLotDTO.setLots(new ArrayList<>());
            for (LotDTO lot : lotDTOS1) {
                    CandidatLotDTO candidatLotDTO1 = new CandidatLotDTO();
                    candidatLotDTO1.setDeleted(false);
                    candidatLotDTO1.setAttributaire(false);
                    if(!candidatLotDTO.isDossierPaye()){
                        candidatLotDTO1.setDossierPaye(false);
                    }
                    else {
                        candidatLotDTO1.setDossierPaye(true);
                    }
                    candidatLotDTO1.setEstCandidat(false);
                    candidatLotDTO1.setCaution(false);
                    candidatLotDTO1.setSoumissionnaire(false);
                    candidatLotDTO1.setTitulaire(false);
                    candidatLotDTO.setRetenu(false);
                    candidatLotDTO1.setLotId(lot.getId());
                    candidatLotDTO1.setCandidatId(candidat.getId());
                    candidatLots.add(candidatLotDTO1);
            }
        }
        if (!candidatLots.isEmpty()) {
            List<CandidatLot> candidatLotList = candidatLotRepository.saveAll( candidatLotMapper.toEntity(candidatLots) );
            return candidatLotMapper.toDto( candidatLotList.get(0) );
        }
        return null;
    }


    public CandidatLot save1(CandidatLot candidatLot) {
        return candidatLotRepository.save(candidatLot);
    }

    /**
     * Get all the candidatLots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidatLotDTO> findAll(Pageable pageable) {
        log.debug( "Request to get all CandidatLots" );
        List<CandidatLotDTO> candidatLotDTOS = candidatLotRepository.findAll().stream().map( candidatLotMapper::toDto )
            .filter( candidatLotDTO -> candidatLotDTO.isDeleted() != null && !candidatLotDTO.isDeleted() ).collect( Collectors.toList() );
      return new PageImpl<>(candidatLotDTOS , pageable, candidatLotDTOS.size() );
    }


    /**
     * Get one candidatLot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CandidatLotDTO> findOne(Long id) {
        log.debug( "Request to get CandidatLot : {}", id );
        return candidatLotRepository.findById( id )
            .map( candidatLotMapper::toDto );
    }

    public CandidatLotDTO findbyCandidat(Long lotId) {
        log.debug( "====================liste============", lotId);
        CandidatLot candidatLots = candidatLotRepository.findAllByLotIdAndDeletedIsFalse(lotId);
        CandidatLotDTO candidatLotDTO1 = candidatLotMapper.toDto(candidatLots);

        return candidatLotDTO1;
    }

    /**
     * Delete the candidatLot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug( "Request to delete CandidatLot : {}", id );
        candidatLotRepository.deleteById( id );
    }

    /**
     * Recuperation des candidats ou soumissionaire par lot
     */
    public List<CandidatLotDTO> findAllByLot(Long idLot) {
        return candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.isDeleted() != null && !candidatLot.isDeleted()
            && candidatLot.getLot() != null && candidatLot.getLot().getId().equals(idLot)
            && candidatLot.getDepouillement() == null ).map( candidatLotMapper::toDto ).collect( Collectors.toList() );
    }

    public List<CandidatLotDTO> findAllCandidatLot() {
        return candidatLotRepository.findAll().stream().filter( value -> value.isDeleted() != null && !value.isDeleted() )
            .map( candidatLotMapper::toDto ).collect( Collectors.toList() );
    }

    public List<CandidatLotDTO> updateaAll(List<CandidatLotDTO> candidatLotDTOS) {
        candidatLotDTOS.forEach( candidatLotDTO -> {
            candidatLotDTO.setDeleted( true );
        } );
        candidatLotRepository.saveAll( candidatLotDTOS.stream().map( candidatLotMapper::toEntity ).collect( Collectors.toList() ) );
        List<CandidatLotDTO> candidatLotDTOS1 = candidatLotRepository.findAll().stream().map( candidatLotMapper::toDto ).filter( candidatLotDTO ->
            candidatLotDTO.isDeleted() != null && !candidatLotDTO.isDeleted() ).collect( Collectors.toList() );
        return candidatLotDTOS1;
    }


    public List<CandidatLotDTO> findAllCandidatByActivite(Long activiteId) {
        return candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null && candidatLot.getLot().getAvisDac().getId().equals( activiteId )
            && candidatLot.getDepouillement() == null
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() )
            .map( candidatLotMapper::toDto ).collect( Collectors.toList() );
    }

    public List<CandidatLotDTO> findAllCandidatByLot(Long lotId) {
        return candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null && candidatLot.getLot().getId().equals( lotId )
            && candidatLot.getDepouillement() != null
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() )
            .map( candidatLotMapper::toDto ).collect( Collectors.toList() );
    }

    public List<CandidatLotDTO> findAllByCandidatId( Long candidatId){
        return candidatLotRepository.findAll().stream()
            .filter(candidatLot -> candidatLot.getCandidat()!=null && !candidatLot.isDeleted() && candidatLot.getCandidat().getId().equals(candidatId))
            .map(candidatLotMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<CandidatLotDTO> findSoumissionnaireByLot(Long lotId) {
        return this.findSoumissionnaireByLotWithoutDTO( lotId ).stream()
            .map( candidatLotMapper::toDto ).collect( Collectors.toList() );
    }

    public List<CandidatLot> findSoumissionnaireByLotWithoutDTO(Long lotId) {
        return candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null && candidatLot.getLot().getId().equals( lotId )
            && candidatLot.getDepouillement() != null
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted()
            && candidatLot.isSoumissionnaire() != null && candidatLot.isSoumissionnaire() )
            .collect( Collectors.toList() );
    }

    public List<CandidatLot> findSoumissionnaireByLotWithoutDTOS(List<Long> lotId) {
        List<CandidatLot> candidatLotList2 = new ArrayList<>();

        List<CandidatLot> candidatLots = candidatLotRepository.findAllByDeletedIsFalse();
        lotId.forEach(aLong -> {
            candidatLotList2.addAll(
                candidatLots.stream()
                    .filter(candidatLot -> candidatLot.getLot() != null && candidatLot.getLot().getId() != null && candidatLot.getLot().getId().equals(aLong)
                     /*&& candidatLot.getDepouillement() != null
                     && candidatLot.isDeleted() != null && !candidatLot.isDeleted()
                     && candidatLot.isSoumissionnaire() != null && !candidatLot.isSoumissionnaire()*/
                    )
                    .collect(Collectors.toList())
            );
        });
        return candidatLotList2;
    }

    public List<CandidatLot> findAllCandidatByLotAndDepouillement(Long activiteId, Long lotId, Long depouillementId) {
        return candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null && candidatLot.getLot().getId().equals( lotId )
            && candidatLot.getLot().getAvisDac().getId().equals( activiteId )
            && candidatLot.getDepouillement() != null && candidatLot.getDepouillement().getId().equals( depouillementId )
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() )
            .collect( Collectors.toList() );
    }

    public List<CandidatLotDTO> findAllSoummisionnaireByDepouillement(Long depouillementId) {
        return candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getDepouillement() != null
            && candidatLot.getDepouillement().getId().equals( depouillementId )
            && candidatLot.isSoumissionnaire()
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() )
            .map( candidatLotMapper::toDto ).collect( Collectors.toList() );
    }


    public CandidatLotDTO activateAttributaire(CandidatLotDTO candidatLotDTO) {
        List<CandidatLot> candidatLots = this.findSoumissionnaireByLotWithoutDTO( candidatLotDTO.getLotId() );
        candidatLots.forEach( candidatLot -> candidatLot.setAttributaire( false ) );
        candidatLotRepository.saveAll( candidatLots );
        CandidatLot candidatLot = candidatLotMapper.toEntity( candidatLotDTO );
        updateCandidat( candidatLot );
        candidatLot = candidatLotRepository.save( candidatLot );
        return candidatLotMapper.toDto( candidatLot );
    }

    public void updateCandidat(CandidatLot candidatLot) {
        if (!candidatLot.isSoumissionnaire()) {
            this.candidatLotRepository.saveAll( candidatLotRepository.findAll().stream()
                .peek( candidatLot1 -> candidatLot1.setAttributaire( false ) ).collect( Collectors.toList() ) );
        }
    }
    public CandidatLotDTO findAttributaireByLot(Long lotId, Long activiteId) {
        Optional<CandidatLotDTO> candidatLotDTO = candidatLotRepository.findAll().stream()
            .filter( candidatLot -> candidatLot.getLot() != null && candidatLot.getLot().getId().equals( lotId ) && candidatLot.getLot().getAvisDac()!=null &&
                candidatLot.getLot().getAvisDac().getId().equals(activiteId)
            && candidatLot.isAttributaire()!=null && candidatLot.isAttributaire() && candidatLot.isDeleted() != null && !candidatLot.isDeleted() )
            .map( candidatLotMapper::toDto )
            .findFirst();
        return candidatLotDTO.orElse( null );
    }
    public CandidatLotDTO findAttributaireByLotCandidat(Long lotId) {
        Optional<CandidatLotDTO> candidatLotDTO = candidatLotRepository.findAll().stream()
            .filter( candidatLot -> candidatLot.getLot() != null && candidatLot.getLot().getId().equals( lotId )
            && candidatLot.isAttributaire()!=null && candidatLot.isAttributaire() && candidatLot.isDeleted() != null && !candidatLot.isDeleted() )
            .map( candidatLotMapper::toDto )
            .findFirst();
        return candidatLotDTO.orElse( null );
    }

    public List<CandidatLotDTO> findlistAttributairebylot(Long activiteId, Long candidatId){
        return candidatLotRepository.findCandidatLotsByCandidatIdAndAttributaireIsTrueAndDeletedIsFalse(candidatId).stream()
            .filter(candidatLot -> candidatLot.getLot() != null && candidatLot.getLot().getAvisDac() != null && candidatLot.getLot().getAvisDac().getId().equals( activiteId ))
            .map(candidatLotMapper::toDto)
            .collect(Collectors.toList());
        //return candidatLotDTOS;
    }

    /* **** service wroten by mohams **** */
    public List<CandidatLotDTO> findListeAttributaireByActivite(Long avisDacId) {
        List<LotDTO> lotDTOS = lotService.findLotByAvisDac(avisDacId);
        List<CandidatLotDTO> candidatLots = new ArrayList<>();

/*    public List<CandidatLot> findAllCandidatDepouillement(Long idDepouillement, boolean etat) {
        if (etat) {
            return candidatLotRepository.findCandidatLotsByDepouillementIdAndDepouillementIsNotNullAndDeletedIsFalse( idDepouillement );
        } else {
            return candidatLotRepository.findCandidatLotsByDepouillementIdAndDepouillementIsNullAndDeletedIsFalse( idDepouillement );
        }
    } */
        lotDTOS.forEach(lotDTO -> candidatLots.addAll(candidatLotRepository
            .findAllByLotIdAndDeletedIsFalseAndAttributaireIsNotNullAndAttributaireIsTrue(lotDTO.getId()).stream().map(candidatLotMapper::toDto).collect(Collectors.toList())));
        return candidatLots;
    }


    public List<CandidatLot> findAllByLotIdAndDeletedIsFalseAndAttributaireIsNotNullAndAttributaireIsTrue (Long lotId) {
        return candidatLotRepository.findAllByLotIdAndDeletedIsFalseAndAttributaireIsNotNullAndAttributaireIsTrue(lotId);
    }

    /**
     * Inititié la list des candidats lot pour le depouillement.
     * @param avisDacId
     * @return List<CandidatLotDTO>
     */
    public List<CandidatLotDTO> initCandidatLot(Long candidatId, Long avisDacId) {
       // List<CandidatLotDTO> candidatLotDTOList = new ArrayList<>();
        /*List<LotDTO> lotDTOList = lotRepository.findAll().stream()
                .filter(lot -> lot.getAvisDac() != null && lot.getAvisDac().getId().equals(avisDacId))
                .map(lotMapper::toDto).collect(Collectors.toList());*/
       /* if (!lotDTOList.isEmpty()) {
            lotDTOList.forEach(lotDTO -> {
                CandidatLotDTO candidatLotDTO = new CandidatLotDTO();
                candidatLotDTO.setCaution(false);
                candidatLotDTO.setAttributaire(false);
                candidatLotDTO.setDeleted(false);
                candidatLotDTO.setSoumissionnaire(false);
                candidatLotDTO.setEstCandidat(true);
                candidatLotDTO.setTitulaire(false);
                candidatLotDTO.setDossierPaye(false);
                candidatLotDTO.setRetenu(false);
                candidatLotDTO.setLotId(lotDTO.getId());
                candidatLotDTO.setLot(lotDTO);
                candidatLotDTOList.add(candidatLotDTO);
            });
        }*/
        return this.candidatLotRepository.findAllByCandidatIdAndDeletedIsFalse(candidatId)
                .stream().filter(v -> v.getLot() != null
                        && v.getLot().getAvisDac() != null
                        && v.getLot().getAvisDac().getId().equals(avisDacId))
                .map(candidatLotMapper::toDto).collect(Collectors.toList());
    }

    public List<LotDTO> findAllLotByActivite(Long idActivite) {
        return candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.getLot() != null
            && candidatLot.getLot().getAvisDac().getId().equals( idActivite )
            && candidatLot.isDeleted() != null && !candidatLot.isDeleted() ).map( CandidatLot::getLot ).map( lotMapper::toDto ).collect( Collectors.toList());
    }


    public List<LotDTO> findLotByCandidat (Long candidatId, Long activiteId) {
        return candidatLotRepository.findAll().stream().filter(candidatLot -> candidatLot.getCandidat() != null
            && candidatLot.getCandidat().getId().equals(candidatId) && candidatLot.isDeleted() !=null &&
            !candidatLot.isDeleted() && candidatLot.getLot() != null && candidatLot.getLot().getAvisDac().getId().equals(activiteId)).map(CandidatLot::getLot).map(lotMapper::toDto).collect(Collectors.toList());
    }

    public Boolean findLotByCandidate (Long candidatId, Long activiteId, Long lotId) {
        List<CandidatLotDTO> candidatLotDTOS= candidatLotRepository.findAll().stream().filter(candidatLot -> candidatLot.getCandidat().getId().equals(candidatId) &&
        candidatLot.isDeleted() != null && candidatLot.getCandidat() != null && !candidatLot.isDeleted() && candidatLot.getLot() != null && candidatLot.getLot().getAvisDac().getId().equals(activiteId)
        && candidatLot.getLot().getId().equals(lotId)).map(candidatLotMapper::toDto).collect(Collectors.toList());
        CandidatLotDTO candidatLotDTO = candidatLotDTOS.get(0);
        return  candidatLotDTO.isDossierPaye();
    }
    //
    public List<LotDTO> findLotByCandidatAndAviDacId (Long candidatId, Long avisDacId) {
        return candidatLotRepository.findAll().stream().filter(candidatLot -> candidatLot.getCandidat() != null
            && candidatLot.getCandidat().getId().equals(candidatId) && candidatLot.isDeleted() !=null &&
            !candidatLot.isDeleted() && candidatLot.getLot() != null && candidatLot.getLot().getAvisDac().getId().equals(avisDacId)).map(CandidatLot::getLot).map(lotMapper::toDto).collect(Collectors.toList());
    }

    public Boolean findLotByCandidateAndAviDacIdAndLot (Long candidatId, Long avisDacId, Long lotId) {
        List<CandidatLotDTO> candidatLotDTOS= candidatLotRepository.findAll().stream().filter(candidatLot -> candidatLot.getCandidat().getId().equals(candidatId) &&
            candidatLot.isDeleted() != null && candidatLot.getCandidat() != null && !candidatLot.isDeleted() && candidatLot.getLot() != null && candidatLot.getLot().getAvisDac().getId().equals(avisDacId)
            && candidatLot.getLot().getId().equals(lotId)).map(candidatLotMapper::toDto).collect(Collectors.toList());
        CandidatLotDTO candidatLotDTO = candidatLotDTOS.get(0);
        return  candidatLotDTO.isDossierPaye();
    }


    public List<LotDTO> findAllLotByCandidatLot(Long idLot) {
        return candidatLotRepository.findAll().stream().filter( candidatLot -> candidatLot.isDeleted() != null && !candidatLot.isDeleted()
            && candidatLot.getLot() != null && candidatLot.getLot().getId().equals(idLot) && candidatLot.isAttributaire()!=null && candidatLot.isAttributaire()
            && candidatLot.getDepouillement() != null ).map( CandidatLot::getLot ).map(lotMapper::toDto ).collect( Collectors.toList() );
    }

    public List<CandidatLotDTO> findAllList(List<CandidatLotDTO> candidatLots) {

       /* List<CandidatLotDTO> candidatLotDTOs = candidatLotRepository.findAll().stream().map( candidatLotMapper::toDto ).filter( candidatLotDTO ->
            candidatLotDTO.isDeleted() != null && !candidatLotDTO.isDeleted() ).collect( Collectors.toList() );*/
        return candidatLotMapper.toDto(candidatLotRepository.saveAll( candidatLots.stream().map( candidatLotMapper::toEntity ).collect( Collectors.toList() ) ));
    }

//    public List<CandidatDTO> findAllCandidatAttributaireByActivite(Long activiteId) {
//       return candidatLotRepository.
//    }

    /**
     * Enregistrement des candidat Lot sans liste de lot.
     * @return
     */
   /* public CandidatLotDTO saveCandidatLot(CandidatLotDTO candidatLotDTO) {
        Candidat candidat = candidatRepository.save(candidatMapper.toEntity(candidatLotDTO.getCandidat()));

    }*/
}
