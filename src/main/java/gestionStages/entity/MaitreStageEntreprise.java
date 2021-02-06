package gestionStages.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class MaitreStageEntreprise {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idMSEnt;
    
    @Column(unique=true)
    @NonNull
    private String nom;
    
    @NonNull
    private String prenom;
    
    @NonNull
    private String poste;
    
    @NonNull
    private String telephone;
    
    @NonNull
    @Email
    private String email;
}
