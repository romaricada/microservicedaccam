package mena.gov.bf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A PieceCandidat.
 */
@Entity
@Table(name = "piece_candidat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PieceCandidat extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("pieceCandidats")
    private CandidatLot candidatLot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("pieceCandidats")
    private Piece piece;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public PieceCandidat deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public CandidatLot getCandidatLot() {
        return candidatLot;
    }

    public PieceCandidat candidatLot(CandidatLot candidatLot) {
        this.candidatLot = candidatLot;
        return this;
    }

    public void setCandidatLot(CandidatLot candidatLot) {
        this.candidatLot = candidatLot;
    }

    public Piece getPiece() {
        return piece;
    }

    public PieceCandidat piece(Piece piece) {
        this.piece = piece;
        return this;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PieceCandidat)) {
            return false;
        }
        return id != null && id.equals(((PieceCandidat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PieceCandidat{" +
            "id=" + getId() +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
