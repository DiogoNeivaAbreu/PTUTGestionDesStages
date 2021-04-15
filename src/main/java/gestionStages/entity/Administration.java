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
    
    public Administration(String username, String password, String email){
        super(username, password, email);
    }
    
//    public List<String> documentsStage = new LinkedList<>();
    
}
