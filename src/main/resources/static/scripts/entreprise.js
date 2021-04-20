let boutonAfficherOffres = document.getElementById("boutonAfficherOffres");
let boutonAjouterUneOffre = document.getElementById("boutonAjouterUneOffre");
let boutonModifierProfilEntreprise = document.getElementById("boutonModifierProfilEntreprise");

boutonAfficherOffres.addEventListener("click", pageAfficherOffres);
boutonAjouterUneOffre.addEventListener("click", pageAjouterUneOffre);
boutonModifierProfilEntreprise.addEventListener("click", pageModifierProfil)

function pageAfficherOffres(){
    document.location.href="/offreStage/show";
}

function pageAjouterUneOffre(){
    document.location.href="/offreStage/add";
}

function pageModifierProfil(){
    document.location.href="/entreprise/modifierProfil";
}