package gestionStages.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Ecole {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idEcole;

    @Column(unique=true)
    @NonNull
    private String nom;
    
    @NonNull
    private String adresse;
    
    @NonNull
    private String telephone;
    
    @NonNull
    @Email
    private String email;
    
    @NonNull
    private String domaine;
    
    @NonNull
    private String description;
    
    @OneToMany(mappedBy="ecole")
    @ToString.Exclude
    private List<MaitreStageEcole> tuteursecole = new LinkedList<>();
    
    @OneToMany(mappedBy="univ")
    @ToString.Exclude
    private List<Etudiant> listeEtudiants = new LinkedList<>();
//    
//    private List<MaitreStageEcole> etudiants = new LinkedList<>();
//
//    public List<Entreprise> archiveEntreprises = new LinkedList<>();
    
    public List<Entreprise> ArchiveEntreprisePartenaire(){
        
        //TODO
        return null;
    }
    
    public void ValiderOffre(OffreStage offre){
        // TODO 
    }
    
    public void DesignerMaitreStage(MaitreStageEcole tuteurecole, Stage stage){
        // TODO 
    }
    
    public void VerifierStage(Stage stage){
        // TODO 
    }    
}
