package gestionStages.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import gestionStages.entity.Utilisateur;

@Entity
// Lombok
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @NonNull
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private List<Utilisateur> users = new LinkedList<>();
}
