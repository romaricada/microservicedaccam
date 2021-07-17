package mena.gov.bf.data.fileManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.activation.MimetypesFileTypeMap;
import javax.transaction.Transactional;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import mena.gov.bf.beans.Activite;
import mena.gov.bf.beans.Avenant;
import mena.gov.bf.beans.Contentieux;
import mena.gov.bf.beans.Contrat;
import mena.gov.bf.beans.EtapeActivitePpm;
import mena.gov.bf.beans.EtapeExecution;
import mena.gov.bf.beans.ExerciceBudgetaire;
import mena.gov.bf.beans.Liquidation;
import mena.gov.bf.beans.PPM;
import mena.gov.bf.beans.Penalite;
import mena.gov.bf.beans.PpmActivite;
import mena.gov.bf.beans.Serveur;
import mena.gov.bf.beans.StatutExecution;
import mena.gov.bf.domain.CandidatCautionLot;
import mena.gov.bf.domain.CandidatLot;
import mena.gov.bf.domain.Decision;
import mena.gov.bf.domain.Deliberation;
import mena.gov.bf.domain.Depouillement;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.domain.MembreCommission;
import mena.gov.bf.domain.Reclamation;
import mena.gov.bf.domain.ReclamationCandidatLot;
import mena.gov.bf.domain.Tache;
import mena.gov.bf.domain.TacheEtape;
import mena.gov.bf.domain.TypeCommission;
import mena.gov.bf.domain.enumeration.TypeDossier;
import mena.gov.bf.model.DataFile;
import mena.gov.bf.proxies.ActiviteRepository;
import mena.gov.bf.proxies.AvenantRepository;
import mena.gov.bf.proxies.ContentieuxRepository;
import mena.gov.bf.proxies.ContratRepository;
import mena.gov.bf.proxies.DecisionContentieuxRepository;
import mena.gov.bf.proxies.EtapeActivitePpmRepository;
import mena.gov.bf.proxies.EtapeExecutionRepository;
import mena.gov.bf.proxies.ExerciceBudgetaireRepository;
import mena.gov.bf.proxies.LiquidationRepository;
import mena.gov.bf.proxies.PenaliteRepository;
import mena.gov.bf.proxies.PpmActiviteRepository;
import mena.gov.bf.proxies.PpmRepository;
import mena.gov.bf.proxies.ServeurRepository;
import mena.gov.bf.proxies.StatutExecutionRepository;
import mena.gov.bf.repository.CandidatCautionLotRepository;
import mena.gov.bf.repository.CandidatLotRepository;
import mena.gov.bf.repository.DecisionRepository;
import mena.gov.bf.repository.DeliberationRepository;
import mena.gov.bf.repository.DepouillementRepository;
import mena.gov.bf.repository.LotRepository;
import mena.gov.bf.repository.MembreCommissionRepository;
import mena.gov.bf.repository.ReceptionRepository;
import mena.gov.bf.repository.ReclamationCandidatLotRepository;
import mena.gov.bf.repository.ReclamationRepository;
import mena.gov.bf.repository.TacheEtapeRepository;
import mena.gov.bf.repository.TacheRepository;
import mena.gov.bf.repository.TypeCommissionRepository;

@Service
public class FTPFileManagerService {

