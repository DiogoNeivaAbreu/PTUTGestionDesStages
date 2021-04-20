package gestionStages.service;

import gestionStages.dao.AdministrateurRepository;
import gestionStages.dao.EntrepriseRepository;
import gestionStages.dao.EtudiantRepository;
import gestionStages.entity.Administration;
import gestionStages.entity.Entreprise;
import gestionStages.entity.Etudiant;
import lombok.extern.slf4j.Slf4j;
import gestionStages.dao.UserRepository;
import gestionStages.entity.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final EtudiantRepository etudiantRepository;
    private final AdministrateurRepository administrateurRepository;
    private final EntrepriseRepository entrepriseRepository;

    public UserDetailsServiceImpl(EtudiantRepository etudiantRepository, AdministrateurRepository administrateurRepository, EntrepriseRepository entrepriseRepository) {
        this.etudiantRepository = etudiantRepository;
        this.administrateurRepository = administrateurRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Etudiant etudiant = etudiantRepository.findByUsername(username);
        Entreprise entreprise = entrepriseRepository.findCompanyByUsername(username);
        Administration administration = administrateurRepository.findByUsername(username);

        if (etudiant == null && entreprise == null && administration == null){
            throw new UsernameNotFoundException(username);
        }
        if (etudiant != null){
            return etudiant;
        }
        else if (entreprise != null){
            return entreprise;
        }
        else if(administration != null) {
            return administration;
        }
        //Inutile mais il fallait une valeur de retour
        return administration;
    }
}
