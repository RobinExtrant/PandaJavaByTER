# PandaJavaByTER
Pour la couverture :
    - selectFirstLines et selectLastLines:
        Le dernier catch de n'a pas besoin d'être testé car le dataframe sur lequel on appelle la méthode a forcément été créé correctement (et aucune modification ne permet de le modifier), et on fait les vérifications nécessaires avant d'en créer un nouveau.

Répartition du travail:
- Emmanuel : Implémentation de la classe Dataframe + méthodes sur les données d'un dataframe.
- Robin : Mise en place automatisation des tests + couverture du code. Implémentation des tests.
- Tuo : Implémentation de la classe CSVToDataFrame permettant de lire les données d'un fichier csv.

Fonctionnalités implémentées :
- Création d'un dataframe via un chemin + un nom de fichier (fonction readFromFile de la classe Dataframe)
- Création d'un dataframe à partir d'un tableau de contenu + tableau des labels (constructeur de la classe Dataframe)

- Obtenir sous forme de chaine de caractères les i premières lignes d'un dataframe (fonction getFirstLines), les i dernières lignes (fonction getLastLines), toutes les lignes (fonction getAllLines).
- Afficher sur la sortie standard les i premières lignes d'un dataframe (fonction displayFirstLines), les i dernières lignes (fonction displayLastLines), toutes les lignes (fonction displayAll).

- Obtenir sous forme d'un nouvel Dataframe les i premières lignes d'un dataframe (fonction selectFirstLines), les i dernières lignes (fonction selectLastLines), les lignes entre l'indice start et end (fonction selectLines)

- Obtenir sous forme d'un Double le minimum de la i-ème colonne d'un dataframe (fonction getMin), le maximum de la i-ème colonne (fonction getMax), la moyenne de la i-ème colonne (fonction getAverage). Ces fonctions sont utilisables sur les colonnes contenant des Integer ou des Double.
- Affichage des valeurs retournées par ces fonctions via les fonctions showMin, showMax, showAverage.
