package mena.gov.bf.reporting.dto;

import mena.gov.bf.beans.Activite;
import mena.gov.bf.domain.Depouillement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecuDTO {

    private List<Activite> activites = new ArrayList<>();
    private List<Depouillement> depouillements = new ArrayList<>();




    private String nom;
    private String prenom;
    private String nomActivite;
    private String telephone;
    private String email;
    private Enum typeDepot;
    private String  lieu;


    private String description;
    private LocalDate date;
   /* private String lieu;*/
    private String salle;
    private String depouillementId;

    public String getDepouillementId() {
        return depouillementId;
    }

    public void setDepouillementId(String depouillementId) {
        this.depouillementId = depouillementId;
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

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public List<Depouillement> getDepouillements() {
        return depouillements;
    }

    public void setDepouillements(List<Depouillement> depouillements) {
        this.depouillements = depouillements;
    }


    public RecuDTO() {
    }


    /**
     * Sets new nom.
     *
     * @param nom New value of nom.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Sets new nomActivite.
     *
     * @param nomActivite New value of nomActivite.
     */
    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    /**
     * Gets nom.
     *
     * @return Value of nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Gets nomActivite.
     *
     * @return Value of nomActivite.
     */
    public String getNomActivite() {
        return nomActivite;
    }


    /**
     * Gets prenom.
     *
     * @return Value of prenom.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets new prenom.
     *
     * @param prenom New value of prenom.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }



    /**
     * Gets activites.
     *
     * @return Value of activites.
     */
    public List<Activite> getActivites() {
        return activites;
    }

    /**
     * Sets new activites.
     *
     * @param activites New value of activites.
     */
    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public Enum getTypeDepot() {
        return typeDepot;
    }

    public void setTypeDepot(Enum typeDepot) {
        this.typeDepot = typeDepot;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
