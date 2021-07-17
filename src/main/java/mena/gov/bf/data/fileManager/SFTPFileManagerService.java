package mena.gov.bf.data.fileManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import javax.activation.MimetypesFileTypeMap;
import javax.transaction.Transactional;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
public class SFTPFileManagerService {

    private final Logger log = LoggerFactory.getLogger(FileManagerService.class);
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

   private Path path;
   private String chemin = null;
   private String host = "";
   private int port = 0;
   private String user = "";
   private String pswd = "";

   private JSch ssh = new JSch();
   private Session session;
   private ChannelSftp sftp;

    public SFTPFileManagerService(ExerciceBudgetaireRepository exerciceBudgetaireRepository,
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

    private void setServeurValues() {
        Serveur serveur = serveurRepository.getServeurActif();
        host = serveur.getAdresse();
        port = serveur.getPort();
        user = serveur.getNomServeur();
        pswd = serveur.getMotPasse();
        System.out.println("Informations Serveur: adresse=" + host + ", port=" + port + ", user=" + user);
    }

    public boolean sftpConnect() {
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        setServeurValues();
        /*
         * host = "192.162.71.76"; port = 22; user = "odk"; pswd = "O2kp@$$wd";
         */
        try {
            session = ssh.getSession(user, host, port);
            session.setConfig(config);
            session.setPassword(pswd);
            // session.setTimeout(360000000);
            session.connect();

            sftp = (ChannelSftp) session.openChannel("sftp");
            sftp.connect();
            System.out.println("========== Connection success =============");
            return true;
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    public void sftDisconnect() {
        sftp.disconnect();
        session.disconnect();
    }

    @Transactional
    public void sftpConnexion() {
        boolean success = sftpConnect();
        if (success)
            System.out.println("============= Connexion effectuée avec success ========");
        else
            System.out.println("=============== Echec de connexion =================");
    }

    @Transactional
    public void returnChemin(Long id, TypeDossier typeDossier) {
        System.out.println("================ " + getPath(id, typeDossier) + " ======================");
    }

    public void fileUploading(Long id, TypeDossier typeDossier, List<DataFile> dataFiles) {
        boolean success = sftpConnect();
        InputStream inputStream;
        if (success) {
            String filePath = createDirectory(id, typeDossier);
            if (!dataFiles.isEmpty()) {
                for (DataFile dataFile : dataFiles) {
                    if (dataFile.getFile() != null) {
                        inputStream = new ByteArrayInputStream(dataFile.getFile());
                        try {

                            sftp.put(inputStream, filePath + dataFile.getFileName());
                            System.out.println("======================== Document chargé avec success ==============");
                        } catch (SftpException e) {
                            // TODO Auto-generated catch block
                            log.debug("================= Problème de SFTP =====================");
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("************* Le byte est null ***********************");
                    }
                }
            } else {
                System.out.println("************* Nombre de fichiers joints: 0 ***********************");
            }
        }
    }

    @Transactional
    public List<DataFile> getEntityDataFile(Long id, TypeDossier typeDossier, boolean success) {
        List<DataFile> datas = new ArrayList<>();
        String currentDirectory = null;
        String path = getPath(id, typeDossier);
        byte[] byteArray;
        MimetypesFileTypeMap contenType = new MimetypesFileTypeMap();
        try {
            try {
                currentDirectory = sftp.pwd();
            } catch (SftpException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String chemin1 = currentDirectory + path;
            if (!success) {
                System.out.println("============== Could not login to the server ===============");
            } else {
                Vector filelist = sftp.ls(chemin1);
                for (int i = 0; i < filelist.size(); i++) {
                    LsEntry entry = (LsEntry) filelist.get(i);
                    if (!entry.getAttrs().isDir()) {
                        InputStream inputStream = sftp.get(chemin1 + entry.getFilename());
                        byteArray = IOUtils.toByteArray(inputStream);
                        try {
                            if (byteArray.length > 0) {
                                DataFile dataFile = new DataFile();
                                dataFile.setFileName(entry.getFilename());
                                dataFile.setFile(byteArray);
                                dataFile.setFileContentType(contenType.getContentType(entry.getFilename()));
                                datas.add(dataFile);
                            }
                            inputStream.close();

                        } catch (Exception e) {
                            // TODO: handle exception
                            System.out.println("======= Erreur de lecture de fichier =======");
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception

        }

        return datas;
    }

    public void deleteAllByEntity(Long id, TypeDossier typeDossier, boolean success) {
        String currentDirectory = null;
        String path = getPath(id, typeDossier);
        byte[] byteArray;
        List<DataFile> datas = new ArrayList<>();
        try {
            try {
                currentDirectory = sftp.pwd();
            } catch (SftpException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String chemin1 = currentDirectory + path;
            if (!success) {
                System.out.println("============== Could not login to the server ===============");
            } else {

                Vector filelist = sftp.ls(chemin1);
                MimetypesFileTypeMap contenType = new MimetypesFileTypeMap();
                for (int i = 0; i < filelist.size(); i++){
                    LsEntry entry = (LsEntry) filelist.get(i);
                    if(!entry.getAttrs().isDir()){
                        InputStream inputStream = sftp.get(chemin1 + entry.getFilename());
                       byteArray = IOUtils.toByteArray(inputStream);
                        log.debug( "==byteArray : {}", byteArray.length );
                        try {
                           // int in = inputStream.read(byteArray);
                          //  if (in != -1) {
                            if (byteArray.length>0) {
                                DataFile dataFile = new DataFile();
                                dataFile.setFileName(entry.getFilename());
                                dataFile.setFile(byteArray);
                                log.debug( "contentType: {}", contenType.getContentType( entry.getFilename() ) );
                                dataFile.setFileContentType(contenType.getContentType( entry.getFilename() )  );
                                datas.add(dataFile);
                            }
                            inputStream.close();
                            /*} else {
                                System.out.println("Aucun octe lu");
                            }*/
                            // while (!sftp.completePendingCommand())
                        } catch (Exception e) {
                            // TODO: handle exception
                            System.out.println("======= Erreur de lecture de fichier =======");
                        }
                    }

                }
                sftp.cd("..");
                sftp.rmdir(chemin1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("=============== Echec de suppression de document ================");
        }
    }

    @Transactional
    public void deleteFile(Long id, TypeDossier typeDossier, String filename, boolean success) {
        String currentDirectory = null;
        String path = getPath(id, typeDossier);
        try {
            try {
                currentDirectory = sftp.pwd();
            } catch (SftpException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
          //  String chemin1 = currentDirectory;
            if (!success) {
                System.out.println("============== Could not login to the server ===============");
            } else {
                LsEntry entry = getFile(id, typeDossier, currentDirectory, filename);
                if (entry!=null) {
                    sftp.rm(currentDirectory + path + entry.getFilename());
                    System.out.println("Fichier '" + entry.getFilename() + "' Supprimé avec succès...");
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("=============== Echec de suppression de document ================");
        }
    }

    private LsEntry getFile(Long id, TypeDossier typeDossier, String currentDirectory, String filename) {
        String chem = getPath(id, typeDossier);
        String chemin1 = currentDirectory + chem;
        LsEntry entry = null;
        try {
            Vector filelist = sftp.ls(chemin1);
            for (int i = 0; i < filelist.size(); i++) {
                entry = (LsEntry) filelist.get(i);
                if (!entry.getAttrs().isDir() && entry.getFilename().equals(filename))
                    break;
            }
            return entry;
        } catch (SftpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public String getPath(Long id, TypeDossier typeDossier) {
        chemin = "/documents/";
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
                log.debug("=============================================={},", id);
                Penalite penalite = penaliteRepository.findPenaliteById(id);
                log.debug("======penalite.getId() : {}",penalite.getId());
                Contrat contrat7 = contratRepository.findContratById(penalite.getContratId());

                log.debug("======contrat7.getId() : {}",contrat7.getId());
                CandidatLot candidatLot8 = candidatLotRepository.getOne(contrat7.getCandidatLotId());
                log.debug("======candidatLot8.getId() : {}",candidatLot8.getId());

                Lot lot12 = lotRepository.getOne(candidatLot8.getLot().getId());
                log.debug("======lot12.getId() : {}",lot12.getId());
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
            return candidatExist.get().getLot();
        }
        return null;
    }

    private Lot getLotByReclamation(Long idReclamation) {
        Optional<ReclamationCandidatLot> candidatExist = reclamationCandidatLotRepository.findAll().stream()
                .filter(candidatLot -> candidatLot.getReclamation() != null
                        && candidatLot.getReclamation().getId().equals(idReclamation))
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
        } else
            return null;
    }

    private String createDirectory(Long id, TypeDossier typeDossier) {
        String currentDirectory = null;
        try {
            currentDirectory = sftp.pwd();
        } catch (SftpException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String path = currentDirectory + getPath(id, typeDossier);

        try {
            String[] folders = path.split("/");
            if (folders[0].isEmpty())
                folders[0] = "/";
            String fullPath = folders[0];
            for (int i = 1; i < folders.length; i++) {
                Vector ls = sftp.ls(fullPath);
                boolean isExist = false;
                for (Object o : ls) {
                    if (o instanceof LsEntry) {
                        LsEntry e = (LsEntry) o;
                        if (e.getAttrs().isDir() && e.getFilename().equals(folders[i])) {
                            isExist = true;
                        }
                    }
                }
                if (!isExist && !folders[i].isEmpty()) {
                    sftp.mkdir(fullPath + folders[i]);
                }
                fullPath = fullPath + folders[i] + "/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

}
