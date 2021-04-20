package gestionStages.dao;

import gestionStages.entity.Administration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administration, Integer> {
    Administration findByUsername(String string);
}
