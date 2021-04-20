package gestionStages.controller;

import gestionStages.dao.EntrepriseRepository;
import gestionStages.dao.EtudiantRepository;
import gestionStages.dao.OffreStageRepository;
import gestionStages.dao.StageRepository;
import gestionStages.entity.EtatOffreStage;
import gestionStages.entity.Etudiant;
import gestionStages.entity.OffreStage;
import gestionStages.entity.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Edition des catégories, sans gestion des erreurs
 */
@Controller
@RequestMapping(path = "/offreStage")
public class OffreStageController {
    @Autowired
    private OffreStageRepository dao;

    @Autowired
    private EntrepriseRepository dao2;

    @Autowired
    private EtudiantRepository dao3;
    
    @Autowired
    private StageRepository dao4;

    /**
     * Affiche toutes les offres de stage dans la base
     *
     * @param model pour transmettre les informations à la vue
     * @return le nom de la vue à afficher ('afficheOffreStage.html')
     */
    @GetMapping(path = "show")
    public String afficheToutesLesOffresDeStage(Model model) {
        model.addAttribute("offreStage", dao.findAll());
        return "afficheOffreStage";
    }

    /**
     * Montre le formulaire permettant d'ajouter une offre de stage
     *
     * @param offreStage initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @return le nom de la vue à afficher ('formulaireOffreStage.html')
     */
    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("offreStage") OffreStage offreStage, Model model) {
        model.addAttribute("entreprises", dao2.findAll());
        return "formulaireOffreStage";
    }

    /**
     * Appelé par 'formulaireOffreStage.html', méthode POST
     *
     * @param offreStage Une offre de stage initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des offres
     */
    @PostMapping(path = "save")
    public String ajouteOffrePuisMontreLaListe(OffreStage offreStage, Stage stage, EtatOffreStage etatOffre, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            dao.save(offreStage);
//            if(offreStage.getEtatOffre()== etatOffre.VALIDEE){
//                stage.setTitre(offreStage.getTitre());
//                stage.setDescription(offreStage.getDescription());
//                stage.setDateDebut(offreStage.getDateDebut());
//                stage.setDateFin(offreStage.getDateFin());
//                stage.setEntrepriseAccueil(offreStage.getProposeur());
//                dao4.save(stage);
//            }
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "L'offre '" + offreStage.getTitre() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE'
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : L'offre '" + offreStage.getTitre() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste
    }
    
    /**
     * Montre le formulaire permettant de modifier une offre de stage
     *
     * @param offreStage initialisé par Spring, valeurs par défaut à afficher dans le formulaire
     * @return le nom de la vue à afficher ('modifierOffre.html')
     */
    @GetMapping(path = "modif")
    public String montreLeFormulairePourModifier(@RequestParam("id") OffreStage offreStage, Integer id, Model model) {
        
        model.addAttribute("offreStage", dao.findById(id));
        return "modifierOffre";
    }

    /**
     * Appelé par 'afficheOffreStage.html', méthode POST
     *
     * @param offreStage Une offre de stage initialisée avec les valeurs saisies dans le formulaire
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des offres
     */
    @PatchMapping(path = "modifier")
    public String modifieOffrePuisMontreLaListe(OffreStage offreStage, Stage stage, 
                                EtatOffreStage etatOffre, RedirectAttributes redirectInfo, Model model) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            
            dao.save(offreStage);
            if(offreStage.getEtatOffre()== etatOffre.VALIDEE){
                stage.setTitre(offreStage.getTitre());
                stage.setDescription(offreStage.getDescription());
                stage.setDateDebut(offreStage.getDateDebut());
                stage.setDateFin(offreStage.getDateFin());
                stage.setEntrepriseAccueil(offreStage.getProposeur());
                dao4.save(stage);
            }
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "L'offre '" + offreStage.getTitre() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE'
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : L'offre '" + offreStage.getTitre() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste
    }
    
//    @GetMapping(path="modif")
//    OffreStage one(@PathVariable Integer id) throws NotFoundException {
//    
//        return dao.findById(id)
//            .orElseThrow(() -> new NotFoundException());
//  }
//    
//    @PutMapping(path="modif")
//    OffreStage modifOffre(@RequestBody OffreStage newOffre, @PathVariable Integer id) {
//
//      return dao.findById(id)
//        .map(offre -> {
//          offre.setTitre(newOffre.getTitre());
//          offre.setDescription(newOffre.getDescription());
//          offre.setEtatOffre(newOffre.getEtatOffre());
//          return dao.save(offre);
//        })
//        .orElseGet(() -> {
//          newOffre.setId(id);
//          return dao.save(newOffre);
//        });
//    }

    /**
     * Appelé par le lien 'Supprimer' dans 'afficheOffreStage.html'
     *
     * @param offreStage à partir de l'id de l'offre de stage transmis en paramètre, Spring fera une requête SQL SELECT pour
     * chercher l'offre dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des offres
     */
    @GetMapping(path = "delete")
    public String supprimeUneOffrePuisMontreLaListe(@RequestParam("id") OffreStage offreStage, RedirectAttributes redirectInfo) {
        String message = "L'offre '" + offreStage.getTitre() + "' a bien été supprimée";
        try {
            dao.delete(offreStage); // Ici on peut avoir une erreur
        } catch (DataIntegrityViolationException e) {
            // violation de contrainte d'intégrité si on essaie de supprimer une offre de stage
            message = "Erreur : Impossible de supprimer l'offre '" + offreStage.getTitre() + "'";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheOffreStage.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }

    /**
     * Appelé par le lien 'postuler' dans 'OffreStage.html', méthode POST
     *
     * @param offreStage à partir de l'id de l'offre de stage transmis en paramètre, Spring fera une requête SQL SELECT pour
     * chercher la galerie dans la base
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers l'affichage de la liste des offres
     */
//    @PostMapping(path = "postuler")
//    public String postuleOffrePuisMontreLaListe(@RequestParam("id") OffreStage offreStage, @RequestParam("id") Etudiant etudiant, RedirectAttributes redirectInfo) {
//        String message;
//        try {
//            // cf. https://www.baeldung.com/spring-data-crud-repository-save
//            dao.postulerOffre(etudiant, offreStage);
//            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
//            message = "Vous avez postulé à " + offreStage.getTitre() ;
//        } catch (DataIntegrityViolationException e) {
//            // Les noms sont définis comme 'UNIQUE' 
//            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
//            message = "Erreur : Vous avez déjà postuler à cette offre";
//        }
//        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
//        // Ici on transmet un message de succès ou d'erreur
//        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
//        redirectInfo.addFlashAttribute("message", message);
//        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
//    }
}