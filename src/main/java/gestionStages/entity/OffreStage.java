package gestionStages.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.NonNull;
import lombok.ToString;

public class OffreStage extends Stage{
    
    @NonNull
    private String niveauRequis;
    
    @NonNull
    private Boolean accepté;
    
    @NonNull
    private Boolean entretien;
    
    @NonNull
    private String CV;
    
    @NonNull
    private String lettreMotivation;
    
    @ManyToMany
    @ToString.Exclude
    List<Etudiant> candidats = new LinkedList<>();
    
    @ManyToOne
    Entreprise proposeur;
}
