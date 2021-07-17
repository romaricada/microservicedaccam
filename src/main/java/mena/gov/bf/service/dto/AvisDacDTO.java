/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mena.gov.bf.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;

import mena.gov.bf.beans.Activite;
import mena.gov.bf.beans.BesoinLigneBudgetaire;
import mena.gov.bf.dashboard.entity.LotInfoDTO;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.domain.enumeration.EtatMarche;
import mena.gov.bf.model.DataFile;

/**
 *
 * @author nafolo
 */
public class AvisDacDTO implements Serializable {
   private Long id;
    @NotNull
    private String numeroAvis;
    @NotNull
    private String objet;
    private Long exerciceId;
    private Long modePassationId;
    private LocalDate dateLancement;
    private LocalDate dateDepotOffre;
    private Boolean deleted;
    private Long activiteId;
    private Integer validite;
    private Set<LotDTO> lots = new HashSet<>();
    private List<DataFile> files = new ArrayList<>();
    private Set<Long> besionLigneBugetaitaireIds;
    private Double etatAvancement;
    private List<LotInfoDTO> lotInfoDTOS = new ArrayList<>();
    private List<TacheDTO> taches = new ArrayList<>();
    private EtatMarche etatMarche;
    private  List<BesoinLigneBudgetaire> listebudgets = new ArrayList<>();
    private  List<Lot> listelots = new ArrayList<>();


    public List<Lot> getListelots() {
        return listelots;
    }

    public void setListelots(List<Lot> listelots) {
        this.listelots = listelots;
    }

    public List<BesoinLigneBudgetaire> getListebudgets() {
        return listebudgets;
    }

    public void setListebudgets(List<BesoinLigneBudgetaire> listebudgets) {
        this.listebudgets = listebudgets;
    }

    public String getNumeroAvis() {
        return numeroAvis;
    }

    public void setNumeroAvis(String numeroAvis) {
        this.numeroAvis = numeroAvis;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Long getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(Long exerciceId) {
        this.exerciceId = exerciceId;
    }

    public Long getModePassationId() {
        return modePassationId;
    }

    public void setModePassationId(Long modePassationId) {
        this.modePassationId = modePassationId;
    }

    public LocalDate getDateLancement() {
        return dateLancement;
    }

    public void setDateLancement(LocalDate dateLancement) {
        this.dateLancement = dateLancement;
    }

    public LocalDate getDateDepotOffre() {
        return dateDepotOffre;
    }

    public void setDateDepotOffre(LocalDate dateDepotOffre) {
        this.dateDepotOffre = dateDepotOffre;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<LotDTO> getLots() {
        return lots;
    }

    public void setLots(Set<LotDTO> lots) {
        this.lots = lots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }

    public Integer getValidite() {
        return validite;
    }

    public void setValidite(Integer validite) {
        this.validite = validite;
    }

    public List<DataFile> getFiles() {
        return files;
    }

    public void setFiles(List<DataFile> files) {
        this.files = files;
    }

    public Set<Long> getBesionLigneBugetaitaireIds() {
        return besionLigneBugetaitaireIds;
    }

    public void setBesionLigneBugetaitaireIds(Set<Long> besionLigneBugetaitaireIds) {
        this.besionLigneBugetaitaireIds = besionLigneBugetaitaireIds;
    }

    public Double getEtatAvancement() {
        return etatAvancement;
    }

    public void setEtatAvancement(Double etatAvancement) {
        this.etatAvancement = etatAvancement;
    }

    public List<LotInfoDTO> getLotInfoDTOS() {
        return lotInfoDTOS;
    }

    public void setLotInfoDTOS(List<LotInfoDTO> lotInfoDTOS) {
        this.lotInfoDTOS = lotInfoDTOS;
    }

    public List<TacheDTO> getTaches() {
        return taches;
    }

    public void setTaches(List<TacheDTO> taches) {
        this.taches = taches;
    }

    public EtatMarche getEtatMarche() {
        return etatMarche;
    }

    public void setEtatMarche(EtatMarche etatMarche) {
        this.etatMarche = etatMarche;
    }

    @Override
    public String toString() {
        return "AvisDacDTO{" +
            "id=" + id +
            ", numeroAvis='" + numeroAvis + '\'' +
            ", objet='" + objet + '\'' +
            ", exerciceId=" + exerciceId +
            ", modePassationId=" + modePassationId +
            ", dateLancement=" + dateLancement +
            ", dateDepotOffre=" + dateDepotOffre +
            ", deleted=" + deleted +
            ", activiteId=" + activiteId +
            ", validite=" + validite +
            ", lots=" + lots +
            ", files=" + files +
            ", besionLigneBugetaitaireIds=" + besionLigneBugetaitaireIds +
            ", etatAvancement=" + etatAvancement +
            ", lotInfoDTOS=" + lotInfoDTOS +
            ", taches=" + taches +
            ", etatMarche=" + etatMarche +
            ", listebudgets=" + listebudgets +
            ", listelots=" + listelots +
            '}';
    }
}
