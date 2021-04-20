package gestionStages.controller;

import lombok.extern.slf4j.Slf4j;
import gestionStages.entity.Utilisateur;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(path = "/admin")
@Secured("ROLE_ADMIN") // Réservé aux utilisateurs qui ont le rôle 'ROLE_ADMIN'
public class AdminController {
    /**
     * Permet à un administrateur d'accéder à sa page utilisateur
     */
    @GetMapping(path = "/")
    public String montreLaPageAdmin(@AuthenticationPrincipal Utilisateur user, Model model) { // Les infos de l'utilisateur connecté
        return "accueil/admin"; // On affiche la vue 'admin.html'
    }
}