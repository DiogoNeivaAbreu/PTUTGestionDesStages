package gestionStages.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity // Une entité JPA
public class Etudiant extends Personne{

    //@NonNull
    private String anneeEtude;
    
    @ManyToMany(mappedBy= "candidats")
    @ToString.Exclude
    private List<OffreStage> listeoffres = new LinkedList<>();
    
    @ManyToOne 
    Ecole univ;
    
    @OneToOne
    private Stage stageEtudiant;
    
    public void PostulerOffre(OffreStage offre){
        listeoffres.add(offre);
    }
    
    public void AjouterDesDocuments(Administratif documents){
        // TODO 
    }
}
