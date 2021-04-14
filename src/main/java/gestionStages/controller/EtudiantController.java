package gestionStages.controller;

import gestionStages.dao.EtudiantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/etudiant")
public class EtudiantController {
    
    private EtudiantRepository dao;
    
    /**
     * Affiche toutes les informations d'une personne inscrite dans le site
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('tousLesEtudiants.html')
     */
    @GetMapping(path = "tousLesEtudiants")
    public String afficheToutesLesPersonnes(Model model) {
        model.addAttribute("etudiant", dao.findAll());
        return "tousLesEtudiants";
    }
}
