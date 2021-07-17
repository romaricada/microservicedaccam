package mena.gov.bf.reporting.service;

import mena.gov.bf.beans.*;
import mena.gov.bf.dashboard.entity.Dashboard;
import mena.gov.bf.dashboard.entity.LotInfoDTO;
import mena.gov.bf.domain.*;
import mena.gov.bf.domain.enumeration.Etat;
import mena.gov.bf.domain.enumeration.EtatMarche;
import mena.gov.bf.model.ConstanteGlobale;
import mena.gov.bf.proxies.ActiviteRepository;
import mena.gov.bf.proxies.ContentieuxRepository;
import mena.gov.bf.proxies.ContratRepository;
import mena.gov.bf.proxies.ExerciceBudgetaireRepository;
import mena.gov.bf.reporting.dto.AllAvisDacDTO;
import mena.gov.bf.reporting.dto.DeliberationRecuDTO;
import mena.gov.bf.reporting.dto.RecuDTO;
import mena.gov.bf.reporting.dto.RecuReclamationDTO;
import mena.gov.bf.reporting.utils.ReportService;
import mena.gov.bf.repository.*;
import mena.gov.bf.service.*;
import mena.gov.bf.service.dto.*;
import mena.gov.bf.service.mapper.AvisDacMapper;
import mena.gov.bf.service.mapper.CandidatLotMapper;
import mena.gov.bf.service.mapper.CandidatMapper;
import mena.gov.bf.service.mapper.LotMapper;
import mena.gov.bf.service.mapper.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ReportingService {
    private ActiviteRepository activiteRepository;

    private ExerciceBudgetaireRepository exerciceBudgetaireRepository;

    private TacheService tacheService;

    private CandidatLotService candidatLotService;

    private CandidatCautionLotRepository candidatCautionLotRepository;

    private CandidatCautionLotService candidatCautionLotService;

    private ContratRepository contratRepository;

    private ContentieuxRepository contentieuxRepository;

    private LotService lotService;

    private AvisDac avisDac;

    private ReceptionService receptionService;

    private LotRepository lotRepository;

    private final ReportService reportService;

    private DepouillementService depouillementService;


    @Autowired
    AvisDacRepository avisDacRepository;
    @Autowired
    AvisDacMapper avisDacMapper;
    @Autowired
    LotRepository getLotRepository;
    @Autowired
    LotMapper lotMapper;
    @Autowired
    CandidatLotRepository candidatLotRepository;
    @Autowired
    CandidatLotMapper candidatLotMapper;
    @Autowired
    DepouillementRepository depouillementRepository;
    @Autowired
    DepouillementMapper depouillementMapper;
    @Autowired
    DepouillementRepository getDepouillementRepository;
    @Autowired
    DeliberationRepository deliberationRepository;
    @Autowired
    DeliberationMapper deliberationMapper;

    CandidatMapper candidatMapper;
    @Autowired
    CandidatRepository candidatRepository;
    CautionRepository cautionRepository;
    @Autowired
    CautionMapper cautionMapper;
    @Autowired
    CandidatCautionLotMapper candidatCautionLotMapper;
    @Autowired
    ReclamationRepository reclamationRepository;
    @Autowired
    ReclamationMapper reclamationMapper;


    private final Logger log = LoggerFactory.getLogger(ReportingService.class);


    public ReportingService(ActiviteRepository activiteRepository, ReceptionService receptionService, CandidatCautionLotRepository candidatCautionLotRepository, CandidatCautionLotService candidatCautionLotService,
                            ExerciceBudgetaireRepository exerciceBudgetaireRepository, TacheService tacheService, CandidatLotService candidatLotService, ContratRepository contratRepository,
                            ContentieuxRepository contentieuxRepository, LotService lotService, ReportService reportService) {
        this.activiteRepository = activiteRepository;
        this.exerciceBudgetaireRepository = exerciceBudgetaireRepository;
        this.tacheService = tacheService;
        this.candidatLotService = candidatLotService;
        this.contratRepository = contratRepository;
        this.contentieuxRepository = contentieuxRepository;
        this.lotService = lotService;
        this.reportService = reportService;
        this.receptionService = receptionService;
        this.candidatCautionLotRepository = candidatCautionLotRepository;
        this.candidatCautionLotService = candidatCautionLotService;
    }


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

                if (ifActiviteTacheIsTerminer(avisDac.getId())) {
                    avisDac.setEtatMarche(EtatMarche.TERMINE);
                }
                if (ifActiviteTacheIsEncours(avisDac.getId())) {
                    avisDac.setEtatMarche(EtatMarche.ENCOURS);
                }
                if (ifActiviteHasContencieu(avisDac.getId())) {
                    avisDac.setEtatMarche(EtatMarche.CONFLIT);
                }
                if (ifActiviteIsResilier(avisDac.getId())) {
                    avisDac.setEtatMarche(EtatMarche.RESILIE);
                }

            }).collect(Collectors.toList());

        Dashboard acceuil = new Dashboard();
        if (!avisDacs.isEmpty()) {
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

        if (!lotList.isEmpty()) {
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
            if (contrat.get().getId() != null) {
                List<Contentieux> contentieuxes = contentieuxRepository.getAllContentieuxesByContrat(contrat.get().getId());
                if (!contentieuxes.isEmpty()) {
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
        Optional<CandidatLotDTO> candidatLotDTO = Optional.ofNullable(candidatLotService.findAttributaireByLotCandidat(lotId));
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
        if (nbrAllTache != 0) {
            etatAvance = (nbrAllTAcheTminer / nbrAllTache) * 100;
        }
        return etatAvance;
    }

    private LotInfoDTO getLotInfo(Long lotId) {
        Optional<LotDTO> lot = lotService.findOne(lotId);
        LotInfoDTO lotInfoDTO = new LotInfoDTO();
        lot.ifPresent(lotDTO -> lotInfoDTO.setLibelle(lotDTO.getLibelle()));
        Optional<CandidatLotDTO> candidatLotDTO = Optional.ofNullable(candidatLotService.findAttributaireByLotCandidat(lotId));
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
        if (ifLotHasContentieux(lotId)) {
            situation = "En contentieux";
        } else if (ifLotIsResilier(lotId)) {
            situation = "Contrat resilié";
        }
        return situation;
    }

    public List<AllAvisDacDTO> construireListAvisDacs(Long sessionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        log.debug("==========> Exercice  <==========");
        log.debug("{}", exercice.getAnnee());
        List<AllAvisDacDTO> allAvisdacByAnnee = new ArrayList<>();
        List<AvisDacDTO> avisDac1 = avisDacRepository.findAll().stream().filter(avisDac2 -> avisDac2.getExerciceId().equals(sessionId)).map(avisDacMapper::toDto).peek(avisDac -> {
            avisDac.setTaches(tacheService.findTacheByCriteria(avisDac.getId(), "ALL", "byActivite").stream().sorted(Comparator.comparing(TacheDTO::getDateDebut)).collect(Collectors.toList())
            );

            if (ifActiviteTacheIsTerminer(avisDac.getId())) {
                avisDac.setEtatMarche(EtatMarche.TERMINE);
            }
            if (ifActiviteTacheIsEncours(avisDac.getId())) {
                avisDac.setEtatMarche(EtatMarche.ENCOURS);
            }
            if (ifActiviteHasContencieu(avisDac.getId())) {
                avisDac.setEtatMarche(EtatMarche.CONFLIT);
            }
            if (ifActiviteIsResilier(avisDac.getId())) {
                avisDac.setEtatMarche(EtatMarche.RESILIE);
            }

        }).collect(Collectors.toList());

        avisDac1.forEach(avisDac -> {
            AllAvisDacDTO allAvisDacs1 = new AllAvisDacDTO();
            allAvisDacs1.setNomActivite(avisDac.getNumeroAvis() + " " + avisDac.getObjet());
            log.debug("==========> Nom de l'activite  <==========");
            log.debug("{}", avisDac.getNumeroAvis() + " " + avisDac.getObjet());

            allAvisDacs1.setObjet(avisDac.getObjet());
            log.debug("==========> Budget de l'activite  <==========");
            log.debug("{}", avisDac.getObjet());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateDepotOffre = avisDac.getDateDepotOffre().format(formatter);
            allAvisDacs1.setDateDepotOffre(dateDepotOffre);
            log.debug("==========> Date buttoire de l'activite  <==========");
            log.debug("{}", dateDepotOffre);

            String dateLancement = avisDac.getDateLancement().format(formatter);
            allAvisDacs1.setDateLancement(dateLancement);
            log.debug("==========> Date de lancement de l'activite  <==========");
            log.debug("{}", dateLancement);

            allAvisDacs1.setEtatMarche(avisDac.getEtatMarche());

            allAvisdacByAnnee.add(allAvisDacs1);

        });


        return allAvisdacByAnnee;
    }

    public byte[] imprimeAllActivite(Long sessionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        System.out.println("==========================TRAORE=================================");
        System.out.println(exercice);
        System.out.println("==========================TRAORE====================================");
        List<AllAvisDacDTO> listActivites = construireListAvisDacs(sessionId);
        HashMap<String, Object> parametres = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);

        parametres.put("EXERCICE", exercice.getAnnee().toString());
        parametres.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        log.debug("==========> ADADADADADADAD <=========={}", ConstanteGlobale.COPYRIGHT);
        log.debug("==========> Exercice dans la fonction d'impression  <==========");
        log.debug("{}", exercice.getAnnee());
        parametres.put("jourDelivre", jourDelivre);
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listActivites);
        return reportService.createReports("statistiqueMarche.jasper", datasource, parametres);
    }


    public List<AllAvisDacDTO> construireListActivitesEnCours(Long sessionId) {

        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        log.debug("==========> Exercice dans la fonction construireListActivitesEnCours <==========");
        log.debug("{}", exercice.getAnnee());
        List<AllAvisDacDTO> allAvisDacEnCours = new ArrayList<>();
        List<AvisDacDTO> avisDacEnCours = avisDacRepository.findAll().stream().filter(avisDac2 -> avisDac2.getExerciceId().equals(sessionId)).map(avisDacMapper::toDto).peek(avisDac -> {
            avisDac.setTaches(tacheService.findTacheByCriteria(avisDac.getId(), "ALL", "byActivite").stream().sorted(Comparator.comparing(TacheDTO::getDateDebut)).collect(Collectors.toList()));

            if (ifActiviteTacheIsEncours(avisDac.getId())) {
                avisDac.setEtatMarche(EtatMarche.ENCOURS);
            }
        }).collect(Collectors.toList());

        AllAvisDacDTO allAvisDacs2 = new AllAvisDacDTO();
        if (!avisDacEnCours.isEmpty()) {
            List<AvisDacDTO> avisDacs2 = avisDacEnCours.stream().filter(avisDac -> ifActiviteTacheIsEncours(avisDac.getId())).collect(Collectors.toList());

            allAvisDacs2.setActivitesEnCours(avisDacs2);
            avisDacs2.forEach(avisDac -> {
                AllAvisDacDTO allAvisDac2 = new AllAvisDacDTO();
                allAvisDac2.setNomActivite(avisDac.getNumeroAvis() + " " + avisDac.getObjet());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dateDepotoffre = avisDac.getDateDepotOffre().format(formatter);
                allAvisDac2.setDateDepotOffre(dateDepotoffre);

                String dateLancement = avisDac.getDateLancement().format(formatter);
                allAvisDac2.setDateLancement(dateLancement);
                allAvisDacEnCours.add(allAvisDac2);
            });
        }
        return allAvisDacEnCours;
    }


    public byte[] imprimeActivitiesEnCours(Long sessionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        List<AllAvisDacDTO> listActivitiesEnCours = construireListActivitesEnCours(sessionId);
        HashMap<String, Object> parametres = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);

        parametres.put("EXERCICE", exercice.getAnnee().toString());
        parametres.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        log.debug("==========> Exercice dans la fonction d'impression  <==========");
        log.debug("{}", exercice.getAnnee());
        parametres.put("jourDelivre", jourDelivre);
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listActivitiesEnCours);
        return reportService.createReports("statMarchesEnCours.jasper", datasource, parametres);
    }

    public List<AllAvisDacDTO> construireListAvisDacsTerminer(Long sessionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        log.debug("==========> Exercice  <==========");
        log.debug("{}", exercice.getAnnee());
        List<AllAvisDacDTO> allAvisDacByAnnee = new ArrayList<>();
        List<AvisDacDTO> avisDac1 = avisDacRepository.findAll().stream().filter(avisDac2 -> avisDac2.getExerciceId().equals(sessionId)).map(avisDacMapper::toDto).peek(avisDac -> {
            avisDac.setTaches(tacheService.findTacheByCriteria(avisDac.getId(), "ALL", "byActivite").stream().sorted(Comparator.comparing(TacheDTO::getDateDebut)).collect(Collectors.toList())
            );

            if (ifActiviteTacheIsTerminer(avisDac.getId())) {
                avisDac.setEtatMarche(EtatMarche.TERMINE);
            }
        }).collect(Collectors.toList());

        AllAvisDacDTO allAvisDacs = new AllAvisDacDTO();
        if (!avisDac1.isEmpty()) {
            List<AvisDacDTO> avisDacs3 = avisDac1.stream().filter(activite -> ifActiviteTacheIsTerminer(activite.getId())).collect(Collectors.toList());


            allAvisDacs.setActivitesExecute(avisDacs3);
            avisDac1.forEach(avisDacDTO -> {
                AllAvisDacDTO allActivites1 = new AllAvisDacDTO();
                allActivites1.setNomActivite(avisDacDTO.getNumeroAvis() + " " + avisDacDTO.getObjet());
/*
            allAvisDacs.setActivitesExecute(avisDac1);
            avisDac1.forEach(avisDacDTO -> {
                AllAvisDacDTO allActivites1 = new AllAvisDacDTO();
                allActivites1.setNomActivite(avisDacDTO.getNumeroAvis() + " " + avisDacDTO.getObjet());*/

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dateDepotOffre = avisDacDTO.getDateDepotOffre().format(formatter);
                allActivites1.setDateDepotOffre(dateDepotOffre);

                String dateLancement = avisDacDTO.getDateLancement().format(formatter);
                allActivites1.setDateLancement(dateLancement);
                allAvisDacByAnnee.add(allActivites1);

            });
        }

        return allAvisDacByAnnee;

    }

    public byte[] imprimeFinishedActivities(Long sessionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        List<AllAvisDacDTO> listFinishedActivities = construireListAvisDacsTerminer(sessionId);
        HashMap<String, Object> parametres = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);

        parametres.put("EXERCICE", exercice.getAnnee().toString());
        parametres.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        log.debug("==========> ADADADADADADAD <=========={}", ConstanteGlobale.COPYRIGHT);
        log.debug("==========> Exercice dans la fonction d'impression  <==========");
        log.debug("{}", exercice.getAnnee());
        parametres.put("jourDelivre", jourDelivre);
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listFinishedActivities);
        return reportService.createReports("statMarchesTerminer.jasper", datasource, parametres);
    }


    public List<AllAvisDacDTO> contruireListActivitesAyantLitige(Long sessionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        List<AllAvisDacDTO> allAvisdacsAyantLitige = new ArrayList<>();
        List<AvisDacDTO> avisdacsAyantLitige = avisDacRepository.findAll().stream().filter(avisDac2 -> avisDac2.getExerciceId().equals(sessionId)).map(avisDacMapper::toDto).peek(avisDac -> {
            avisDac.setTaches(tacheService.findTacheByCriteria(avisDac.getId(), "All", "byActivite").stream().sorted(Comparator.comparing(TacheDTO::getDateDebut)).collect(Collectors.toList()));

            if (ifActiviteHasContencieu(avisDac.getId())) {
                avisDac.setEtatMarche(EtatMarche.CONFLIT);
            }
        }).collect(Collectors.toList());

        AllAvisDacDTO allAvisDacs = new AllAvisDacDTO();
        if (!avisdacsAyantLitige.isEmpty()) {
            List<AvisDacDTO> avisdacs3 = avisdacsAyantLitige.stream().filter(avisDac -> ifActiviteHasContencieu(avisDac.getId())).collect(Collectors.toList());

            allAvisDacs.setActivitesEnLitige(avisdacs3);
            avisdacs3.forEach(avisDac -> {
                AllAvisDacDTO allActivite3 = new AllAvisDacDTO();
                allActivite3.setNomActivite(avisDac.getNumeroAvis() + " " + avisDac.getObjet());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dateDepotOffre = avisDac.getDateDepotOffre().format(formatter);
                allActivite3.setDateDepotOffre(dateDepotOffre);

                String dateLancement = avisDac.getDateLancement().format(formatter);
                allActivite3.setDateLancement(dateLancement);

                allAvisdacsAyantLitige.add(allActivite3);
            });
        }
        return allAvisdacsAyantLitige;
    }

    public byte[] imprimeActivitiesAyantLitige(Long sessionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        List<AllAvisDacDTO> listActivitiesAyantLitige = contruireListActivitesAyantLitige(sessionId);
        HashMap<String, Object> parametres = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);

        parametres.put("EXERCICE", exercice.getAnnee().toString());
        parametres.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        log.debug("==========> ADADADADADADAD <=========={}", ConstanteGlobale.COPYRIGHT);
        log.debug("==========> Exercice dans la fonction d'impression  <==========");
        log.debug("{}", exercice.getAnnee());
        parametres.put("jourDelivre", jourDelivre);
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listActivitiesAyantLitige);
        return reportService.createReports("statMarchesEnCours.jasper", datasource, parametres);
    }

    public List<AllAvisDacDTO> ContruireListMarcheAyantContratResilie(Long sessionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        List<AllAvisDacDTO> allAvisDacAyantContratResilie = new ArrayList<>();
        List<AvisDacDTO> avisdacAyantContratResilie = avisDacRepository.findAll().stream().filter(avisDac2 -> avisDac2.getExerciceId().equals(sessionId)).map(avisDacMapper::toDto).peek(avisDac -> {
            avisDac.setTaches(tacheService.findTacheByCriteria(avisDac.getId(), "All", "byActivite").stream().sorted(Comparator.comparing(TacheDTO::getDateDebut)).collect(Collectors.toList()));

            if (ifActiviteIsResilier(avisDac.getId())) {
                avisDac.setEtatMarche(EtatMarche.RESILIE);
            }
        }).collect(Collectors.toList());

        AllAvisDacDTO allAvisDacs4 = new AllAvisDacDTO();
        if (!avisdacAyantContratResilie.isEmpty()) {
            List<AvisDacDTO> avisDacs4 = avisdacAyantContratResilie.stream().filter(avisDac -> ifActiviteIsResilier(avisDac.getId())).collect(Collectors.toList());

            allAvisDacs4.setActivitesResilie(avisDacs4);
            avisDacs4.forEach(avisDac -> {
                AllAvisDacDTO allAvisDac4 = new AllAvisDacDTO();
                allAvisDac4.setNomActivite(avisDac.getNumeroAvis() + " " + avisDac.getObjet());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dateDepotOffre = avisDac.getDateDepotOffre().format(formatter);
                allAvisDac4.setDateDepotOffre(dateDepotOffre);

                String dateLancement = avisDac.getDateLancement().format(formatter);
                allAvisDac4.setDateLancement(dateLancement);
                allAvisDacAyantContratResilie.add(allAvisDac4);
            });
        }
        return allAvisDacAyantContratResilie;
    }


    public byte[] imprimeActivitiesAyantContratResilie(Long sessionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        List<AllAvisDacDTO> listAvisDacsAyantContratResilie = ContruireListMarcheAyantContratResilie(sessionId);
        HashMap<String, Object> parametres = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);

        parametres.put("EXERCICE", exercice.getAnnee().toString());
        parametres.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        log.debug("==========> ADADADADADADAD <=========={}", ConstanteGlobale.COPYRIGHT);
        log.debug("==========> Exercice dans la fonction d'impression  <==========");
        log.debug("{}", exercice.getAnnee());
        parametres.put("jourDelivre", jourDelivre);
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listAvisDacsAyantContratResilie);
        return reportService.createReports("statMarchesAyantContratResilie.jasper", datasource, parametres);
    }


    public List<RecuDTO> donneRecuPaiement(Long avisDacId, Long exerciceId, Long receptionId) {

        RecuDTO recuDTOs = new RecuDTO();
        List<RecuDTO> recuDTOList = new ArrayList<>();
        System.out.println("===============================1===============================");

        Optional<ReceptionDTO> receptions = receptionService.findOne(receptionId);

        List<AvisDac> avisDacs = avisDacRepository.findAll().stream().filter(avisDac1 -> avisDac1.getExerciceId().equals(exerciceId)).collect(Collectors.toList());


        System.out.println("=============== 2 lot1==================================");

        List<LotDTO> lots = lotService.findLotByAvisDac(avisDacId);
        System.out.println("================================ 2 lot recuperer ==============================");
        System.out.println(lots);
        System.out.println("==============================================================");

        if (!avisDacs.isEmpty()) {
            recuDTOs.setNomActivite(avisDacs.get(0).getNumeroAvis() + " " + avisDacs.get(0).getObjet());
        }

        if (receptions.isPresent()) {
            recuDTOs.setNom(receptions.get().getNom());
            recuDTOs.setPrenom(receptions.get().getPrenom());
            recuDTOs.setTelephone(receptions.get().getTelephone());
            recuDTOs.setEmail(receptions.get().getEmail());
            recuDTOs.setTypeDepot(receptions.get().getTypeReception());
            recuDTOs.setLieu(receptions.get().getLieu());
        }

        recuDTOList.add(recuDTOs);
        log.debug("=============recuDTOList ada=========={}", recuDTOList);

        return recuDTOList;
    }


    public byte[] imprimerRecu(Long avisDacId, Long exerciceId, Long receptionId) {
        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();
        System.out.println("==========================TRAORE=================================");
        System.out.println(exercice);
        System.out.println("==========================TRAORE====================================");
        List<RecuDTO> listDesRecuPaiement = donneRecuPaiement(avisDacId, exerciceId, receptionId);
        HashMap<String, Object> parametres = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);
        parametres.put("EXERCICE", exercice.getAnnee().toString());
        parametres.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        log.debug("==========> Exercice dans la fonction d'impression  <========== {}");
        log.debug("{}", exercice.getAnnee());
        parametres.put("jourDelivre", jourDelivre);
        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(listDesRecuPaiement);
        return reportService.createReports("recuPaiement.jasper", datasource, parametres);

    }


    public byte[] imprimeDac(AvisDacDTO avisDacDTOS) {

        List<Activite> activites = activiteRepository.listActivites().stream().filter(activite -> activite.getId() != null && activite.getId().equals(avisDacDTOS.getActiviteId())).collect(Collectors.toList());

        HashMap<String, Object> parametres = new HashMap<>();

        System.out.println("==========================Laurent====================================");
        System.out.println(avisDacDTOS);
        System.out.println("==========================Laurent====================================");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);
        parametres.put("jourDelivre", jourDelivre);
        parametres.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        parametres.put("nomActivite", activites.get(0).getNomActivite());
        List<AvisDacDTO> avisDacDTOS1 = new ArrayList<>();
        avisDacDTOS1.add(avisDacDTOS);

        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(avisDacDTOS1);
        return reportService.createReports("avisDac.jasper", datasource, parametres);
    }


    public byte[] imprimeDepouillemnt(List<DepouillementDTO> depouillementDTOS) {

        HashMap<String, Object> parametre = new HashMap<>();
        depouillementDTOS.forEach(depouillementDTO -> {
            depouillementDTO.setDescription(depouillementDTO.getDescription().replaceAll("<p>", ""));
            depouillementDTO.setDescription(depouillementDTO.getDescription().replaceAll("</p>", ""));


            depouillementDTO.getCandidats().forEach(candidatDTO -> {

                List<LotDTO> lots = new ArrayList<>();
                List<Double> montant = new ArrayList<>();
                List<String> institut = new ArrayList<>();


                candidatDTO.getSoumisionniares().forEach(candidatLotDTO -> {
                    candidatLotDTO.getCandidatCautionLots().forEach(candidatCautionLotDTO -> {

                        montant.add(candidatCautionLotDTO.getMontantCandidat());
                        institut.add(candidatCautionLotDTO.getInstitutionFinanciere());
                    });

                    lots.add(candidatLotDTO.getLot());

                });

                candidatDTO.setLots(lots);
                for (int i = 0; i < candidatDTO.getLots().size(); i++) {

                    candidatDTO.getLots().get(i).setMontantCandidat(montant.get(i));
                    candidatDTO.getLots().get(i).setInstitutionFinanciere(institut.get(i));

                }


            });
        });


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);
        System.out.println("==========================montant====================================");
        System.out.println(jourDelivre);
        System.out.println(ConstanteGlobale.COPYRIGHT);
        System.out.println("==========================Laurent====================================");

        parametre.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        parametre.put(ConstanteGlobale.PARAM_LIEN_CANDIDAT, ConstanteGlobale.LIENCANDIDAT);
        parametre.put("jourDelivre", jourDelivre);

        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(depouillementDTOS);
        return reportService.createReports("depouie.jasper", datasource, parametre);
    }


    public byte[] imprimerDeliberation(DeliberationDTO deliberationDTO) {

        ExerciceBudgetaire exercice = exerciceBudgetaireRepository.findCurrentExercice();

        deliberationDTO.getCandidatLots().forEach(candidatLotDTO -> {

            candidatLotDTO.setNomStructure(candidatLotDTO.getCandidat().getNomStructure());
            candidatLotDTO.setAdresse(candidatLotDTO.getCandidat().getAdresse());
            candidatLotDTO.setEmail(candidatLotDTO.getCandidat().getEmail());

            candidatLotDTO.setLibelle(candidatLotDTO.getLot().getLibelle());

            System.out.println("==========================getLibelle====================================");
            System.out.println(candidatLotDTO.getLot().getLibelle());
            System.out.println("==========================Faycal====================================");

            deliberationDTO.getDate();

            System.out.println("==========================getDate====================================");
            System.out.println(deliberationDTO.getDate());
            System.out.println("==========================Faycal====================================");

        });

        HashMap<String, Object> parametres = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);
        System.out.println("==========================montant====================================");
        System.out.println(jourDelivre);
        System.out.println(ConstanteGlobale.COPYRIGHT);
        System.out.println("==========================Laurent====================================");

        parametres.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        parametres.put(ConstanteGlobale.PARAM_LIEN_CANDIDAT, ConstanteGlobale.LIENCANDIDAT);
        parametres.put("jourDelivre", jourDelivre);
        List<DeliberationDTO> deliberationDTOS = new ArrayList<>();
        deliberationDTOS.add(deliberationDTO);

        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(deliberationDTOS);
        return reportService.createReports("Deliberation.jasper", datasource, parametres);
    }


    public byte[] imprimerCandidat(List<CandidatDTO> candidatDTOS) {

        candidatDTOS.forEach(candidatDTO -> {
            List<LotDTO> lotDTOS = new ArrayList<>();
            List<CandidatLotDTO> candidatLotDTOS = candidatLotService.findAllByCandidatId(candidatDTO.getId());
            candidatLotDTOS.forEach(candidatLotDTO -> {
                lotDTOS.add(candidatLotDTO.getLot());
            });
            candidatDTO.setLots(lotDTOS);
            System.out.println("==========================Laurent====================================");
            System.out.println(candidatDTO.getLots());
            System.out.println("==========================Laurent====================================");

        });

        // candidatDTOS = candidatLotRepository.findAll().stream().filter(candidatLot -> candidatLot.getLot().equals()).collect(Collectors.toList());

        HashMap<String, Object> parametres = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);
        parametres.put("jourDelivre", jourDelivre);

        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(candidatDTOS);
        return reportService.createReports("candidat.jasper", datasource, parametres);

    }


