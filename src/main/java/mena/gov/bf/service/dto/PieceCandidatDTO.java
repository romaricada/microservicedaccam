package mena.gov.bf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.PieceCandidat} entity.
 */
public class PieceCandidatDTO implements Serializable {

    private Long id;

    @NotNull
    private Boolean deleted;

    private Long candidatLotId;

    private Long pieceId;

    private PieceDTO piece = new PieceDTO();

    private  Boolean valide;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getCandidatLotId() {
        return candidatLotId;
    }

    public void setCandidatLotId(Long candidatLotId) {
        this.candidatLotId = candidatLotId;
    }

    public Long getPieceId() {
        return pieceId;
    }

    public void setPieceId(Long pieceId) {
        this.pieceId = pieceId;
    }

    public PieceDTO getPiece() {
        return piece;
    }

    public void setPiece(PieceDTO piece) {
        this.piece = piece;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PieceCandidatDTO pieceCandidatDTO = (PieceCandidatDTO) o;
        if (pieceCandidatDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pieceCandidatDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PieceCandidatDTO{" +
            "id=" + getId() +
            ", deleted='" + isDeleted() + "'" +
            ", candidatLot=" + getCandidatLotId() +
            ", piece=" + getPieceId() +
            "}";
    }
}
