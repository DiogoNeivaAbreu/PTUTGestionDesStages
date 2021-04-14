-- Les données qui seront initialisées automatiquement quand on lance l'application
-- cf. application.properties

INSERT INTO Offre_stage(id, titre, description, date_debut, date_fin) VALUES
(1, 'Informatique 1', 'Stage de 3A', TO_DATE('2021-05-01', 'YYYY-MM-DD'), TO_DATE('2021-07-07', 'YYYY-MM-DD')),
(2, 'Informatique 2', 'Stage de 3A', TO_DATE('2021-05-03', 'YYYY-MM-DD'), TO_DATE('2021-07-10', 'YYYY-MM-DD'));

INSERT INTO Entreprise(id, nom, adresse, telephone, email, secteur) VALUES
(1, 'PHP', 'Paris', '0660066006', null, 'Informatique'),
(2, 'Pierre Fabre', 'Castres','0660066006', null, 'Informatique');

INSERT INTO Utilisateur(DTYPE, id, nom, prenom, adresse, telephone, email, poste, matiere_enseignee, annee_etude) VALUES
('Etudiant', 1, 'Dubois', 'Pierre', 'France', '0671827384', null, 'abc@gmail.com', null, '2021'),
('MaitreStageEcole', 2, 'Petit', 'Jean', 'France', '0694857844', null, 'abcd@gmail.com', 'Informatique', null);