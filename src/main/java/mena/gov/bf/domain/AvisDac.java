/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mena.gov.bf.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author nafolo
 */
@Entity
@Table(name = "avis_dac")
public class AvisDac extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Unique
    @Column(name = "numero_avis")
    private String numeroAvis;

    @NotNull
    @Column(name = "objet")
    private String objet;

    @Column(name = "exercice_id")
    private Long exerciceId;

    @Column(name = "mode_passation_id")
    private Long modePassationId;

    @Column(name = "date_lancement")
    private LocalDate dateLancement;

    @Column(name = "date_depot_offre")
    private LocalDate dateDepotOffre;

    @Column(name = "deleted")
    private Boolean deleted;

    @ElementCollection(targetClass=Long.class, fetch = FetchType.EAGER)
    private Set<Long> besionLigneBugetaitaireIds;

    @Column(name = "activite_id")
    private Long activiteId;

    @Column(name = "validite")
    private Integer validite;

    @OneToMany(mappedBy = "avisDac", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Lot> lots = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Lot> getLots() {
        return lots;
    }

    public void setLots(Set<Lot> lots) {
        this.lots = lots;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public AvisDac deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public Set<Long> getBesionLigneBugetaitaireIds() {
        return besionLigneBugetaitaireIds;
    }

    public void setBesionLigneBugetaitaireIds(Set<Long> besionLigneBugetaitaireIds) {
        this.besionLigneBugetaitaireIds = besionLigneBugetaitaireIds;
    }

    @Override
    public String toString() {
        return "AvisDac{" +
                "id=" + id +
                ", numeroAvis='" + numeroAvis + '\'' +
                ", objet='" + objet + '\'' +
                ", exerciceId=" + exerciceId +
                ", modePassationId=" + modePassationId +
                ", dateLancement=" + dateLancement +
                ", dateDepotOffre=" + dateDepotOffre +
                ", deleted=" + deleted +
                ", besionLigneBugetaitaireIds=" + besionLigneBugetaitaireIds +
                ", activiteId=" + activiteId +
                ", validite=" + validite +
                ", lots=" + lots +
                '}';
    }
}
