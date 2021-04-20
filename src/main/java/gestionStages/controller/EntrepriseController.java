package gestionStages.controller;

import gestionStages.dao.EntrepriseRepository;
import gestionStages.entity.Entreprise;
import gestionStages.service.SecurityService;
import gestionStages.service.UserService;
import gestionStages.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Affiche toutes les informations d'un étudiant inscrit dans le site
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('tousLesEtudiants.html')
     */
    @GetMapping(path = "/show")
    public String afficheTousLesEtudiants(Model model) {
        model.addAttribute("entreprises", dao.findAll());
        return "toutesLesEntreprises";
    }

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

        return "redirect:/";
    }
}
