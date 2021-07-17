package mena.gov.bf.dashboard.entity;
import mena.gov.bf.service.dto.CandidatLotDTO;
import java.time.LocalDate;

public class LotInfoDTO {

    private String libelle;
    private CandidatLotDTO titulaire = new CandidatLotDTO();
    private LocalDate dateSignatureContrat;
    private String situation;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public CandidatLotDTO getTitulaire() {
        return titulaire;
    }

    public void setTitulaire(CandidatLotDTO titulaire) {
        this.titulaire = titulaire;
    }

    public LocalDate getDateSignatureContrat() {
        return dateSignatureContrat;
    }

    public void setDateSignatureContrat(LocalDate dateSignatureContrat) {
        this.dateSignatureContrat = dateSignatureContrat;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
