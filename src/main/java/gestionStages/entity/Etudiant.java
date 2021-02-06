package gestionStages.entity;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Etudiant {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idEtudiant;

    @Column(unique=true)
    @NonNull
    private String nom;
    
    @NonNull
    private String prenom;
    
    @NonNull
    private LocalDate dateNaissance;
   
    @NonNull
    private String adresse;
    
    @NonNull
    private String telephone;
    
    @NonNull
    @Email
    private String email;
    
    @NonNull
    private LocalDate anneeEtude;
    
}
