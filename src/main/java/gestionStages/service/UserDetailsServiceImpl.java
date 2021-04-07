package gestionStages.service;

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
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Utilisateur user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return user;
    }
}