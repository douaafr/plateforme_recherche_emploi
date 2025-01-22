# Acteurs :
<ul>
<li>Candidat : Cherche des emplois et postule aux offres. </li>
<li>Employeur : Publie des offres et gère les candidatures. </li>
</ul>


# Objectifs du système à modéliser :


On propose de modéliser un système d'aide à la recherche d'emplois, mettant en relation les recruteurs et les candidats et de mise en relation des candidats et des employeurs pour faciliter la recherche d’emplois et le suivi des candidatures. Le système permet également d’envoyer des recommandations personnalisées aux candidats basées sur leur CV et les offres disponibles.


## Le projet inclut :
<ul>
<li>Service de gestion des candidatures.</li>
<li>Service de gestion des CV (CVThèque). </li>
<li>Service de gestion des offres d’emploi (Emploithèque). </li>
<li>Service d’envoi des notifications (nouvelle offre d'emploi). </li>
</ul>


## Exigences fonctionnelles :


#### Candidat


- Consulter les offres d’emploi disponibles.
- Postuler à une ou plusieurs offres d’emploi.
- Suivre le statut de ses candidatures (en cours, instruite, rejetée, acceptée pour entretien).
- Recevoir des notifications par email concernant les nouvelles offres.


#### Recruteur
- Publier des offres d’emploi.
- Consulter et gérer les candidatures reçues (modifier le statut : en cours, rejetée, acceptée).
- Supprimer une offre d’emploi.


## Règles métier
- La gestion des candidatures permet à un candidat de postuler et à un employeur de suivre l'évolution des candidatures.
- La CVThèque envoie par email les nouvelles offres aux candidats.




# Diagrammes de séquences :


#### Processus de candidature :
![](Processus de candidature.png)
  
#### Publication d'une offre :
![](Processus de publication d'une offre.png)


#### Mise à jour d'une offre :
![](Processus de mise à jour d'une offre.png)
  
#### Création d'un CV:
![](Processus de création d'un CV(1).png)


#### Mise à jour d'un CV:
![](Processus de mise à jour d'un CV(1).png)


# Diagrammes de séquences :

#### Processus de candidature :
    
![](process_candidature.jpg)
    
#### Publication d'une offre :
    
![](publication_offre.jpg)

#### Mise à jour d'une offre :
    
![](maj_offre.jpg)
    
#### Création d'un CV:
    
![](création_cv.jpg)

#### Mise à jour d'un CV:
    
![](maj_cv.jpg)

    