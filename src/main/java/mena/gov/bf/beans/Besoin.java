package mena.gov.bf.beans;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Besoin {
    private Long id;

    private String libelle;

    private Integer quantite;

    private Boolean deleted;

    private Boolean used;

    private Integer nombreLot;

    private Long exerciceId;

    private Long uniteAdministrativeId;

    private Long naturePrestationId;

    private Integer anneeExercice;

    private String libelleUniteAdministrative;

    private String libellenaturePrestation;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;
}
