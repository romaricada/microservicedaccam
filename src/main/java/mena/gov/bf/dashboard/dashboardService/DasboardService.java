package mena.gov.bf.dashboard.dashboardService;

import mena.gov.bf.beans.Activite;
import mena.gov.bf.beans.Contentieux;
import mena.gov.bf.beans.Contrat;
import mena.gov.bf.beans.ExerciceBudgetaire;
import mena.gov.bf.dashboard.entity.Dashboard;
import mena.gov.bf.dashboard.entity.LotInfoDTO;
import mena.gov.bf.domain.AvisDac;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.domain.enumeration.Etat;
import mena.gov.bf.domain.enumeration.EtatMarche;
import mena.gov.bf.proxies.ActiviteRepository;
import mena.gov.bf.proxies.ContentieuxRepository;
import mena.gov.bf.proxies.ContratRepository;
import mena.gov.bf.proxies.ExerciceBudgetaireRepository;
import mena.gov.bf.repository.AvisDacRepository;
import mena.gov.bf.repository.LotRepository;
import mena.gov.bf.service.CandidatLotService;
import mena.gov.bf.service.LotService;
import mena.gov.bf.service.TacheService;
import mena.gov.bf.service.dto.AvisDacDTO;
import mena.gov.bf.service.dto.CandidatLotDTO;
import mena.gov.bf.service.dto.LotDTO;
import mena.gov.bf.service.dto.TacheDTO;
import mena.gov.bf.service.mapper.AvisDacMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DasboardService {
    private ActiviteRepository activiteRepository;

    private final ExerciceBudgetaireRepository exerciceBudgetaireRepository;

    private final TacheService tacheService;

    private final CandidatLotService candidatLotService;

    private final ContratRepository contratRepository;

    private final ContentieuxRepository contentieuxRepository;
    @Autowired
    private LotService lotService;

    @Autowired
    private AvisDacRepository avisDacRepository;

    @Autowired
    private AvisDacMapper avisDacMapper;

    private final Logger log = LoggerFactory.getLogger( DasboardService.class );
    public DasboardService(ActiviteRepository activiteRepository, ExerciceBudgetaireRepository exerciceBudgetaireRepository, TacheService tacheService, CandidatLotService candidatLotService, ContratRepository contratRepository, ContentieuxRepository contentieuxRepository) {
        this.activiteRepository = activiteRepository;
        this.exerciceBudgetaireRepository = exerciceBudgetaireRepository;
        this.tacheService = tacheService;
        this.candidatLotService = candidatLotService;
        this.contratRepository = contratRepository;
        this.contentieuxRepository = contentieuxRepository;
    }


    @Transactional //(readOnly = false)
    public Dashboard getCurrentTask(Long idExercice) {
        List<String> labels = new ArrayList<>();
        List<String> colors = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
       // ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        List<AvisDacDTO> avisDacs = avisDacRepository.findByExerciceId(idExercice)
            .stream().map(avisDacMapper::toDto).peek(avisDac -> {
                avisDac.setEtatAvancement(calculateExecuteStep(avisDac.getId()));
                avisDac.setLotInfoDTOS(getLoInfoListByActivite(avisDac.getId()));
                avisDac.setTaches(
                    tacheService.findTacheByCriteria(avisDac.getId(), "ALL", "byAvisDac").stream().sorted(Comparator.comparing(TacheDTO::getDateDebut)).collect(Collectors.toList())
                );

                if(ifActiviteTacheIsTerminer(avisDac.getId())) {
                    avisDac.setEtatMarche(EtatMarche.TERMINE);
                }
                if(ifActiviteTacheIsEncours(avisDac.getId())) {
                    avisDac.setEtatMarche(EtatMarche.ENCOURS);
                }
                if(ifActiviteHasContencieu(avisDac.getId())) {
                    avisDac.setEtatMarche(EtatMarche.CONFLIT);
                }
                if(ifActiviteIsResilier(avisDac.getId())) {
                    avisDac.setEtatMarche(EtatMarche.RESILIE);
                }

                }).collect(Collectors.toList());

        Dashboard acceuil = new Dashboard();
        if(!avisDacs.isEmpty()) {
            acceuil.setAllAvisDac(avisDacs);
            List<AvisDacDTO> avisDac1 = avisDacs.stream().filter(avisDacDTO -> ifActiviteTacheIsTerminer(avisDacDTO.getId())).collect(Collectors.toList());
            List<AvisDacDTO> avisDac2 = avisDacs.stream().filter(avisDacDTO -> ifActiviteTacheIsEncours(avisDacDTO.getId())).collect(Collectors.toList());
            List<AvisDacDTO> avisDac3 = avisDacs.stream().filter(avisDacDTO -> ifActiviteHasContencieu(avisDacDTO.getId())).collect(Collectors.toList());
            List<AvisDacDTO> avisDac4 = avisDacs.stream().filter(avisDacDTO -> ifActiviteIsResilier(avisDacDTO.getId())).collect(Collectors.toList());

            acceuil.setAvisDacExecute(avisDac1);
            acceuil.setAvisDacEnCours(avisDac2);
            acceuil.setAvisDacEnLitige(avisDac3);
            acceuil.setAvisDacResilie(avisDac4);

            labels.add("Executé");
            labels.add("En cours");
            labels.add("En état de litige");
            labels.add("Résiliés");

            data.add(acceuil.getAvisDacExecute().size());
            data.add(acceuil.getAvisDacEnCours().size());
            data.add(acceuil.getAvisDacEnLitige().size());
            data.add(acceuil.getAvisDacResilie().size());

            colors.add("#28A745");
            colors.add("#17A2b8");
            colors.add("#FFC107");
            colors.add("#DC3545");

            acceuil.setLabels(labels);
            acceuil.setData(data);
            acceuil.setColors(colors);
        }
        log.debug("========================================================");
        log.debug("=================this.acceuil.getAllAvisDac().size()==================");
        log.debug("{}", acceuil.getAllAvisDac().size());
        return acceuil;
    }

    private boolean ifActiviteIsMarche(Long avisDacId) {
        List<LotDTO> lotList = lotService.findLotByAvisDac(avisDacId);
        int countOfLotWithContra;
        countOfLotWithContra = lotList.stream().filter(lotDTO -> getLotContra(lotDTO.getId()).getId() != null).collect(Collectors.toList()).size();
        return countOfLotWithContra != 0;
    }

    private boolean ifActiviteTacheIsTerminer(Long avisDacId) {
        int nbrAllTache = tacheService.findTacheByCriteria(avisDacId, "ALL", "byAvisDac").size();
        int nbrAllTAcheTminer = tacheService.findTacheByCriteria(avisDacId, Etat.TERMINE.toString(), "byAvisDac").size();
        return (nbrAllTache == nbrAllTAcheTminer) && nbrAllTache != 0;
    }


    private boolean ifActiviteTacheIsEncours(Long avisDacId) {
        int nbrAllTAcheEnCours = tacheService.findTacheByCriteria(avisDacId, Etat.ENCOURS.toString(), "byAvisDac").size();
        int nbrAllTAcheEnInitial = tacheService.findTacheByCriteria(avisDacId, Etat.INITIAL.toString(), "byAvisDac").size();
        return nbrAllTAcheEnCours != 0 || nbrAllTAcheEnInitial != 0;
    }

    private boolean ifActiviteHasContencieu(Long avisDacId) {
        List<LotDTO> lotList = lotService.findLotByAvisDac(avisDacId);
        int nbreLotWithConentieux = (int) lotList.stream().filter(lotDTO -> ifLotHasContentieux(lotDTO.getId())).count();
        return nbreLotWithConentieux > 0;
    }

    private boolean ifActiviteIsResilier(Long avisDacId) {
        List<LotDTO> lotList = lotService.findLotByAvisDac(avisDacId);
        int nbreLotWithResilier = (int) lotList.stream().filter(lotDTO -> ifLotIsResilier(lotDTO.getId())).count();
        return nbreLotWithResilier > 0;
    }

    private List<LotInfoDTO> getLoInfoListByActivite(Long avisDacId) {
        List<LotDTO> lotList = lotService.findLotByAvisDac(avisDacId);
        log.debug("======= la liste des lot =====>");
        log.debug("{}", lotList);
        List<LotInfoDTO> lotInfos = new ArrayList<>();

        if (!lotList.isEmpty()){
            lotList.forEach(lotDTO -> {
                lotInfos.add(getLotInfo(lotDTO.getId()));
            });
        }
        return lotInfos;
    }

    private boolean ifLotHasContentieux(Long lotId) {
        boolean hasContentieux = false;
        Optional<Contrat> contrat = Optional.ofNullable(getLotContra(lotId));

        if (contrat.isPresent()) {
            if (contrat.get().getId() != null){
                List<Contentieux> contentieuxes = contentieuxRepository.getAllContentieuxesByContrat(contrat.get().getId());
                if(!contentieuxes.isEmpty()) {
                    hasContentieux = true;
                }
            }
        }
        return hasContentieux;
    }

    private boolean ifLotIsResilier(Long lotId) {
        boolean isResilier = false;
        Optional<Contrat> contrat = Optional.ofNullable(getLotContra(lotId));

        if (contrat.isPresent()) {
            if (contrat.get().getId() != null) {
                isResilier = contrat.get().getResilierContrat();
            }
        }
        return isResilier;
    }

    private Contrat getLotContra(Long lotId) {
        Contrat contrat1 = new Contrat();
        Optional<CandidatLotDTO> candidatLotDTO = Optional.ofNullable( candidatLotService.findAttributaireByLotCandidat( lotId ) );
        if (candidatLotDTO.isPresent()) {
            contrat1 = contratRepository.findContratByCandidatLot(candidatLotDTO.get().getId());
            log.debug("==========> le contra of Lot <==========");
            log.debug("{}", contrat1);
        }

        return contrat1;
    }

    private Double calculateExecuteStep(Long avisDacId) {
        double nbrAllTache = tacheService.findTacheByCriteria(avisDacId, "ALL", "byAvisDac").size();
        double nbrAllTAcheTminer = tacheService.findTacheByCriteria(avisDacId, Etat.TERMINE.toString(), "byAvisDac").size();
        double etatAvance = 0;
        if (nbrAllTache !=0 ) {
            etatAvance = (nbrAllTAcheTminer/nbrAllTache) * 100;
        }
        return etatAvance;
    }

    private LotInfoDTO getLotInfo(Long lotId) {
        Optional<LotDTO> lot = lotService.findOne(lotId);
        LotInfoDTO lotInfoDTO = new LotInfoDTO();
        lot.ifPresent(lotDTO -> lotInfoDTO.setLibelle(lotDTO.getLibelle()));
        Optional<CandidatLotDTO> candidatLotDTO = Optional.ofNullable( candidatLotService.findAttributaireByLotCandidat( lotId) );
        if (candidatLotDTO.isPresent()) {
            Optional<Contrat> contrat = Optional.ofNullable(contratRepository.findContratByCandidatLot(candidatLotDTO.get().getId()));
            contrat.ifPresent(contrat1 -> lotInfoDTO.setDateSignatureContrat(contrat1.getDateSignature()));
            lotInfoDTO.setTitulaire(candidatLotDTO.get());
        }
        lotInfoDTO.setSituation(getSituationLot(lotId));
        return lotInfoDTO;
    }

    private String getSituationLot(Long lotId) {
        String situation = "En cours";
        if (ifLotHasContentieux(lotId)){
            situation = "En contentieux";
        } else if (ifLotIsResilier(lotId)) {
            situation = "Contrat resilié";
        }
        return situation;
    }

}