    private final Logger log = LoggerFactory.getLogger(FTPFileManagerService.class);
    private final ExerciceBudgetaireRepository exerciceBudgetaireRepository;
    private final ActiviteRepository activiteRepository;
    private final LotRepository lotRepository;
    private final TacheRepository tacheRepository;
    private final TacheEtapeRepository tacheEtapeRepository;
    private final PpmActiviteRepository ppmActiviteRepository;
    private final PpmRepository ppmRepository;
    private final EtapeActivitePpmRepository etapeActivitePpmRepository;
    private final DeliberationRepository deliberationRepository;
    private final CandidatCautionLotRepository candidatCautionLotRepository;
    private final ReclamationRepository reclamationRepository;
    private final CandidatLotRepository candidatLotRepository;
    private final DecisionRepository decisionRepository;
    private final ReceptionRepository receptionRepository;
    private final DepouillementRepository depouillementRepository;
    private final ReclamationCandidatLotRepository reclamationCandidatLotRepository;
    private final ContentieuxRepository contentieuxRepository;
    private final ContratRepository contratRepository;
    private final AvenantRepository avenantRepository;
    private final DecisionContentieuxRepository decisionContentieuxRepository;
    private final EtapeExecutionRepository etapeExecutionRepository;
    private final LiquidationRepository liquidationRepository;
    private final PenaliteRepository penaliteRepository;
    private final StatutExecutionRepository statutExecutionRepository;
    private final MembreCommissionRepository membreCommissionRepository;
    private final TypeCommissionRepository typeCommissionRepository;
    private final ServeurRepository serveurRepository;

    Path path;
    String chemin = null;
    String host = "";
    int port = 0;
    String user = "";
    String pswd = "";

    FTPClient client = new FTPClient();

    public FTPFileManagerService(ExerciceBudgetaireRepository exerciceBudgetaireRepository,
            ActiviteRepository activiteRepository, LotRepository lotRepository, TacheRepository tacheRepository,
            TacheEtapeRepository tacheEtapeRepository, PpmActiviteRepository ppmActiviteRepository,
            PpmRepository ppmRepository, EtapeActivitePpmRepository etapeActivitePpmRepository,
            DeliberationRepository deliberationRepository, CandidatCautionLotRepository candidatCautionLotRepository,
            ReclamationRepository reclamationRepository, CandidatLotRepository candidatLotRepository,
            DecisionRepository decisionRepository, ReceptionRepository receptionRepository,
            DepouillementRepository depouillementRepository,
            ReclamationCandidatLotRepository reclamationCandidatLotRepository,
            ContentieuxRepository contentieuxRepository, ContratRepository contratRepository,
            AvenantRepository avenantRepository, DecisionContentieuxRepository decisionContentieuxRepository,
            EtapeExecutionRepository etapeExecutionRepository, LiquidationRepository liquidationRepository,
            PenaliteRepository penaliteRepository, StatutExecutionRepository statutExecutionRepository,
            MembreCommissionRepository membreCommissionRepository, TypeCommissionRepository typeCommissionRepository,
            ServeurRepository serveurRepository) {
        this.exerciceBudgetaireRepository = exerciceBudgetaireRepository;
        this.activiteRepository = activiteRepository;
        this.lotRepository = lotRepository;
        this.tacheRepository = tacheRepository;
        this.tacheEtapeRepository = tacheEtapeRepository;
        this.ppmActiviteRepository = ppmActiviteRepository;
        this.ppmRepository = ppmRepository;
        this.etapeActivitePpmRepository = etapeActivitePpmRepository;
        this.deliberationRepository = deliberationRepository;
        this.candidatCautionLotRepository = candidatCautionLotRepository;
        this.reclamationRepository = reclamationRepository;
        this.candidatLotRepository = candidatLotRepository;
        this.decisionRepository = decisionRepository;
        this.receptionRepository = receptionRepository;
        this.depouillementRepository = depouillementRepository;
        this.reclamationCandidatLotRepository = reclamationCandidatLotRepository;
        this.contentieuxRepository = contentieuxRepository;
        this.contratRepository = contratRepository;
        this.avenantRepository = avenantRepository;
        this.decisionContentieuxRepository = decisionContentieuxRepository;
        this.etapeExecutionRepository = etapeExecutionRepository;
        this.liquidationRepository = liquidationRepository;
        this.penaliteRepository = penaliteRepository;
        this.statutExecutionRepository = statutExecutionRepository;
        this.membreCommissionRepository = membreCommissionRepository;
        this.typeCommissionRepository = typeCommissionRepository;
        this.serveurRepository = serveurRepository;
    }

