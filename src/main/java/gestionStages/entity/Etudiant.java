package gestionStages.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity // Une entité JPA
public class Etudiant extends Personne{

    @NonNull
    private LocalDate anneeEtude;
    
    @ManyToMany(mappedBy= "candidats")
    @ToString.Exclude
    private List<OffreStage> listeoffres = new LinkedList<>();
    
    @ManyToOne 
    Ecole univ;
    
    public void PostulerOffre(OffreStage offre, Entreprise entreprise){
        // TODO 
    }
    
    public void AjouterDesDocuments(Administratif documents){
        // TODO 
    }
}
