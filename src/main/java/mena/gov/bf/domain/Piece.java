package mena.gov.bf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Piece.
 */
@Entity
@Table(name = "piece")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Piece extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nom_piece", nullable = false)
    private String nomPiece;

    @NotNull
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @OneToMany(mappedBy = "piece")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PieceCandidat> pieceCandidats = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPiece() {
        return nomPiece;
    }

    public Piece nomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
        return this;
    }

    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Piece deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<PieceCandidat> getPieceCandidats() {
        return pieceCandidats;
    }

    public Piece pieceCandidats(Set<PieceCandidat> pieceCandidats) {
        this.pieceCandidats = pieceCandidats;
        return this;
    }

    public Piece addPieceCandidats(PieceCandidat pieceCandidat) {
        this.pieceCandidats.add(pieceCandidat);
        pieceCandidat.setPiece(this);
        return this;
    }

    public Piece removePieceCandidats(PieceCandidat pieceCandidat) {
        this.pieceCandidats.remove(pieceCandidat);
        pieceCandidat.setPiece(null);
        return this;
    }

    public void setPieceCandidats(Set<PieceCandidat> pieceCandidats) {
        this.pieceCandidats = pieceCandidats;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece)) {
            return false;
        }
        return id != null && id.equals(((Piece) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Piece{" +
            "id=" + getId() +
            ", nomPiece='" + getNomPiece() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
