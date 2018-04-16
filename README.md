# PandaJavaByTER

Membres:
- Robin EXTRANT
- Emmanuel LIONET
- Tuo ZHAO

Répartition du travail:
- Emmanuel : Implémentation de la classe Dataframe + méthodes sur les données d'un dataframe.
- Robin : Mise en place automatisation des tests (Maven) + couverture du code (EclEmma - JaCoCo) + intégration continue (Travis CI) + Implémentation des tests (JUnit).
- Tuo : Implémentation de la classe CSVToDataFrame permettant de lire les données d'un fichier csv.

Fonctionnalités implémentées :
- Création d'un dataframe via un chemin + un nom de fichier (fonction readFromFile de la classe Dataframe)
- Création d'un dataframe à partir d'un tableau de contenu + tableau des labels (constructeur de la classe Dataframe)

- Obtenir sous forme de chaine de caractères les i premières lignes d'un dataframe (fonction getFirstLines), les i dernières lignes (fonction getLastLines), toutes les lignes (fonction getAllLines).
- Afficher sur la sortie standard les i premières lignes d'un dataframe (fonction displayFirstLines), les i dernières lignes (fonction displayLastLines), toutes les lignes (fonction displayAll).

- Obtenir sous forme d'un nouvel Dataframe les i premières lignes d'un dataframe (fonction selectFirstLines), les i dernières lignes (fonction selectLastLines), les lignes entre l'indice start et end (fonction selectLines)

- Obtenir sous forme d'un Double le minimum de la i-ème colonne d'un dataframe (fonction getMin), le maximum de la i-ème colonne (fonction getMax), la moyenne de la i-ème colonne (fonction getAverage). Ces fonctions sont utilisables sur les colonnes contenant des Integer ou des Double.
- Affichage des valeurs retournées par ces fonctions via les fonctions showMin, showMax, showAverage.

Manipulation du projet :
- Pour lancer les tests, il suffit de faire : 'mvn test'
- La génération du rapport de couverture de code se fait à la phase "site" de maven. Pour générer le rapport, il suffit de faire  : 'mvn site' . Le rapport est alors généré sous forme de site html dans le dossier "./target/site/jacoco-ut/index.html"

Feedback :
- Dans l'ensemble nous n'avons pas eu de difficulté majeure pendant le projet. Cependant, il est très inconfortable de devoir convertir une librairie d'un language non typé (python) vers un language très typé (java). En effet, nous avons du utilisé des structures de type Object et de nombreux cast, à cause du fait qu'une structure de données en java (array, list...) ne peut avoir qu'un seul type. Il serait préférable, pour les prochains projets, d'essayer de ne pas devoir passer d'un language avec très peu de contraintes vers un language tel que le java (surtout dans le cadre du DevOps, ou nous pensons que le but premier du projet n'était pas l'implémentation en elle même de la librairie Pandas, mais l'utilisation des différents outils vus pendant le semestre).

Pour la couverture du code :
- SelectLines : Le dernier catch de n'a pas besoin d'être testé car le dataframe sur lequel on appelle la méthode a forcément été créé correctement (et aucune modification ne permet de le modifier), et on fait les vérifications nécessaires avant d'en créer un nouveau.

