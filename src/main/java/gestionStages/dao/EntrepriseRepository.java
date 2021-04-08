package gestionStages.dao;

import gestionStages.entity.Entreprise;
import gestionStages.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer>{

}
