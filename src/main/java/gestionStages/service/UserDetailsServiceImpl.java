package gestionStages.service;

import gestionStages.dao.EtudiantRepository;
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

    public UserDetailsServiceImpl(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Etudiant etudiant = etudiantRepository.findByUsername(username);
        if (etudiant == null) throw new UsernameNotFoundException(username);

        return etudiant;
    }
}
