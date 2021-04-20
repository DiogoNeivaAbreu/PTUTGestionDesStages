package gestionStages.controller;

import gestionStages.dao.EtudiantRepository;
import gestionStages.entity.Etudiant;
import gestionStages.entity.Utilisateur;
import gestionStages.service.SecurityService;
import gestionStages.service.UserService;
import gestionStages.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/")
    public String montrePageUtilisateur(
            @AuthenticationPrincipal Etudiant etudiant,  // Les infos de l'utilisateur connecté
            Model model) {
        return "accueil/etudiant"; // On affiche la vue 'pageUser.html'
    }

    /**
     * Affiche toutes les informations d'un étudiant inscrit dans le site
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('tousLesEtudiants.html')
     */
    @GetMapping(path = "show")
    public String afficheTousLesEtudiants(Model model) {
        model.addAttribute("etudiants", dao.findAll());
        return "tousLesEtudiants";
    }

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

        return "redirect:/";
    }

    @GetMapping(path = "modifierProfil")
    public String modifierProfilUtilisateur(@AuthenticationPrincipal Etudiant etudiant, Model model){
        return "modifierProfil";
    }
}