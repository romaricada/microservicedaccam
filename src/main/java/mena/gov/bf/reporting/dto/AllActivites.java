package mena.gov.bf.reporting.dto;

import mena.gov.bf.beans.Activite;
import mena.gov.bf.domain.enumeration.EtatMarche;
import mena.gov.bf.service.dto.TacheDTO;

import java.util.ArrayList;
import java.util.List;

public class AllActivites {
    private String nomActivite;
    private String dateLancement;
    private String dateButtoire;
    private String budget;
    private EtatMarche etatMarche;

    private List<Activite> activitesExecute = new ArrayList<>();
    private List<Activite> activitesEnCours = new ArrayList<>();
    private List<Activite> activitesEnLitige = new ArrayList<>();
    private List<Activite> activitesResilie = new ArrayList<>();
    private List<Activite> allActivites = new ArrayList<>();
    private List<TacheDTO> taches = new ArrayList<>();


    public EtatMarche getEtatMarche() { return etatMarche; }

    public void setEtatMarche(EtatMarche etatMarche) { this.etatMarche = etatMarche; }

    public List<Activite> getActivitesExecute() {
        return activitesExecute;
    }

    public void setActivitesExecute(List<Activite> activitesExecute) {
        this.activitesExecute = activitesExecute;
    }

    public List<Activite> getActivitesEnCours() {
        return activitesEnCours;
    }

    public void setActivitesEnCours(List<Activite> activitesEnCours) {
        this.activitesEnCours = activitesEnCours;
    }

    public List<Activite> getActivitesEnLitige() {
        return activitesEnLitige;
    }

    public void setActivitesEnLitige(List<Activite> activitesEnLitige) {
        this.activitesEnLitige = activitesEnLitige;
    }

    public List<Activite> getActivitesResilie() {
        return activitesResilie;
    }

    public void setActivitesResilie(List<Activite> activitesResilie) {
        this.activitesResilie = activitesResilie;
    }

    public List<Activite> getAllActivites() {
        return allActivites;
    }

    public void setAllActivites(List<Activite> allActivites) {
        this.allActivites = allActivites;
    }

    public List<TacheDTO> getTaches() {
        return taches;
    }

    public void setTaches(List<TacheDTO> taches) {
        this.taches = taches;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public String getDateLancement() {
        return dateLancement;
    }

    public void setDateLancement(String dateLancement) {
        this.dateLancement = dateLancement;
    }

    public String getDateButtoire() {
        return dateButtoire;
    }

    public void setDateButtoire(String dateButtoire) {
        this.dateButtoire = dateButtoire;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
