let boutonAfficherOffres = document.getElementById("boutonAfficherOffres");
let boutonModifierProfilEtudiant = document.getElementById("boutonModifierProfilEtudiant");

boutonAfficherOffres.addEventListener("click", pageAfficherOffres);
boutonModifierProfilEtudiant.addEventListener("click", pageModifierProfil)

function pageAfficherOffres(){
    document.location.href="/offreStage/show";
}

function pageModifierProfil(){
    document.location.href="/etudiant/modifierProfil";
}