package gestionStages.service;

import gestionStages.entity.Entreprise;
import gestionStages.entity.Etudiant;
import gestionStages.entity.Utilisateur;

public interface UserService {
    void initializeRolesAndAdmin();

    //void saveUser(Utilisateur user);
    //Utilisateur findByUserName(String username);

    void saveEtudiant(Etudiant etudiant);
    Etudiant findByUsername(String username);

    void saveEntreprise(Entreprise entreprise);
    Entreprise findCompanyByUsername(String username);


}
