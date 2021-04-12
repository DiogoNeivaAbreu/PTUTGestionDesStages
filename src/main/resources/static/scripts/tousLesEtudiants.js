let boutonNom = document.getElementById("nom");
let boutonPrenom = document.getElementById("prenom");
let boutonAnneeEtude = document.getElementById("anneeEtude");

let nomActif = 0;
let prenomActif = 0;
let anneeActif = 0;


boutonNom.addEventListener("click", boutonNomClique);
boutonPrenom.addEventListener("click", boutonPrenomClique);
boutonAnneeEtude.addEventListener("click", boutonAnneeClique);

function boutonNomClique(){
    if (!nomActif) {
        boutonNom.style.boxShadow = "inset -3px -3px 6px rgba(255, 255, 255, 0.75), inset 3px 3px 6px rgba(0, 0, 0, 0.3)";
        nomActif = 1;
    }
    else{
        boutonNom.style.boxShadow = "-3px -3px 6px rgba(255, 255, 255, 0.75), 3px 3px 6px rgba(0, 0, 0, 0.3)";
        nomActif = 0;
    }
}

function boutonPrenomClique(){
    if (!prenomActif) {
        boutonPrenom.style.boxShadow = "inset -3px -3px 6px rgba(255, 255, 255, 0.75), inset 3px 3px 6px rgba(0, 0, 0, 0.3)";
        prenomActif = 1;
    }
    else{
        boutonPrenom.style.boxShadow = "-3px -3px 6px rgba(255, 255, 255, 0.75), 3px 3px 6px rgba(0, 0, 0, 0.3)";
        prenomActif = 0;
    }
}

function boutonAnneeClique(){
    if (!anneeActif) {
        boutonAnneeEtude.style.boxShadow = "inset -3px -3px 6px rgba(255, 255, 255, 0.75), inset 3px 3px 6px rgba(0, 0, 0, 0.3)";
        anneeActif = 1;
    }
    else{
        boutonAnneeEtude.style.boxShadow = "-3px -3px 6px rgba(255, 255, 255, 0.75), 3px 3px 6px rgba(0, 0, 0, 0.3)";
        anneeActif = 0;
    }
}
