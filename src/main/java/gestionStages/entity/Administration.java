package gestionStages.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Administration extends Utilisateur{
    
    @NonNull
    private String prenom;
    
    public Administration(String username, String password, String nom, String email, String prenom){
        super(username, password, nom, email);
        this.prenom = prenom;
    }
    
//    public List<String> documentsStage = new LinkedList<>();
    
}
