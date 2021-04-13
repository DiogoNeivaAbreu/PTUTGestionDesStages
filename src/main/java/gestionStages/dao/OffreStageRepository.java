package gestionStages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import gestionStages.entity.OffreStage;

// This will be AUTO IMPLEMENTED by Spring 

public interface OffreStageRepository extends JpaRepository<OffreStage, Integer> {
        //void postulerOffre(OffreStage offreStage);
}
