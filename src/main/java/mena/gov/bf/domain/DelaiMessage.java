package mena.gov.bf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import mena.gov.bf.domain.enumeration.TypeMessage;

/**
 * A DelaiMessage.
 */
@Entity
@Table(name = "delai_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DelaiMessage extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "temps_alerte")
    private Integer tempsAlerte;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_message", nullable = false)
    private TypeMessage typeMessage;

    @Column(name = "message")
    private String message;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTempsAlerte() {
        return tempsAlerte;
    }

    public DelaiMessage tempsAlerte(Integer tempsAlerte) {
        this.tempsAlerte = tempsAlerte;
        return this;
    }

    public void setTempsAlerte(Integer tempsAlerte) {
        this.tempsAlerte = tempsAlerte;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public DelaiMessage typeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
        return this;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public String getMessage() {
        return message;
    }

    public DelaiMessage message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Boolean isDeleted() {
        return deleted;
    }

    public DelaiMessage deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DelaiMessage)) {
            return false;
        }
        return id != null && id.equals(((DelaiMessage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DelaiMessage{" +
            "id=" + getId() +
            ", tempsAlerte=" + getTempsAlerte() +
            ", typeMessage='" + getTypeMessage() + "'" +
            ", message='" + getMessage() + "'" +

            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
