package mena.gov.bf.reporting.dto;

import mena.gov.bf.domain.AvisDac;
import mena.gov.bf.domain.enumeration.EtatMarche;
import mena.gov.bf.service.dto.AvisDacDTO;
import mena.gov.bf.service.dto.TacheDTO;

import java.util.ArrayList;
import java.util.List;

public class AllAvisDacDTO {

    private String nomActivite;
    private String dateLancement;
    private String dateDepotOffre;
    private String Objet;
    private EtatMarche etatMarche;

    private List<AvisDacDTO> activitesExecute = new ArrayList<>();
    private List<AvisDacDTO> activitesEnCours = new ArrayList<>();
    private List<AvisDacDTO> activitesEnLitige = new ArrayList<>();
    private List<AvisDacDTO> activitesResilie = new ArrayList<>();
    private List<AvisDacDTO> allActivites = new ArrayList<>();
    private List<TacheDTO> taches = new ArrayList<>();


    public EtatMarche getEtatMarche() { return etatMarche; }

    public void setEtatMarche(EtatMarche etatMarche) { this.etatMarche = etatMarche; }

    public List<AvisDacDTO> getActivitesExecute() {
        return activitesExecute;
    }

    public void setActivitesExecute(List<AvisDacDTO> activitesExecute) {
        this.activitesExecute = activitesExecute;
    }

    public List<AvisDacDTO> getActivitesEnCours() {
        return activitesEnCours;
    }

    public void setActivitesEnCours(List<AvisDacDTO> activitesEnCours) {
        this.activitesEnCours = activitesEnCours;
    }

    public List<AvisDacDTO> getActivitesEnLitige() {
        return activitesEnLitige;
    }

    public void setActivitesEnLitige(List<AvisDacDTO> activitesEnLitige) {
        this.activitesEnLitige = activitesEnLitige;
    }

    public List<AvisDacDTO> getActivitesResilie() {
        return activitesResilie;
    }

    public void setActivitesResilie(List<AvisDacDTO> activitesResilie) {
        this.activitesResilie = activitesResilie;
    }

    public List<AvisDacDTO> getAllActivites() {
        return allActivites;
    }

    public void setAllActivites(List<AvisDacDTO> allActivites) {
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

    public String getDateDepotOffre() {
        return dateDepotOffre;
    }

    public void setDateDepotOffre(String dateDepotOffre) {
        this.dateDepotOffre = dateDepotOffre;
    }

    public String getObjet() {
        return Objet;
    }

    public void setObjet(String objet) {
        Objet = objet;
    }

}
