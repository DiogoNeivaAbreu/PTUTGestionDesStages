package gestionStages.entity;

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
}
