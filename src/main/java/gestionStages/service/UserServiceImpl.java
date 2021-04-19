package gestionStages.service;

import gestionStages.dao.*;
import gestionStages.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final AdministrateurRepository administrateurRepository;
    private final EtudiantRepository etudiantRepository;
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

    public UserServiceImpl(EntrepriseRepository entrepriseRepository,
                           AdministrateurRepository administrateurRepository,
                           EtudiantRepository etudiantRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.entrepriseRepository = entrepriseRepository;
        this.administrateurRepository =  administrateurRepository;
        this.etudiantRepository = etudiantRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /*@Override
    public void saveUser(Utilisateur user) {
        // Par défaut on attribue le rôle 'ROLE_USER' aux nouveaux utilisateurs
        // Ce rôle est créé automatiquement au lancement de l'application
        Role normal = roleRepository.findByName("ROLE_USER").orElseThrow();
        // On crypte le mot de passe avant de l'enregistrer
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(normal);
        userRepository.save(user);
    }
     */

    @Override
    public void saveEtudiant(Etudiant etudiant){
        Role normal = roleRepository.findByName("ROLE_ETUDIANT").orElseThrow();
        etudiant.setPassword(bCryptPasswordEncoder.encode(etudiant.getPassword()));
        etudiant.getRoles().add(normal);
        etudiantRepository.save(etudiant);
    }

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

    @Override
    public Etudiant findByUsername(String username){
        return etudiantRepository.findByUsername(username);
    }

    @Override
    public Entreprise findCompanyByUsername(String username){
        return entrepriseRepository.findCompanyByUsername(username);
    }




    /*
    @Override
    public Utilisateur findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
     */

    @Override
    public void initializeRolesAndAdmin() {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            log.info("Création des rôles et de l'administrateur");
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleEtudiant = new Role("ROLE_ETUDIANT");
            Role roleEntreprise = new Role("ROLE_ENTREPRISE");
            roleRepository.save(roleAdmin);
            roleRepository.save(roleEtudiant);
            roleRepository.save(roleEntreprise);
            Administration firstAdmin = new Administration(adminLogin, adminPassword, adminNom, adminEmail, adminPrenom);
            // On crypte le mot de passe avant de l'enregistrer
            firstAdmin.setPassword(bCryptPasswordEncoder.encode(firstAdmin.getPassword()));
            firstAdmin.getRoles().add(roleAdmin);
            administrateurRepository.save(firstAdmin);
        }
    }
}