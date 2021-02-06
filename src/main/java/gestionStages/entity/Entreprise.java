package gestionStages.entity;

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
}
