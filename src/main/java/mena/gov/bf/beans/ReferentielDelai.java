package mena.gov.bf.beans;

import java.time.LocalDate;

public class ReferentielDelai {
    private Long id;

    private Integer norme;

    private Integer referentiel;

    private Integer normeMin;

    private Integer referentielMin;

    private Integer normeMax;

    private Integer referentielMax;

    private String observation;

    private Boolean normeOuvrable;

    private Boolean referentielOuvrable;

    private Boolean deleted;

    private LocalDate debut;

    private LocalDate fin;

    private Long etapeId;

    private String etapeLibelle;

    private Integer etapeOrdre;

    private Long acteurId;

    private String acteurLibelle;

    private Long modePassationId;

    private String libellePassation;

    private  boolean intervalle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNorme() {
        return norme;
    }

    public void setNorme(Integer norme) {
        this.norme = norme;
    }

    public Integer getReferentiel() {
        return referentiel;
    }

    public void setReferentiel(Integer referentiel) {
        this.referentiel = referentiel;
    }

    public Integer getNormeMin() {
        return normeMin;
    }

    public void setNormeMin(Integer normeMin) {
        this.normeMin = normeMin;
    }

    public Integer getReferentielMin() {
        return referentielMin;
    }

    public void setReferentielMin(Integer referentielMin) {
        this.referentielMin = referentielMin;
    }

    public Integer getNormeMax() {
        return normeMax;
    }

    public void setNormeMax(Integer normeMax) {
        this.normeMax = normeMax;
    }

    public Integer getReferentielMax() {
        return referentielMax;
    }

    public void setReferentielMax(Integer referentielMax) {
        this.referentielMax = referentielMax;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Boolean getNormeOuvrable() {
        return normeOuvrable;
    }

    public void setNormeOuvrable(Boolean normeOuvrable) {
        this.normeOuvrable = normeOuvrable;
    }

    public Boolean getReferentielOuvrable() {
        return referentielOuvrable;
    }

    public void setReferentielOuvrable(Boolean referentielOuvrable) {
        this.referentielOuvrable = referentielOuvrable;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public String getEtapeLibelle() {
        return etapeLibelle;
    }

    public void setEtapeLibelle(String etapeLibelle) {
        this.etapeLibelle = etapeLibelle;
    }

    public Integer getEtapeOrdre() {
        return etapeOrdre;
    }

    public void setEtapeOrdre(Integer etapeOrdre) {
        this.etapeOrdre = etapeOrdre;
    }

    public Long getActeurId() {
        return acteurId;
    }

    public void setActeurId(Long acteurId) {
        this.acteurId = acteurId;
    }

    public String getActeurLibelle() {
        return acteurLibelle;
    }

    public void setActeurLibelle(String acteurLibelle) {
        this.acteurLibelle = acteurLibelle;
    }

    public Long getModePassationId() {
        return modePassationId;
    }

    public void setModePassationId(Long modePassationId) {
        this.modePassationId = modePassationId;
    }

    public String getLibellePassation() {
        return libellePassation;
    }

    public void setLibellePassation(String libellePassation) {
        this.libellePassation = libellePassation;
    }

    public boolean isIntervalle() {
        return intervalle;
    }

    public void setIntervalle(boolean intervalle) {
        this.intervalle = intervalle;
    }
}
