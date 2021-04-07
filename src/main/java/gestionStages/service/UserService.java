package gestionStages.service;

import gestionStages.entity.Utilisateur;

public interface UserService {
    void initializeRolesAndAdmin();

    void save(Utilisateur user);

    Utilisateur findByUserName(String username);
}
