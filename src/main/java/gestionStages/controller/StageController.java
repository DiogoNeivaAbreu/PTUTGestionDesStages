package gestionStages.controller;

import gestionStages.dao.EntrepriseRepository;
import gestionStages.dao.EtudiantRepository;
import gestionStages.dao.OffreStageRepository;
import gestionStages.dao.StageRepository;
import gestionStages.entity.EtatOffreStage;
import gestionStages.entity.OffreStage;
import gestionStages.entity.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/stage")
public class StageController {
    @Autowired
    private StageRepository dao;

    @Autowired
    private EntrepriseRepository dao2;

    @Autowired
    private EtudiantRepository dao3;
    
    @Autowired
    private OffreStageRepository dao4;

    /**
     * Affiche toutes les stages dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('afficheOffreStage.html')
     */
    @GetMapping(path = "show")
    public String afficheToutesLesOffresDeStage(Model model) {
        model.addAttribute("stages", dao.findAll());
        return "tousLesStages";
    }

    /**
     * Montre le formulaire permettant d'ajouter un stage
     *
     * @param stage initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @return le nom de la vue à afficher ('formulaireStage.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("stages") Stage stage, Model model) {
        model.addAttribute("entreprises", dao2.findAll());
        model.addAttribute("etudiants", dao3.findAll());
        model.addAttribute("offreStage", dao4.findAll());
        return "formulaireStage";
    }

    /**
     * Appelé par 'formulaireStage.html', méthode POST
     *
     * @param stage Un stage initialisé  avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des stages
     */
    @PostMapping(path = "save")
    public String ajouteStagePuisMontreLaListe(Stage stage, OffreStage offreStage, EtatOffreStage etatOffre, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            if(offreStage.getEtatOffre()== etatOffre.VALIDEE){
                stage.setTitre(offreStage.getTitre());
                stage.setDescription(offreStage.getDescription());
                stage.setDateDebut(offreStage.getDateDebut());
                stage.setDateFin(offreStage.getDateFin());
                stage.setEntrepriseAccueil(offreStage.getProposeur());
                dao.save(stage);
            }
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "Le stage '" + stage.getTitre() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE'
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : Le stage '" + stage.getTitre() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste
    }

    /**
     * Appelé par le lien 'Supprimer' dans 'afficheStage.html'
     *
     * @param stage à partir de l'id du stage transmis en paramètre, Spring fera une requête SQL SELECT pour
     * chercher le stage dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des stages
     */
    @GetMapping(path = "delete")
    public String supprimeUnStagePuisMontreLaListe(@RequestParam("id") Stage stage, RedirectAttributes redirectInfo) {
        String message = "Le stage '" + stage.getTitre() + "' a bien été supprimée";
        try {
            dao.delete(stage); // Ici on peut avoir une erreur
        } catch (DataIntegrityViolationException e) {
            // violation de contrainte d'intégrité si on essaie de supprimer un stage
            message = "Erreur : Impossible de supprimer le stage '" + stage.getTitre() + "'";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheStage.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
}
