package gestionStages.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Entreprise {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idEntreprise;

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
    private String secteur;
    
    @OneToMany(mappedBy = "entreprise")
    List<MaitreStageEntreprise> tuteurs = new LinkedList<>();
    
    @OneToMany(mappedBy="proposeur")
    @ToString.Exclude
    private List<OffreStage> listeOffres = new LinkedList<>();
    
    public void AjouterOffre(OffreStage offre){
        // TODO 
    }
    
    public void AccepterOffre(OffreStage offre){
        // TODO 
    }
    
    public void DesignerMaitreStage(MaitreStageEntreprise tuteur, Stage stage){
        // TODO 
    }
}
    
