package mena.gov.bf.beans;

import mena.gov.bf.dashboard.entity.LotInfoDTO;
import mena.gov.bf.domain.enumeration.EtatMarche;
import mena.gov.bf.service.dto.TacheDTO;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Activite {
    private Long id;

    private String codeLignePlan;

    private String gestionnaireCredit;

    private EtatMarche etatMarche;

    private Boolean deleted;

    private Boolean reported;

    private Long passationId;

    private String modePassationLibelle;

    private Long naturePrestationId;

    private String naturePrestationLibelle;

    private Set<BesoinLigneBudgetaire> besoinLignes = new HashSet<>();

    private Set<PpmActivite> ppmActivites = new HashSet<>();

    private PpmActivite ppmActivite;

    private Double total;

    private Double etatAvancement;

    private String nomActivite;

    private List<Besoin> besoins = new ArrayList<>();

    private List<ReferentielDelai> referentielDelais = new ArrayList<>();

    private List<TacheDTO> taches = new ArrayList<>();

    private List<LotInfoDTO> lotInfoDTOS = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeLignePlan() {
        return codeLignePlan;
    }

    public void setCodeLignePlan(String codeLignePlan) {
        this.codeLignePlan = codeLignePlan;
    }

    public String getGestionnaireCredit() {
        return gestionnaireCredit;
    }

    public void setGestionnaireCredit(String gestionnaireCredit) {
        this.gestionnaireCredit = gestionnaireCredit;
    }

    public EtatMarche getEtatMarche() {
        return etatMarche;
    }

    public void setEtatMarche(EtatMarche etatMarche) {
        this.etatMarche = etatMarche;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getReported() {
        return reported;
    }

    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    public Long getPassationId() {
        return passationId;
    }

    public void setPassationId(Long passationId) {
        this.passationId = passationId;
    }

    public String getModePassationLibelle() {
        return modePassationLibelle;
    }

    public void setModePassationLibelle(String modePassationLibelle) {
        this.modePassationLibelle = modePassationLibelle;
    }

    public Long getNaturePrestationId() {
        return naturePrestationId;
    }

    public void setNaturePrestationId(Long naturePrestationId) {
        this.naturePrestationId = naturePrestationId;
    }

    public String getNaturePrestationLibelle() {
        return naturePrestationLibelle;
    }

    public void setNaturePrestationLibelle(String naturePrestationLibelle) {
        this.naturePrestationLibelle = naturePrestationLibelle;
    }

    public Set<BesoinLigneBudgetaire> getBesoinLignes() {
        return besoinLignes;
    }

    public void setBesoinLignes(Set<BesoinLigneBudgetaire> besoinLignes) {
        this.besoinLignes = besoinLignes;
    }

    public Set<PpmActivite> getPpmActivites() {
        return ppmActivites;
    }

    public void setPpmActivites(Set<PpmActivite> ppmActivites) {
        this.ppmActivites = ppmActivites;
    }

    public PpmActivite getPpmActivite() {
        return ppmActivite;
    }

    public void setPpmActivite(PpmActivite ppmActivite) {
        this.ppmActivite = ppmActivite;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Besoin> getBesoins() {
        return besoins;
    }

    public void setBesoins(List<Besoin> besoins) {
        this.besoins = besoins;
    }

    public List<ReferentielDelai> getReferentielDelais() {
        return referentielDelais;
    }

    public void setReferentielDelais(List<ReferentielDelai> referentielDelais) {
        this.referentielDelais = referentielDelais;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public Double getEtatAvancement() {
        return etatAvancement;
    }

    public void setEtatAvancement(Double etatAvancement) {
        this.etatAvancement = etatAvancement;
    }

    public List<TacheDTO> getTaches() {
        return taches;
    }

    public void setTaches(List<TacheDTO> taches) {
        this.taches = taches;
    }

    public List<LotInfoDTO> getLotInfoDTOS() {
        return lotInfoDTOS;
    }

    public void setLotInfoDTOS(List<LotInfoDTO> lotInfoDTOS) {
        this.lotInfoDTOS = lotInfoDTOS;
    }
}
