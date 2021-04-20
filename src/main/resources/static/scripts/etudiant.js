let boutonAfficherOffres = document.getElementById("boutonAfficherOffres");
let boutonModifierProfilEtudiant = document.getElementById("boutonModifierProfilEtudiant");
let mettreEnLigneUnFichier = document.getElementById("mettreEnLigneUnFichier");

boutonAfficherOffres.addEventListener("click", pageAfficherOffres);
mettreEnLigneUnFichier.addEventListener("click", pageUploadFichier);
boutonModifierProfilEtudiant.addEventListener("click", pageModifierProfil)

function pageAfficherOffres(){
    document.location.href="/offreStage/show";
}

function pageUploadFichier(){
    document.location.href="/upload";
}

function pageModifierProfil(){
    document.location.href="/etudiant/modifierProfil";
}