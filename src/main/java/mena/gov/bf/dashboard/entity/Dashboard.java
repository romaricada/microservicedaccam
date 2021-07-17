package mena.gov.bf.dashboard.entity;

import mena.gov.bf.domain.AvisDac;
import mena.gov.bf.service.dto.AvisDacDTO;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {
    private List<AvisDacDTO> avisDacExecute = new ArrayList<>();
    private List<AvisDacDTO> avisDacEnCours = new ArrayList<>();
    private List<AvisDacDTO> avisDacEnLitige = new ArrayList<>();
    private List<AvisDacDTO> avisDacResilie = new ArrayList<>();
    private List<AvisDacDTO> allAvisDac = new ArrayList<>();
    private List<String> labels = new ArrayList<>();
    private List<Integer> data = new ArrayList<>();
    private List<String> colors = new ArrayList<>();


    public List<AvisDacDTO> getAvisDacExecute() {
        return avisDacExecute;
    }

    public void setAvisDacExecute(List<AvisDacDTO> avisDacExecute) {
        this.avisDacExecute = avisDacExecute;
    }

    public List<AvisDacDTO> getAvisDacEnCours() {
        return avisDacEnCours;
    }

    public void setAvisDacEnCours(List<AvisDacDTO> avisDacEnCours) {
        this.avisDacEnCours = avisDacEnCours;
    }

    public List<AvisDacDTO> getAvisDacEnLitige() {
        return avisDacEnLitige;
    }

    public void setAvisDacEnLitige(List<AvisDacDTO> avisDacEnLitige) {
        this.avisDacEnLitige = avisDacEnLitige;
    }

    public List<AvisDacDTO> getAvisDacResilie() {
        return avisDacResilie;
    }

    public void setAvisDacResilie(List<AvisDacDTO> avisDacResilie) {
        this.avisDacResilie = avisDacResilie;
    }

    public List<AvisDacDTO> getAllAvisDac() {
        return allAvisDac;
    }

    public void setAllAvisDac(List<AvisDacDTO> allAvisDac) {
        this.allAvisDac = allAvisDac;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

}
