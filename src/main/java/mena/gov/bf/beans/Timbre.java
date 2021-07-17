package mena.gov.bf.beans;

import javax.persistence.Lob;
import java.util.Arrays;

public class Timbre {
    private Long id;

    private String code;

    private String sigle;

    private String libelle;

    private String pays;

    private String devise;

    @Lob
    private byte[] logo;

    private String logoContentType;
    private String identiteMinistre;

    private String titreMinistre;


    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for property 'id'.
     *
     * @param id Value to set for property 'id'.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for property 'code'.
     *
     * @return Value for property 'code'.
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for property 'code'.
     *
     * @param code Value to set for property 'code'.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for property 'sigle'.
     *
     * @return Value for property 'sigle'.
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * Setter for property 'sigle'.
     *
     * @param sigle Value to set for property 'sigle'.
     */
    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    /**
     * Getter for property 'libelle'.
     *
     * @return Value for property 'libelle'.
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Setter for property 'libelle'.
     *
     * @param libelle Value to set for property 'libelle'.
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter for property 'pays'.
     *
     * @return Value for property 'pays'.
     */
    public String getPays() {
        return pays;
    }

    /**
     * Setter for property 'pays'.
     *
     * @param pays Value to set for property 'pays'.
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Getter for property 'devise'.
     *
     * @return Value for property 'devise'.
     */
    public String getDevise() {
        return devise;
    }

    /**
     * Setter for property 'devise'.
     *
     * @param devise Value to set for property 'devise'.
     */
    public void setDevise(String devise) {
        this.devise = devise;
    }

    /**
     * Getter for property 'logo'.
     *
     * @return Value for property 'logo'.
     */
    public byte[] getLogo() {
        return logo;
    }

    /**
     * Setter for property 'logo'.
     *
     * @param logo Value to set for property 'logo'.
     */
    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    /**
     * Getter for property 'logoContentType'.
     *
     * @return Value for property 'logoContentType'.
     */
    public String getLogoContentType() {
        return logoContentType;
    }

    /**
     * Setter for property 'logoContentType'.
     *
     * @param logoContentType Value to set for property 'logoContentType'.
     */
    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    /**
     * Getter for property 'identiteMinistre'.
     *
     * @return Value for property 'identiteMinistre'.
     */
    public String getIdentiteMinistre() {
        return identiteMinistre;
    }

    /**
     * Setter for property 'identiteMinistre'.
     *
     * @param identiteMinistre Value to set for property 'identiteMinistre'.
     */
    public void setIdentiteMinistre(String identiteMinistre) {
        this.identiteMinistre = identiteMinistre;
    }

    @Override
    public String toString() {
        return "Timbre{" +
            "id=" + id +
            ", code='" + code + '\'' +
            ", sigle='" + sigle + '\'' +
            ", libelle='" + libelle + '\'' +
            ", pays='" + pays + '\'' +
            ", devise='" + devise + '\'' +
            ", logo=" + Arrays.toString(logo) +
            ", logoContentType='" + logoContentType + '\'' +
            ", identiteMinistre='" + identiteMinistre + '\'' +
            ", titreMinistre='" + titreMinistre + '\'' +
            '}';
    }

    /**
     * Getter for property 'titreMinistre'.
     *
     * @return Value for property 'titreMinistre'.
     */
    public String getTitreMinistre() {
        return titreMinistre;
    }

    /**
     * Setter for property 'titreMinistre'.
     *
     * @param titreMinistre Value to set for property 'titreMinistre'.
     */
    public void setTitreMinistre(String titreMinistre) {
        this.titreMinistre = titreMinistre;
    }
}
