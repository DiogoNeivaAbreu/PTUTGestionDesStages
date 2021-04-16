package gestionStages.dao;

import gestionStages.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Integer>{
    
}
