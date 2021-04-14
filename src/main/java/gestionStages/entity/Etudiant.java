package gestionStages.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity // Une entité JPA
public class Etudiant extends Utilisateur{

    @NonNull
    private String prenom;
    
    @NonNull
    private String anneeEtude;
    
    @ManyToMany(mappedBy= "candidats")
    @ToString.Exclude
    private List<OffreStage> listeOffres = new LinkedList<>();
    
    @ManyToOne 
    Ecole univ;
    
    @OneToOne
    private Stage stageEtudiant;
    
    public void postulerOffre(OffreStage offreStage) {
        listeOffres.add(offreStage);
    }
    
    public void AjouterDesDocuments(Administratif documents){
        // TODO 
    }
}
