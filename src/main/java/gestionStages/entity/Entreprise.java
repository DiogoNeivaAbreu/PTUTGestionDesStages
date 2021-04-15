package gestionStages.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Entreprise extends Utilisateur{

    @NonNull
    private String secteur;

    @OneToMany(mappedBy = "entreprise")
    List<MaitreStageEntreprise> tuteurs = new LinkedList<>();

    @OneToMany(mappedBy="proposeur")
    private List<OffreStage> listeOffres = new LinkedList<>();

    @OneToMany(mappedBy="entrepriseAccueil")
    private List<Stage> stagesProposes = new LinkedList<>();

    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @Setter(AccessLevel.NONE)
    private List<Role> roles = new LinkedList<>();
    */

    public void AjouterOffre(OffreStage offre){
        // TODO
    }

    public void AccepterOffre(OffreStage offre){
        // TODO
    }

    public void DesignerMaitreStage(MaitreStageEntreprise tuteur, Stage stage){
        // TODO
    }
}
