# PandaJavaByTER
Pour la couverture :
    - selectFirstLines et selectLastLines:
        Le dernier catch de n'a pas besoin d'être testé car le dataframe sur lequel on appelle la méthode a forcément été créé correctement (et aucune modification ne permet de le modifier), et on fait les vérifications nécessaires avant d'en créer un nouveau.
        La vérification que les colonnes ont la même taille n'est pas encore judicieux car on ne peut pas pour l'instant créer un dataframe avec des colonnes de taille différente
    
Ca serait bien que les display ça serait des méthodes où obtient les chaines de caractère mais c'est pas le plus important

Pour les stats, les casts posent problème
