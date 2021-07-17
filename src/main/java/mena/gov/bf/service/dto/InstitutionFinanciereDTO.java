package mena.gov.bf.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link mena.gov.bf.domain.InstitutionFinanciere} entity.
 */
public class InstitutionFinanciereDTO implements Serializable {
    
    private Long id;

    private String libelle;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstitutionFinanciereDTO)) {
            return false;
        }

        return id != null && id.equals(((InstitutionFinanciereDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstitutionFinanciereDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
