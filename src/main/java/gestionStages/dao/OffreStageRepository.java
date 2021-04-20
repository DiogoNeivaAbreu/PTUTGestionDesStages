package gestionStages.dao;

import gestionStages.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import gestionStages.entity.OffreStage;
import org.springframework.data.jpa.repository.Query;

// This will be AUTO IMPLEMENTED by Spring

public interface OffreStageRepository extends JpaRepository<OffreStage, Integer> {
    // public void postulerOffre(Etudiant etudiant, OffreStage offreStage);
}