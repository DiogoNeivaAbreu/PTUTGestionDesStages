-- Les données qui seront initialisées automatiquement quand on lance l'application
-- cf. application.properties

--INSERT INTO Entreprise(id, nom, adresse, telephone, email, secteur) VALUES
--(1, 'PHP', 'Paris', '0660066006', null, 'Informatique'),
--(2, 'Pierre Fabre', 'Castres','0660066006', null, 'Informatique');

--INSERT INTO Utilisateur(DTYPE, id, nom, prenom, adresse, telephone, email, poste, matiere_enseignee, annee_etude) VALUES
--('Etudiant', 1, 'Dubois', 'Pierre', 'France', '0671827384', null, 'abc@gmail.com', null, '2021'),
--('MaitreStageEcole', 2, 'Petit', 'Jean', 'France', '0694857844', null, 'abcd@gmail.com', 'Informatique', null);


INSERT INTO Utilisateur(DTYPE, id, username, password, nom, adresse, telephone, email, secteur) VALUES
('Entreprise', 1, 'pfabre', 'pierre', 'Pierre Fabre', '24 Rue Firmin Oulès', '0660006600', 'mail@pierrefabre.com', 'laboratoire'),
('Entreprise', 5, 'aphp', 'phphp', 'AP-HP', 'quelque part dans Paris', '0660006600', 'mail@aphp.com', 'hôpital');

INSERT INTO Utilisateur(DTYPE, id, username, password, nom, adresse, telephone, email, prenom, annee_etude) VALUES
('Etudiant', 2, 'gpous', 'gogopous', 'POUS', '2 Rue de Metz', '0781887024', 'gauthier.pous@etud.univ-jfc.fr', 'Gauthier', '3e année'),
('Etudiant', 3, 'dneiva', 'diodiogo', 'NEIVA ABREU', '2 Rue de la Rue', '0651721570', 'diogo.neivaabreu@etud.univ-jfc.fr', 'Diogo', '3e année'),
('Etudiant', 4, 'cgay', 'cocogay', 'GAY', '8 Place de la République', '0783734322', 'colin.gay@etud.univ-jfc.fr', 'Colin', '3e année');


INSERT INTO Offre_stage(id, titre, description, date_debut, date_fin, proposeur_id, etat_offre) VALUES
(1, 'Informatique 1', 'Stage de 3A', TO_DATE('2021-05-01', 'YYYY-MM-DD'), TO_DATE('2021-07-07', 'YYYY-MM-DD'),1, 1),
(2, 'Informatique 2', 'Stage de 3A', TO_DATE('2021-05-03', 'YYYY-MM-DD'), TO_DATE('2021-07-10', 'YYYY-MM-DD'),5, 3);

INSERT INTO Stage(id, titre, description, date_debut, date_fin, entreprise_accueil_id, stagiaire_id) VALUES
--(1, 'Informatique 1', 'Stage de 3A', TO_DATE('2021-05-01', 'YYYY-MM-DD'), TO_DATE('2021-07-07', 'YYYY-MM-DD'), 1, 2),
(2, 'Informatique 2', 'Stage de 3A', TO_DATE('2021-05-01', 'YYYY-MM-DD'), TO_DATE('2021-07-07', 'YYYY-MM-DD'), 5, 3);