/*
    public byte[] imprimerRecours(ReclamationDTO reclamationDTO) {

        reclamationDTO.getReclamationCandidatLots().forEach(reclamationCandidatLotDTO -> {

            reclamationCandidatLotDTO.setDateReclam(reclamationCandidatLotDTO.getDecision().getDate());

            reclamationCandidatLotDTO.setStructure(reclamationCandidatLotDTO.getDecision().getStructure());

            reclamationCandidatLotDTO.setReferenceReclam(reclamationCandidatLotDTO.getDecision().getReference());

            reclamationCandidatLotDTO.getMotif();

            reclamationCandidatLotDTO.setLibelle(reclamationDTO.getLot().getLibelle());

            reclamationCandidatLotDTO.setDescription(reclamationDTO.getDescription().replaceAll("<p>", "").replaceAll("</p>", ""));

        });

        reclamationDTO.getDate();

        reclamationDTO.setLibelle(reclamationDTO.getLots().getLibelle());

        reclamationDTO.getAvisDac();

        HashMap<String, Object> parametres = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String jourDelivre = LocalDate.now().format(formatter);
        System.out.println("==========================DateDuJour====================================");
        System.out.println(jourDelivre);
        System.out.println(ConstanteGlobale.COPYRIGHT);
        System.out.println("==========================FayCal====================================");

        parametres.put(ConstanteGlobale.PARAM_COPY_RIGHT, ConstanteGlobale.COPYRIGHT);
        parametres.put(ConstanteGlobale.PARAM_LIEN_CANDIDAT, ConstanteGlobale.LIENCANDIDAT);
        parametres.put("jourDelivre", jourDelivre);

         List<ReclamationDTO> reclamationDTOS1 = new ArrayList<>();
         reclamationDTOS1.add(reclamationDTO);

        JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(reclamationDTOS1);
        return reportService.createReports("Reclamation.jasper", datasource, parametres);

    }
*/


}