    private void serveurValues() {
        Serveur serveur = serveurRepository.getServeurActif();
        host = serveur.getAdresse();
        port = serveur.getPort();
        user = serveur.getNomServeur();
        pswd = serveur.getMotPasse();
    }

    public String getPath(Long id, TypeDossier typeDossier) {

        // chemin = getAdressPath();
        chemin = "./src/main/resources/documents/";
        System.out.println("=============== " + chemin + " ==============");
        ExerciceBudgetaire exerciceBudgetaire = exerciceBudgetaireRepository.findCurrentExercice();
        switch (typeDossier) {
            case ACTIVITE:
                Activite activite = activiteRepository.findActiviteById(id);
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite.getId() + "/";
                break;
            case COMMISSION:
                MembreCommission membreCommission = membreCommissionRepository.getOne(id);
                Activite activitecommision = activiteRepository.findActiviteById(membreCommission.getAvisDacId());
                TypeCommission typeCommission = typeCommissionRepository
                        .getOne(membreCommission.getTypeCommission().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activitecommision.getId() + "/"
                        + typeCommission.getId() + "/";
                break;
            case PPMACTIVITE:
                PpmActivite ppmActivite = ppmActiviteRepository.findPpmActiviteById(id);
                PPM ppm = ppmRepository.finPpmById(ppmActivite.getPpmId());
                ExerciceBudgetaire exerciceBudgetaire2 = exerciceBudgetaireRepository
                        .findExerciceBudgetaireById(ppm.getIdExercice());
                chemin = chemin + ppmActivite.getActiviteId() + "/" + exerciceBudgetaire2.getAnnee() + "/";
                break;
            case LOT:
                Lot lot = lotRepository.findById(id).get();
                Activite activite2 = activiteRepository.findActiviteById(lot.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite2.getId() + "/" + lot.getId() + "/";
                break;
            case TACHE:
                Tache tache = tacheRepository.findById(id).get();
                Activite activite3 = activiteRepository.findActiviteById(tache.getAvisDacId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite3.getId() + "/" + tache.getId() + "/";
                break;
            case TACHEETAPE:
                TacheEtape tacheEtape = tacheEtapeRepository.getOne(id);
                EtapeActivitePpm etapeActivitePpm = etapeActivitePpmRepository
                        .findEtapeActivitePpmById(tacheEtape.getEtapeActivitePpmId());
                PpmActivite ppmActivite2 = ppmActiviteRepository
                        .findPpmActiviteById(etapeActivitePpm.getPpmActiviteId());
                PPM ppm2 = ppmRepository.finPpmById(ppmActivite2.getPpmId());
                ExerciceBudgetaire exerciceBudgetaire3 = exerciceBudgetaireRepository
                        .findExerciceBudgetaireById(ppm2.getIdExercice());
                chemin = chemin + etapeActivitePpm.getEtapeId() + "/" + ppmActivite2.getActiviteId() + "/"
                        + exerciceBudgetaire3.getAnnee() + "/";
                break;
            case DELIBERATION:
                Deliberation deliberation = deliberationRepository.getOne(id);
                Lot lot2 = getLotByDeliberation(deliberation.getId());
                Activite activite4 = activiteRepository.findActiviteById(lot2.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite4.getId() + "/" + lot2.getId() + "/"
                        + deliberation.getId() + "/";
                break;
            case DEPOUILLEMENT:
                Depouillement depouillement = depouillementRepository.getOne(id);
                Activite activite5 = activiteRepository.findActiviteById(depouillement.getAvisDacId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite5.getId() + "/" + depouillement.getId()
                        + "/";
                break;
            case RECLAMATION:
                Reclamation reclamation = reclamationRepository.getOne(id);
                Lot lot3 = getLotByReclamation(reclamation.getId());
                Activite activite6 = activiteRepository.findActiviteById(lot3.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite6.getId() + "/" + lot3.getId() + "/"
                        + reclamation.getId() + "/";
                break;
            case DECISION:
                Decision decision = decisionRepository.getOne(id);
                Reclamation reclamation2 = getReclamationtByDecision(decision.getId());
                Lot lot4 = getLotByReclamation(reclamation2.getId());
                Activite activite7 = activiteRepository.findActiviteById(lot4.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite7.getId() + "/" + lot4.getId() + "/"
                        + reclamation2.getId() + "/" + decision.getId() + "/";
                break;
            case CANDIDATCAUTIONLOT:
                CandidatCautionLot candidatCautionLot = candidatCautionLotRepository.getOne(id);
                CandidatLot candidatLot = candidatLotRepository.getOne(candidatCautionLot.getCandidatLot().getId());
                Lot lot5 = lotRepository.getOne(candidatLot.getLot().getId());
                Activite activite8 = activiteRepository.findActiviteById(lot5.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite8.getId() + "/" + lot5.getId() + "/"
                        + candidatLot.getId() + "/" + candidatCautionLot.getId() + "/";
                break;
            case CONTRAT:
                Contrat contrat = contratRepository.findContratById(id);
                CandidatLot candidatLot2 = candidatLotRepository.getOne(contrat.getCandidatLotId());
                Lot lot6 = lotRepository.getOne(candidatLot2.getLot().getId());
                Activite activite9 = activiteRepository.findActiviteById(lot6.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite9.getId() + "/" + lot6.getId() + "/"
                        + candidatLot2.getId() + "/" + contrat.getId() + "/";
                break;
            case AVENANT:
                Avenant avenant = avenantRepository.findAvenantById(id);
                Contrat contrat2 = contratRepository.findContratById(avenant.getContratId());
                CandidatLot candidatLot3 = candidatLotRepository.getOne(contrat2.getCandidatLotId());
                Lot lot7 = lotRepository.getOne(candidatLot3.getLot().getId());
                Activite activite10 = activiteRepository.findActiviteById(lot7.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite10.getId() + "/" + lot7.getId() + "/"
                        + candidatLot3.getId() + "/" + contrat2.getId() + "/avenant/";
                break;
            case CONTENTIEUX:
                Contentieux contentieux = contentieuxRepository.findContentieuxById(id);
                Contrat contrat3 = contratRepository.findContratById(contentieux.getContratId());
                CandidatLot candidatLot4 = candidatLotRepository.getOne(contrat3.getCandidatLotId());
                Lot lot8 = lotRepository.getOne(candidatLot4.getLot().getId());
                Activite activite11 = activiteRepository.findActiviteById(lot8.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite11.getId() + "/" + lot8.getId() + "/"
                        + candidatLot4.getId() + "/" + contrat3.getId() + "/contentieux/";
                break;
            case DECISIONCONTENTIEUX:
                Contentieux contentieux2 = contentieuxRepository.findContentieuxById(id);
                Contrat contrat6 = contratRepository.findContratById(contentieux2.getContratId());
                CandidatLot candidatLot7 = candidatLotRepository.getOne(contrat6.getCandidatLotId());
                Lot lot11 = lotRepository.getOne(candidatLot7.getLot().getId());
                Activite activite14 = activiteRepository.findActiviteById(lot11.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite14.getId() + "/" + lot11.getId() + "/"
                        + candidatLot7.getId() + "/" + contrat6.getId() + "/contentieux/decisions/";
                break;
            case ETAPEEXECUTION:
                EtapeExecution etapeExecution = etapeExecutionRepository.findEtapeExecutionById(id);
                Contrat contrat4 = contratRepository.findContratById(etapeExecution.getContratId());
                CandidatLot candidatLot5 = candidatLotRepository.getOne(contrat4.getCandidatLotId());
                Lot lot9 = lotRepository.getOne(candidatLot5.getLot().getId());
                Activite activite12 = activiteRepository.findActiviteById(lot9.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite12.getId() + "/" + lot9.getId() + "/"
                        + candidatLot5.getId() + "/" + contrat4.getId() + "/" + etapeExecution.getId() + "/";
                break;
            case LIQUIDATION:
                Liquidation liquidation = liquidationRepository.findLiquidationById(id);
                Contrat contrat5 = contratRepository.findContratById(liquidation.getContratId());
                CandidatLot candidatLot6 = candidatLotRepository.getOne(contrat5.getCandidatLotId());
                Lot lot10 = lotRepository.getOne(candidatLot6.getLot().getId());
                Activite activite13 = activiteRepository.findActiviteById(lot10.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite13.getId() + "/" + lot10.getId() + "/"
                        + candidatLot6.getId() + "/" + contrat5.getId() + "/liquidation/";
                break;
            case PENALITE:
                Penalite penalite = penaliteRepository.findPenaliteById(id);
                Contrat contrat7 = contratRepository.findContratById(penalite.getContratId());
                CandidatLot candidatLot8 = candidatLotRepository.getOne(contrat7.getCandidatLotId());
                Lot lot12 = lotRepository.getOne(candidatLot8.getLot().getId());
                Activite activite15 = activiteRepository.findActiviteById(lot12.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite15.getId() + "/" + lot12.getId() + "/"
                        + candidatLot8.getId() + "/" + contrat7.getId() + "/" + penalite.getId() + "/";
                break;
            case STATUTEXECUTION:
                StatutExecution statutExecution = statutExecutionRepository.findStatutExecutionById(id);
                Contrat contrat8 = contratRepository.findContratById(statutExecution.getContratId());
                CandidatLot candidatLot9 = candidatLotRepository.getOne(contrat8.getCandidatLotId());
                Lot lot13 = lotRepository.getOne(candidatLot9.getLot().getId());
                Activite activite16 = activiteRepository.findActiviteById(lot13.getAvisDac().getId());
                chemin = chemin + exerciceBudgetaire.getAnnee() + "/" + activite16.getId() + "/" + lot13.getId() + "/"
                        + candidatLot9.getId() + "/" + contrat8.getId() + "/" + statutExecution.getId() + "/";
                break;
        }

        System.out.println("================ " + chemin + " ==================");
        return chemin;
    }

    public String getPathCommission(Long idActivite, Long idTypeCommision) {
        ExerciceBudgetaire exerciceBudgetaire = exerciceBudgetaireRepository.findCurrentExercice();
        String chemin = "./src/main/resources/documents/" + exerciceBudgetaire.getAnnee() + "/" + idActivite + "/"
                + idTypeCommision + "/";
        return chemin;
    }

    private Lot getLotByDeliberation(Long idDeliberation) {
        Optional<CandidatLot> candidatExist = candidatLotRepository.findAll().stream()
                .filter(candidatLot -> candidatLot.getDeliberation() != null
                && candidatLot.getDeliberation().getId().equals(idDeliberation))
                .findFirst();
        if (candidatExist.isPresent()) {
            log.debug("===============================================");
            log.debug("trouvé");
            log.debug("=================================================");
            return candidatExist.get().getLot();
        }
        log.debug("===============================================");
        log.debug("non trouvé");
        log.debug("=================================================");
        return null;
    }

    private Lot getLotByReclamation(Long idReclamation) {
        Optional<ReclamationCandidatLot> candidatExist = reclamationCandidatLotRepository.findAll().stream()
                .filter(candidatLot -> candidatLot.getReclamation() != null
                && candidatLot.getReclamation().getId().equals(idReclamation)
                && candidatLot.getCandidatLot() != null && candidatLot.getCandidatLot().getLot() != null)
                .findFirst();
        if (candidatExist.isPresent()) {
            return candidatExist.get().getCandidatLot().getLot();
        }
        return null;
    }

    private Reclamation getReclamationtByDecision(Long idDecision) {
        Optional<ReclamationCandidatLot> reclamationLotExist = reclamationCandidatLotRepository.findAll().stream()
                .filter(reclamationCandidatLot -> reclamationCandidatLot.getDecision() != null
                && reclamationCandidatLot.getDecision().getId().equals(idDecision))
                .findFirst();
        if (reclamationLotExist.isPresent()) {
            Reclamation reclamation = reclamationLotExist.get().getReclamation();
            return reclamation;
        } else {
            return null;
        }
    }

    public void init(Long id, TypeDossier typeDossier) {
        String chemin = getPath(id, typeDossier);
        path = Paths.get(chemin);
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public boolean ftpConnect() {
        try {
            serveurValues();
            client.connect(host, port);
            boolean success = client.login(user, pswd);
            return success;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public void ftpDisconnect() {
        try {
            if (client.isConnected()) {
                client.logout();
                client.disconnect();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void fileUploading(Long id, TypeDossier typeDossier, List<DataFile> dataFiles) {
        InputStream inputStream = null;
        String path = getPath(id, typeDossier);
        boolean success = ftpConnect();

        if (success) {
            try {
                success = client.changeWorkingDirectory(path);
                if (!success) {
                    client.makeDirectory(path);
                }
                if (!dataFiles.isEmpty()) {
                    for (DataFile dataFile : dataFiles) {
                        if (dataFile.getFile() != null) {
                            inputStream = new ByteArrayInputStream(dataFile.getFile());
                            client.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
                            client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
                            client.enterLocalPassiveMode();
                            client.storeFile(path + dataFile.getFileName(), inputStream);
                        } else {
                            System.out.println("============= Le byte est null ============");
                        }
                    }
                    System.out.println("======================== Document chargé avec success ==============");
                } else {
                    System.out.println("************* Nombre de fichiers joints: 0 ***********************");
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
            ftpDisconnect();
        } else {
            System.out.println("===================== Echec de connexion ==================");
        }

    }

    @Transactional
    public void deleteAll(Long id, TypeDossier typeDossier) {
        String chemin = getPath(id, typeDossier);
        path = Paths.get(chemin);
        FileSystemUtils.deleteRecursively(path.toFile());
    }

    @Transactional
    public void deleteFile(Long id, TypeDossier typeDossier, String filename, boolean success) {
        String chemin1 = getPath(id, typeDossier);
        try {
            if (!success) {
                System.out.println("============== Could not login to the server ===============");
            } else {
                success = client.deleteFile(chemin1 + filename);
                if (success) {
                    System.out.println("Fichier '" + filename + "' Supprimé avec succès...");
                } else {
                    System.out.println("File '" + filename + "' doesn't exist...");
                }
                System.out.println("=============   Fin du fichier   ===============");
                ftpDisconnect();

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public List<DataFile> getEntityDataFile(Long id, TypeDossier typeDossier, boolean success) {
        List<DataFile> dataFiles = new ArrayList<>();
        String chemin1 = getPath(id, typeDossier);
        byte[] byteArray;
        try {
            // Boolean success = ftpConnect();
            if (!success) {
                System.out.println("============== Could not login to the server ===============");
            } else {
                FTPFile[] liste = client.listFiles(chemin1);
                MimetypesFileTypeMap contenType = new MimetypesFileTypeMap();
                for (FTPFile file : liste) {
                    client.enterLocalPassiveMode();
                    client.setFileType(FTPClient.BINARY_FILE_TYPE);
                    client.setRemoteVerificationEnabled(false);
                    InputStream inputStream = client.retrieveFileStream(chemin1 + file.getName());
                    byteArray = IOUtils.toByteArray(inputStream);
                    log.debug("==byteArray : {}", byteArray.length);
                    try {

                        if (byteArray.length > 0) {
                            DataFile dataFile = new DataFile();
                            dataFile.setFileName(file.getName());
                            dataFile.setFile(byteArray);
                            log.debug("mimeType: {}", contenType.getContentType(file.getName()));
                            dataFile.setFileContentType(contenType.getContentType(file.getName()));
                            dataFiles.add(dataFile);
                        }
                        inputStream.close();
                        while (!client.completePendingCommand())
                            ;
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println("======= Erreur de lecture de fichier =======");
                    }
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return dataFiles;
    }

}
