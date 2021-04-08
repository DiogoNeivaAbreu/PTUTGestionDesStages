package gestionStages.service;

import gestionStages.entity.Entreprise;
import gestionStages.entity.Utilisateur;

public interface UserService {
    void initializeRolesAndAdmin();

    void saveUser(Utilisateur user);

    void saveEntreprise(Entreprise entreprise);

    Utilisateur findByUserName(String username);
}
