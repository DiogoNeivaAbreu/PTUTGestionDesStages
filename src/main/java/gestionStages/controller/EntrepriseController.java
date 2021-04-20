package gestionStages.controller;

import gestionStages.dao.EntrepriseRepository;
import gestionStages.entity.Entreprise;
import gestionStages.entity.Etudiant;
import gestionStages.service.SecurityService;
import gestionStages.service.UserService;
import gestionStages.validator.UserValidator;
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

@Controller
@RequestMapping(path = "/entreprise")
public class EntrepriseController {

    private final UserService userService;

    private final SecurityService securityService;

    private final UserValidator userValidator;

    @Autowired
    private EntrepriseRepository dao;

    public EntrepriseController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    /**
     * Permet à une entreprise d'accéder à sa page utilisateur
     */
    @Secured({"ROLE_ENTREPRISE"}) // Réservé aux utilisateurs qui ont le rôle 'ROLE_ETUDIANT'
    @GetMapping(path = "/")
    public String montrePageUtilisateur(
            @AuthenticationPrincipal Entreprise entreprise,  // Les infos de l'utilisateur connecté
            Model model) {
        return "accueil/entreprise"; // On affiche la vue 'entreprise.html'
    }

    /**
     * Permet à un administrateur de voir toutes les entreprises de l'application
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping(path = "/show")
    public String afficheTousLesEtudiants(Model model) {
        model.addAttribute("entreprises", dao.findAll(Sort.by(Sort.Direction.ASC, "nom")));
        return "toutesLesEntreprises";
    }

    /**
     * Création de compte entreprises avec gestion des erreurs
     */
    @GetMapping("/creerUnCompte")
    public String registration(Model model) {
        model.addAttribute("userForm", new Entreprise());
        return "creerUnCompteEntreprise";
    }

    @PostMapping("/creerUnCompte")
    public String registration(@Valid @ModelAttribute("userForm") Entreprise userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "creerUnCompteEntreprise";
        }

        userService.saveEntreprise(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/entreprise/";
    }
}
