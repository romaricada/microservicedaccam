package mena.gov.bf.reporting.dto;

import mena.gov.bf.domain.Candidat;
import mena.gov.bf.domain.CandidatLot;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.domain.Reclamation;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecuReclamationDTO {

    List<Candidat> candidats = new ArrayList<>();
    List<CandidatLot> candidatLots = new ArrayList<>();
    List<Lot> lots = new ArrayList<>();
    List<Reclamation> reclamations = new ArrayList<>();


    private String nomStructure;

    private String adresse;

    private String email;

    private String description;

    private LocalDate date;

    private Long lotId;


    public String getNomStructure() {
        return nomStructure;
    }

    public void setNomStructure(String nomStructure) {
        this.nomStructure = nomStructure;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public List<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(List<Candidat> candidats) {
        this.candidats = candidats;
    }

    public List<CandidatLot> getCandidatLots() {
        return candidatLots;
    }

    public void setCandidatLots(List<CandidatLot> candidatLots) {
        this.candidatLots = candidatLots;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    @Override
    public String toString() {
        return "RecuReclamationDTO{" +
            "nomStructure='" + nomStructure + '\'' +
            ", adresse='" + adresse + '\'' +
            ", email='" + email + '\'' +
            ", description='" + description + '\'' +
            ", date=" + date +
            ", lotId=" + lotId +
            '}';
    }
}
