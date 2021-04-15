package gestionStages.controller;

import gestionStages.dao.EtudiantRepository;
import gestionStages.entity.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/etudiant")
public class EtudiantController {
    @Autowired
    private EtudiantRepository dao;

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
}