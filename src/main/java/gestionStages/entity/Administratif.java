package gestionStages.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Administratif {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idEcole;
    
//    public List<String> documentsStage = new LinkedList<>();
    
}
