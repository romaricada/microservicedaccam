package mena.gov.bf.notification;

import mena.gov.bf.beans.Activite;
import mena.gov.bf.beans.ExerciceBudgetaire;
import mena.gov.bf.domain.DelaiMessage;
import mena.gov.bf.domain.Membre;
import mena.gov.bf.domain.MembreCommission;
import mena.gov.bf.domain.Tache;
import mena.gov.bf.domain.enumeration.TypeMessage;
import mena.gov.bf.proxies.ActiviteRepository;
import mena.gov.bf.proxies.ExerciceBudgetaireRepository;
import mena.gov.bf.repository.DelaiMessageRepository;
import mena.gov.bf.repository.MembreCommissionRepository;
import mena.gov.bf.repository.TacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final Logger log = LoggerFactory.getLogger( NotificationService.class );
    private final TacheRepository tacheRepository;
    private final ExerciceBudgetaireRepository exerciceBudgetaireRepository;
    private final ActiviteRepository activiteRepository;
    private final MembreCommissionRepository membreCommissionRepository;
    private final MailService mailService;
    private final DelaiMessageRepository delaiMessageRepository;


    public NotificationService(
        TacheRepository tacheRepository,
        ExerciceBudgetaireRepository exerciceBudgetaireRepository,
        ActiviteRepository activiteRepository,
        MembreCommissionRepository membreCommissionRepository,
        MailService mailService,
        DelaiMessageRepository delaiMessageRepository) {
        this.tacheRepository = tacheRepository;
        this.exerciceBudgetaireRepository = exerciceBudgetaireRepository;
        this.activiteRepository = activiteRepository;
        this.membreCommissionRepository = membreCommissionRepository;
        this.mailService = mailService;
        this.delaiMessageRepository = delaiMessageRepository;
    }

    /**
     * 12h = 43 200 000 ms
     * 1h = 3 600 000 ms
     * 1s = 1000 ms
     * 60s = 60 000 ms
     */
    @Scheduled(fixedDelay = 43200000)
    @Transactional
    public void sendAlerteOfDelaisExecution() {
        Optional<ExerciceBudgetaire> exerciceBudgetaire = Optional.ofNullable( exerciceBudgetaireRepository.findCurrentExercice() );
        int temps = this.getTempsAlerte();
        Optional<DelaiMessage> messageOptional = this.delaiMessageRepository.findTop1ByTypeMessageAndTempsAlerteIsNull( TypeMessage.ALERTEEXECUTION );
        if (exerciceBudgetaire.isPresent()) {
            List<Activite> activiteList = activiteRepository.findAllActiviteByAnnee( exerciceBudgetaire.get().getId() );
            if (!activiteList.isEmpty()) {
                activiteList.forEach( activite -> {
                    List<Tache> tacheList = this.tacheRepository.findAllByAvisDacIdAndAvisDacIdIsNotNullAndDeletedIsFalse( activite.getId() );
                    if (!tacheList.isEmpty()) {
                        tacheList.forEach( tache -> {
                            int nbJourRestant = this.countDayOfTache( tache.getDateDebut(), tache.getDateFin() );
                            if (nbJourRestant <= temps) {
                                Set<Membre> membres = tache.getMembreCommissions().stream().map( MembreCommission::getMembre ).collect( Collectors.toSet() );
                                if (!membres.isEmpty()) {
                                    membres.forEach( membre -> {
                                        if (messageOptional.isPresent()) {
                                            String message = messageOptional.get().getMessage().replaceAll( "<[^>]+>", "" ) + " Il vous reste " + nbJourRestant + " jour(s).";
                                            this.mailService.sendAlertEmail( membre, tache.getLibelle(), message );
                                        } else {
                                            String message = "Le délai d'exécution de la tâche " + tache.getLibelle().toLowerCase() + "est moins de " + nbJourRestant + " jour(s)." +
                                                " Pensez à sa réalisation pour être dans les délais.";
                                            this.mailService.sendAlertEmail( membre, tache.getLibelle(), message );
                                        }
                                    } );
                                }
                            }
                        } );
                    }
                } );
            }
        }
    }

    @Transactional
    public void sendAlerteByTacheAndTypeMessage(Tache tache, TypeMessage typeMessage) {
        List<Membre> membres = this.membreCommissionRepository.findAllByTacheIdAndTacheIsNotNullAndDeletedIsFalse( tache.getId() )
            .stream().map( MembreCommission::getMembre ).collect( Collectors.toList() );
        if (!membres.isEmpty()) {
            Optional<DelaiMessage> messageOptional = this.delaiMessageRepository.findTop1ByTypeMessageAndTempsAlerteIsNull( typeMessage );
            int nbJour = this.countDayOfTache( tache.getDateDebut(), tache.getDateFin() );
            membres.forEach( membre -> {
                if (messageOptional.isPresent()) {
                    String message = messageOptional.get().getMessage().replaceAll( "<[^>]+>", "" ) + " Vous devez le faire dans " + nbJour + " jour(s)";
                    this.mailService.sendAlertEmail( membre, tache.getLibelle(), message );
                } else {
                    String message = "Vous avez été associé à l'execution de la tâche " + tache.getLibelle() + "." +
                        "Vous devez le faire dans " + nbJour + " jour(s).";
                    this.mailService.sendAlertEmail( membre, tache.getLibelle(), message );
                }
            } )   ;
        }
    }

    private int getTempsAlerte() {
        Optional<DelaiMessage> tempsAlerte = this.delaiMessageRepository.findTop1ByTypeMessageAndTempsAlerteIsNotNull( TypeMessage.DELAI );
        if (tempsAlerte.isPresent()) {
            return tempsAlerte.get().getTempsAlerte();
        } else {
            return 2;
        }
    }

    private Integer countDayOfTache(LocalDate debut, LocalDate fin) {
        Integer n = 0;
        for (LocalDate date = debut; date.isBefore( fin ); date = date.plusDays( 1 )) {
            n++;
        }
        return n;
    }
}
