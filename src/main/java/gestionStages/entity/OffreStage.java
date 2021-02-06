package gestionStages.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class OffreStage {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idOffre;

    @Column(unique=true)
    @NonNull
    private String titre;
    
    @NonNull
    private String description;
    
    @NonNull
    private LocalDate dateDebut;
    
    @NonNull
    private LocalDate dateFin;
    
    @NonNull
    private String horaires;
    
    @NonNull
    private String niveauRequis;
    
    @NonNull
    private Boolean Accepté;
}
