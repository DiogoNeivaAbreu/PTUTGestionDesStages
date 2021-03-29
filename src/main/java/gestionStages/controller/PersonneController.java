package gestionStages.controller;

import gestionStages.dao.PersonneRepository;
import gestionStages.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/personne")
public class PersonneController {
    @Autowired
    private PersonneRepository dao;

    /**
     * Affiche toutes les informations d'une personne inscrite dans le site
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('monProfil.html')
     */
    @GetMapping(path = "show")
    public String afficheToutesLesPersonnes(Model model) {
        model.addAttribute("personne", dao.findAll());
        return "monProfil";
    }

    /**
     * Montre le formulaire permettant d'ajouter une nouvelle personne dans la base
     *
     * @param personne initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @return le nom de la vue à afficher ('formulaireMonProfil.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("personne") Personne personne) {
        return "formulaireMonProfil";
    }

    /**
     * Appelé par 'formulaireMonProfil.html', méthode POST
     *
     * @param personne Une personne initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des personnes
     */
    @PostMapping(path = "save")
    public String ajoutePersonnePuisMontreLaListe(Personne personne, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            dao.save(personne);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = personne.getPrenom() + personne.getNom() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : " + personne.getPrenom() + personne.getNom() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'monProfil.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }

    /**
     * Appelé par le lien 'Supprimer' dans 'monProfil.html'
     *
     * @param personne à partir de l'id de la personne transmis en paramètre, Spring fera une requête SQL SELECT pour
     * chercher la galerie dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des personnes
     */
    @GetMapping(path = "delete")
    public String supprimeUneOffrePuisMontreLaListe(@RequestParam("id") Personne personne, RedirectAttributes redirectInfo) {
        String message = personne.getPrenom() + personne.getNom() + "' a bien été supprimée";
        try {
            dao.delete(personne); // Ici on peut avoir une erreur 
        } catch (DataIntegrityViolationException e) {
            // violation de contrainte d'intégrité si on essaie de supprimer une offre de stage
            message = "Erreur : Impossible de supprimer '" + personne.getPrenom() + personne.getNom() +"'";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'monProfil.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
}
