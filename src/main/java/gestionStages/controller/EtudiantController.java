package gestionStages.controller;

import gestionStages.dao.EtudiantRepository;
import gestionStages.entity.Etudiant;
import gestionStages.service.SecurityService;
import gestionStages.service.UserService;
import gestionStages.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    /*
    @Secured({"ROLE_ETUDIANT"})
    @GetMapping(path = "modifierProfil")
    public String modifierProfilUtilisateur(@AuthenticationPrincipal Etudiant etudiant, Model model){
        return "modifierProfil";
    }
     */
    @Secured({"ROLE_ETUDIANT"})
    @GetMapping("/modifierProfil/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model) {
        Etudiant etudiant = dao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id étudiant inconnu :" + id));

        model.addAttribute("etudiant", etudiant);
        return "modifierProfilTest";
    }
    /**
     *Ne fonctionne qu'avec des données au format JSON
     */
    /*
    @PutMapping("/modifierProfil/{id}")
    public Etudiant updateUser(@PathVariable(value = "id") Integer id,
                             @Valid @RequestBody Etudiant changementsEtudiant) throws ResourceNotFoundException {
        Etudiant etudiantAModifier = dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun étudiant n'a été trouvé avec l'id : " + id));

            etudiantAModifier.setNom(changementsEtudiant.getNom());
            etudiantAModifier.setPrenom(changementsEtudiant.getPrenom());
            etudiantAModifier.setUsername(changementsEtudiant.getUsername());
            etudiantAModifier.setAdresse(changementsEtudiant.getAdresse());
            etudiantAModifier.setTelephone(changementsEtudiant.getTelephone());
            etudiantAModifier.setEmail(changementsEtudiant.getEmail());
            etudiantAModifier.setAnneeEtude(changementsEtudiant.getAnneeEtude());
            etudiantAModifier.setPassword(changementsEtudiant.getPassword());
            etudiantAModifier.setPasswordConfirm(changementsEtudiant.getPasswordConfirm());

            dao.save(etudiantAModifier);
            log.info("L'étudiant est enregistré ici");

        return etudiantAModifier;

     */

    /**
     *Ne modifie pas l'éutiant connecté
     */
    /*@PostMapping("/modifierProfil/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid Etudiant etudiant,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            etudiant.setId(id);
            return "update-user";
        }

        dao.save(etudiant);
        return "redirect:/etudiant/";
    }
     */

}