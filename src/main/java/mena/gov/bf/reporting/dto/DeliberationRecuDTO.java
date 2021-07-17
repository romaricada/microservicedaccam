package mena.gov.bf.reporting.dto;

public class DeliberationRecuDTO {


    private String nomStructure;

    private String adresse;

    private String email;

    private Double montantLuHt;

    private Double montantCorrigeHt;

    private Double montantLuTtc;

    private Double montantCorrigeTtc;

    private String rang;

    private Long lotId;

    private Long candidatId;

    private Long deliberationId;


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

    public Double getMontantLuHt() {
        return montantLuHt;
    }

    public void setMontantLuHt(Double montantLuHt) {
        this.montantLuHt = montantLuHt;
    }

    public Double getMontantCorrigeHt() {
        return montantCorrigeHt;
    }

    public void setMontantCorrigeHt(Double montantCorrigeHt) {
        this.montantCorrigeHt = montantCorrigeHt;
    }

    public Double getMontantLuTtc() {
        return montantLuTtc;
    }

    public void setMontantLuTtc(Double montantLuTtc) {
        this.montantLuTtc = montantLuTtc;
    }

    public Double getMontantCorrigeTtc() {
        return montantCorrigeTtc;
    }

    public void setMontantCorrigeTtc(Double montantCorrigeTtc) {
        this.montantCorrigeTtc = montantCorrigeTtc;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public Long getDeliberationId() {
        return deliberationId;
    }

    public void setDeliberationId(Long deliberationId) {
        this.deliberationId = deliberationId;
    }
}
