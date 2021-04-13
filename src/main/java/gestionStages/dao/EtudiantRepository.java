package gestionStages.dao;

import gestionStages.entity.Etudiant;
import gestionStages.entity.OffreStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer>{
    
}
