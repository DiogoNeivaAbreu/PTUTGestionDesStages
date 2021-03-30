package gestionStages.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class OffreStage extends Stage{
    
    //@NonNull
    private String niveauRequis;
    
    //@NonNull
    private Boolean accepte;
    
    //@NonNull
    private Boolean entretien;
    
    //@NonNull
    private String CV;
    
    //@NonNull
    private String lettreMotivation;
    
    @ManyToMany
    @ToString.Exclude
    List<Etudiant> candidats = new LinkedList<>();
    
    @ManyToOne
    Entreprise proposeur;
}
