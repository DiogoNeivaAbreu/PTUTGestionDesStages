package gestionStages.entity;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Personne {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String nom;
    
    @NonNull
    private String prenom;
   
    @NonNull
    private String adresse;
    
    @NonNull
    private String telephone;
    
    @Email
    private String email;
}
