package gestionStages.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class OffreStage {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @NonNull
    private String titre;
    
    @NonNull
    private String description;
    
    private LocalDate dateDebut;
    
    private LocalDate dateFin;
    
    private String horaires;
    
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
    
    @OneToOne
    private Stage stage;
}
