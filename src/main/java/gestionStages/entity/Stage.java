package gestionStages.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Stage {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    private String titre;
    
    @NonNull
    private String description;
    
    private LocalDate dateDebut;
    
    private LocalDate dateFin;
    
    private String horaires;
    
    private EtatStage etatStage;
    
    @OneToOne
    private OffreStage offre;
    
    @OneToOne
    private Etudiant stagiaire;
    
    @OneToOne
    Entreprise entrepriseAccueil;
}
