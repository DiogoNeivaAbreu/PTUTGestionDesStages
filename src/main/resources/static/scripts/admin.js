let boutonAfficherOffres = document.getElementById("boutonAfficherOffres");
let boutonTousLesEtudiants = document.getElementById("boutonTousLesEtudiants");
let boutonToutesLesEntreprises = document.getElementById("boutonToutesLesEntreprises");

boutonAfficherOffres.addEventListener("click", pageAfficherOffres);
boutonTousLesEtudiants.addEventListener("click", pageTousLesEtudiants);
boutonToutesLesEntreprises.addEventListener("click", pageToutesLesEntreprises)

function pageAfficherOffres(){
    document.location.href="/offreStage/show";
}

function pageTousLesEtudiants(){
    document.location.href="/etudiant/show";
}

function pageToutesLesEntreprises(){
    document.location.href="/entreprise/show";
}