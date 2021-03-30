package gestionStages.entity;

import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class MaitreStageEcole extends Personne{

    //@NonNull
    private String matiereEnseignee;
    
    @ManyToOne
    Ecole ecole;
}
