package mena.gov.bf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Piece} entity.
 */
public class PieceDTO implements Serializable {

    private Long id;

    @NotNull
    private String nomPiece;

    @NotNull
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPiece() {
        return nomPiece;
    }

    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PieceDTO pieceDTO = (PieceDTO) o;
        if (pieceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pieceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PieceDTO{" +
            "id=" + getId() +
            ", nomPiece='" + getNomPiece() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
