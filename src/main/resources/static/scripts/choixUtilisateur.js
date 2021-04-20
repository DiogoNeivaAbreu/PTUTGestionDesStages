let boutonEtudiant = document.getElementById("jeSuisUnEtudiant");
let boutonEntreprise = document.getElementById("jeSuisUneEntreprise");

boutonEtudiant.addEventListener("click", formulaireNouveuCompte);
boutonEntreprise.addEventListener("click", formulaireNouveuCompte);


function formulaireNouveuCompte(event){
    if(event.target.id == "jeSuisUnEtudiant"){
        document.location.href="/etudiant/creerUnCompte";
    }
    if(event.target.id == "jeSuisUneEntreprise"){
        document.location.href="/entreprise/creerUnCompte";
    }
}