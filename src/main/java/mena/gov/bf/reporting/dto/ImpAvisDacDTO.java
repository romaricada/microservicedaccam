package mena.gov.bf.reporting.dto;

import mena.gov.bf.beans.BesoinLigneBudgetaire;
import mena.gov.bf.domain.Lot;
import mena.gov.bf.service.dto.LotDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImpAvisDacDTO {
    private LocalDate dateLancement;
    private LocalDate dateDepotOffre;
    private String nomActivite;
    private Integer validite;
    private List<BesoinLigneBudgetaire> listebudgets = new ArrayList<>();
    private Set<LotDTO> listelots = new HashSet<>();

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

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public Integer getValidite() {
        return validite;
    }

    public void setValidite(Integer validite) {
        this.validite = validite;
    }

    public List<BesoinLigneBudgetaire> getListebudgets() {
        return listebudgets;
    }

    public void setListebudgets(List<BesoinLigneBudgetaire> listebudgets) {
        this.listebudgets = listebudgets;
    }

    public Set<LotDTO> getListelots() {
        return listelots;
    }

    public void setListelots(Set<LotDTO> listelots) {
        this.listelots = listelots;
    }
}
