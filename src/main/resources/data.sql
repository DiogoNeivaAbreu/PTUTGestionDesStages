-- Les données qui seront initialisées automatiquement quand on lance l'application
-- cf. application.properties

INSERT INTO Stage(DTYPE, id, titre, description, date_debut, date_fin) VALUES 
    ('OffreStage', 1, 'Informatique', 'Stage de 3A', TO_DATE('2021-05-01', 'YYYY-MM-DD'), TO_DATE('2021-31-07', 'YYYY-MM-DD')),
    ('OffreStage', 2, 'Informatique', 'Stage de 3A', TO_DATE('2021-05-01', 'YYYY-MM-DD'), TO_DATE('2021-31-07', 'YYYY-MM-DD'));

INSERT INTO Entreprise(id, nom, adresse, telephone, email, secteur) VALUES
    (1, 'PHP', 'Paris', '0660066006', null, 'Informatique'),
    (2, 'Pierre Fabre', 'Castres','0660066006', null, 'Informatique');

INSERT INTO Personne(DTYPE, id, nom, prenom, adresse, telephone, email, poste, matiere_enseignee, annee_etude) VALUES
    ('Etudiant', 1, 'Dubois', 'Pierre', 'France', '0671827384', null, null, null, '2021'),
    ('MaitreStageEcole', 2, 'Petit', 'Jean', 'France', '0694857844', null, null, 'Informatique', null);
