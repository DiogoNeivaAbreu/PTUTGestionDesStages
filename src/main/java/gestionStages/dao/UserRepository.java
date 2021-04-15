package gestionStages.dao;

import gestionStages.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findByUsername(String name);
}
