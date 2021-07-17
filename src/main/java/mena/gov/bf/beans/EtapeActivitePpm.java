package mena.gov.bf.beans;
import java.time.LocalDate;

public class EtapeActivitePpm {
    private Long id;

    private LocalDate dateEtape;

    private Boolean deleted;

    private LocalDate dateReelle;

    private LocalDate debut;

    private LocalDate fin;

    private Long etapeId;

    private Long ppmActiviteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEtape() {
        return dateEtape;
    }

    public void setDateEtape(LocalDate dateEtape) {
        this.dateEtape = dateEtape;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDate getDateReelle() {
        return dateReelle;
    }

    public void setDateReelle(LocalDate dateReelle) {
        this.dateReelle = dateReelle;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Long getEtapeId() {
        return etapeId;
    }

    public void setEtapeId(Long etapeId) {
        this.etapeId = etapeId;
    }

    public Long getPpmActiviteId() {
        return ppmActiviteId;
    }

    public void setPpmActiviteId(Long ppmActiviteId) {
        this.ppmActiviteId = ppmActiviteId;
    }
}
