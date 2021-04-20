package gestionStages.controller;

import gestionStages.dao.EtudiantRepository;
import gestionStages.entity.Etudiant;
import gestionStages.entity.Utilisateur;
import gestionStages.service.SecurityService;
import gestionStages.service.UserService;
import gestionStages.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Slf4j
@Controller
@RequestMapping(path = "/etudiant")
public class EtudiantController {

    private final UserService userService;

    private final SecurityService securityService;

    private final UserValidator userValidator;

    @Autowired
    private EtudiantRepository dao;

    public EtudiantController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    /**
     * Permet à un étudiant d'accéder à sa page utilisateur
     */
    @Secured({"ROLE_ETUDIANT"}) // Réservé aux utilisateurs qui ont le rôle 'ROLE_ETUDIANT'
    @GetMapping(path = "/")
    public String montrePageUtilisateur(
            @AuthenticationPrincipal Etudiant etudiant, Model model) { // Les infos de l'utilisateur connecté
        return "accueil/etudiant"; // On affiche la vue 'etudiant.html'
    }

    /**
     * Permet à un administrateur de voir tous les étudiants de l'application
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "show")
    public String afficheTousLesEtudiants(Model model) {
        model.addAttribute("etudiants", dao.findAll(Sort.by(Sort.Direction.ASC, "nom")));
        return "tousLesEtudiants";
    }

    /**
     * Création de compte étudiant avec gestion des erreurs et enregistrement
     */
    @GetMapping("/creerUnCompte")
    public String registration(Model model) {
        model.addAttribute("userForm", new Etudiant());
        return "creerUnCompte";
    }
    @PostMapping("/creerUnCompte")
    public String registration(@Valid @ModelAttribute("userForm") Etudiant userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "creerUnCompte";
        }

        userService.saveEtudiant(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/etudiant/";
    }

    /**
     * Permet à un étudiant de modifier son profil
     */
    @Secured({"ROLE_ETUDIANT"})
    @GetMapping(path = "modifierProfil")
    public String modifierProfilUtilisateur(@AuthenticationPrincipal Etudiant etudiant, Model model){
        return "modifierProfil";
    }
}