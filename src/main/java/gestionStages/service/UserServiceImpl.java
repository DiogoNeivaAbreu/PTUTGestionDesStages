package gestionStages.service;

import gestionStages.dao.EntrepriseRepository;
import gestionStages.entity.Entreprise;
import gestionStages.entity.Administration;
import lombok.extern.slf4j.Slf4j;
import gestionStages.dao.RoleRepository;
import gestionStages.dao.UserRepository;
import gestionStages.entity.Role;
import gestionStages.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    // Login et Password de l'administrateur son définis dans 'application.properties'
    @Value("${admin.login}")
    private String adminLogin;
    @Value("${admin.password}")
    private String adminPassword;
    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.nom}")
    private String adminNom;
    @Value("${admin.prenom}")
    private String adminPrenom;

    public UserServiceImpl(UserRepository userRepository, EntrepriseRepository entrepriseRepository,RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void saveUser(Utilisateur user) {
        // Par défaut on attribue le rôle 'ROLE_USER' aux nouveaux utilisateurs
        // Ce rôle est créé automatiquement au lancement de l'application
        Role normal = roleRepository.findByName("ROLE_USER").orElseThrow();
        // On crypte le mot de passe avant de l'enregistrer
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(normal);
        userRepository.save(user);
    }
    /*
        @Override
        public void saveEntreprise(Entreprise entreprise) {
            // Par défaut on attribue le rôle 'ROLE_ENTREPRISE' aux nouvelles entreprises
            // Ce rôle est créé automatiquement au lancement de l'application
            Role uneEntreprise = roleRepository.findByName("ROLE_ENTREPRISE").orElseThrow();
            // On crypte le mot de passe avant de l'enregistrer
            entreprise.setPassword(bCryptPasswordEncoder.encode(entreprise.getPassword()));
            entreprise.getRoles().add(uneEntreprise);
            entrepriseRepository.save(entreprise);
        }
    */
    @Override
    public Utilisateur findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void initializeRolesAndAdmin() {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            log.info("Création des deux rôles et de l'administrateur");
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            Role roleEntreprise = new Role("ROLE_ENTREPRISE");
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            roleRepository.save(roleEntreprise);
            Administration firstAdmin = new Administration(adminLogin, adminPassword, adminEmail);
            // On crypte le mot de passe avant de l'enregistrer
            firstAdmin.setPassword(bCryptPasswordEncoder.encode(firstAdmin.getPassword()));
            firstAdmin.getRoles().add(roleAdmin);
            userRepository.save(firstAdmin);
        }
    }
}