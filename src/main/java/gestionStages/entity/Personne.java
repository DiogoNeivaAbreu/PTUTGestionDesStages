package gestionStages.entity;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Personne {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idPersonne;

    @Column(unique=true)
    @NonNull
    private String nom;
    
    @NonNull
    private String prenom;
   
    @NonNull
    private String adresse;
    
    @NonNull
    private String telephone;
    
    @NonNull
    @Email
    private String email;
}
