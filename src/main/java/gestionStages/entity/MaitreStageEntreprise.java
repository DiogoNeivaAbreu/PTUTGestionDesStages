package gestionStages.entity;

import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class MaitreStageEntreprise extends Personne{

    //@NonNull
    private String poste;

    @ManyToOne
    Entreprise entreprise;
}