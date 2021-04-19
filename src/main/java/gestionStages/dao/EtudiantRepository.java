package gestionStages.dao;

import gestionStages.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer>{
    Etudiant findByUsername(String string);
}