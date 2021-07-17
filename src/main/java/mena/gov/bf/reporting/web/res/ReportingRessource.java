package mena.gov.bf.reporting.web.res;

import mena.gov.bf.dashboard.entity.Dashboard;
import mena.gov.bf.reporting.service.ReportingService;
import mena.gov.bf.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/api")
public class ReportingRessource {
    @Autowired
    ReportingService reportingService;
    /***
     *
     * @return
     */
    @GetMapping("/dashboard/print")
    public ResponseEntity<Dashboard> findAccueilPrintInformation(@RequestParam Long idExercice) {
        return ResponseEntity.ok(reportingService.getCurrentTask(idExercice));
    }

    @GetMapping("/reporting/all-activite")
    public ResponseEntity<byte[]> imprimeAllActivite(@RequestParam Long sessionId) {
        byte[] contents = reportingService.imprimeAllActivite(sessionId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @GetMapping("/reporting/finished-activities")
    public ResponseEntity<byte[]> imprimeFinishedActivities(@RequestParam Long sessionId) {
        byte[] contents = reportingService.imprimeFinishedActivities(sessionId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @GetMapping("/reporting/activities-en-cours")
    public ResponseEntity<byte[]> imprimeActivitiesEnCours(@RequestParam Long sessionId) {
        byte[] contents = reportingService.imprimeActivitiesEnCours(sessionId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @GetMapping("/reporting/activities-ayant-litige")
    public ResponseEntity<byte[]> imprimeActivitiesAyantLitige( @RequestParam Long sessionId ) {
        byte[] contents = reportingService.imprimeActivitiesAyantLitige(sessionId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @GetMapping("/reporting/activities-ayant-contrat-resilie")
    public ResponseEntity<byte[]> imprimeActivitiesAyantContratResilie(@RequestParam Long sessionId) {
        byte[] contents = reportingService.imprimeActivitiesAyantContratResilie(sessionId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @GetMapping("/reporting/recu-paiement")
    public ResponseEntity<byte[]> imprimeRecuDePaiement(Long avisDacId, Long exerciceId, Long receptionId) {
        byte[] contents = reportingService.imprimerRecu(avisDacId,exerciceId, receptionId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @PutMapping("/reporting/recu-avidac")
    public ResponseEntity<byte[]> imprimeDac(@RequestBody @Valid AvisDacDTO avisDacDTOS) {
        byte[] contents = reportingService.imprimeDac(avisDacDTOS);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @PutMapping("/reporting/imp-candidat")
    public ResponseEntity<byte[]> imprimerCandidat(@RequestBody @Valid List<CandidatDTO> candidatDTOS) {
        byte[] contents = reportingService.imprimerCandidat(candidatDTOS);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @PutMapping("/reporting/recu-delib")
    public ResponseEntity<byte[]> imprimerCandidat(@RequestBody @Valid DeliberationDTO deliberationDTO) {
        byte[] contents = reportingService.imprimerDeliberation(deliberationDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

    @PutMapping("/reporting/recu-depouille")
    public ResponseEntity<byte[]> imprimeDepouillemnt(@RequestBody @Valid List<DepouillementDTO> depouillementDTOS) {
        byte[] contents = reportingService.imprimeDepouillemnt(depouillementDTOS);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }

  /*  @PutMapping("/reporting/recu-recours")
    public ResponseEntity<byte[]> imprimeRecours(@RequestBody @Valid ReclamationDTO  reclamationDTO) {
        byte[] contents = reportingService.imprimerRecours(reclamationDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }*/



}
