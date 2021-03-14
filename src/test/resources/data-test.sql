-- Les données qui seront préchargées dans la base, avant les tests
-- On peut rajouter des données spécifiques pour un test par l'annotation @Sql( ))
-- cf. application-test.properties
INSERT INTO OffreStage(id, titre, proposeur, decription, dateDebut, dateFin) VALUES (1, 'Informatique', 'PHP', 'Stage de 3A', TO_DATE('2021-05-01', 'YYYY-MM-DD'), TO_DATE('2021-31-07', 'YYYY-MM-DD'));
INSERT INTO OffreStage(id, titre, proposeur, decription, dateDebut, dateFin) VALUES (2, 'Informatique', 'Pierre Fabre', 'Stage de 3A', TO_DATE('2021-05-01', 'YYYY-MM-DD'), TO_DATE('2021-31-07', 'YYYY-MM-DD'));