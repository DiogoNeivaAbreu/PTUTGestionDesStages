package gestionStages.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class MaitreStageEcole {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idMSEc;
    
    @Column(unique=true)
    @NonNull
    private String nom;
    
    @NonNull
    private String prenom;
    
    @NonNull
    private String matierEnseignee;
    
    @NonNull
    private String telephone;
    
    @NonNull
    @Email
    private String email;

}
