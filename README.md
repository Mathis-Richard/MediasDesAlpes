# Cahier des charges

## Cahier des charges de la médiathèque de Châteauroux-Les-Alpes 



"Réalisation d’une application Web pour la gestion des emprunts et retours suite à la modernisation de la médiathèque. "

## Contexte

La médiathèque de Chateauroux-Les-Alpes a été créée en Octobre de l’an 2002. Le village souhaite utiliser les subventions de l’état pour moderniser cette dernière ainsi que faciliter son utilisation pour les clients. Aussi, la médiathèque souhaiterait instaurer un système de notation des médias empruntés par les usagers. 

Le contexte est donc celui de la modernisation et la numérisation des registres de visite, d’emprunt, de retour, de location du matériel et de partage d'expérience.


## Problématiques

*Amélioration de l'expérience utilisateur : Comment concevoir un site convivial et intuitif qui facilite la recherche, la sélection, et l'emprunt de médias pour les usagers de la médiathèque ?
*Optimisation de la gestion des collections : Comment gérer de manière plus efficace et efficiente les stocks de médias en utilisant une base de données, en suivant les emprunts et en anticipant les besoins des usagers ?
*Mesure de l'impact et de la satisfaction : Comment mesurer l'impact de la modernisation sur la fréquentation de la médiathèque et la satisfaction des usagers, et comment utiliser ces données pour apporter des améliorations continues ?
*Accessibilité et inclusion : Comment s'assurer que le site web et la base de données sont accessibles à tous, y compris les personnes ayant des besoins spécifiques en matière d'accessibilité ?


## Besoins

Des quelques problématiques décrites ci-dessus, on peut en déduire les besoins principaux du système qui vont se mettre en place. Ces derniers seront décrits sous 2 catégories : 
le profil usager et le profil responsable de médiathèque.  

* Responsable de médiathèque : * 
* Pouvoir créer et administrer un compte à chaque nouvel usager de la médiathèque
* Pouvoir connaître les médias les plus empruntés
* Pouvoir rajouter des médias dans la BDD 
* Pouvoir recenser et consulter l’état du média à son emprunt et son retour 

* Usager: *

* Pouvoir laisser un avis sur un ou plusieurs médias après emprunt
* Pouvoir consulter les avis laissé par les autres usages ayant réalisé le même emprunt
* Pouvoir réserver un média si ce dernier a déjà été emprunté par quelqu’un 
* Pouvoir effectuer des donations de médias personnels à la médiathèque
* Pouvoir consulter les dates auxquelles l’usager doit rendre les médias qu’il a empruntés.


**Contraintes fonctionnelles**

*  Les utilisateurs doivent forcément avoir une adresse mail;
* Les utilisateurs peuvent donner un avis sans laisser de note. C'est-à- dire que la note peut être null;
* Un média ne doit avoir qu’un seul genre;
* Un média ne doit avoir qu’un seul type;
* La date de retour réelle peut être nul tant que statut d’emprunt n’est pas à l'etat rendu;
* Le statut d’emprunt peut être : reserve, en_cours ou rendu;


**Contraintes techniques**

* La solution devra pouvoir fonctionner dans un environnement GNU/LINUX.
* La solution devra être accessible depuis une interface Web.


## Livrables

2 livrables sont attendus :
* La solution _packagée_ dans des conteneurs type Docker
* La documentation contenant les précédures d'installation et de déploiement, le guide utilisateur et le guide administrateur.

# Réponse au besoin

## Solutions :

### Solutions fonctionnelles

Voici les fonctionnalités qui seront offertes par l'application :

*Usager : *
* Un système de rappel est envoyé aux usagers avant la fin de la réservation. 
* Un système de gestion des avis laissé par les usagers;
* Un système de gestion des stocks et de la disponibilitée des médias;

*Responsable de médiathèque*
* Un système de gestion d'inscriptions doté d'une intelligence capable de vérifier chacune des informations entrée et de vous proposer des solutions ;
* Un système de gestion des droits sur le site;
*Un système de gestion des médias dans la médiathèque;
* Un système pour savoir quels médias sont les plus populaires.


### Solutions techniques

D'un point de vue technique, nous proposons d'intégrer des technologies éprouvées, performantes et peu onéreuses : 

* Une base de données PostgreSQL pour la gestion et la manipulation des données. PostgreSQL est une base de données libre, sans coût de licence, et elle existe depuis plus de 20 ans
* Serveur Web Apache pour la délivrance des pages Web. Apache est aujourd'hui le serveur le plus populaire et dispose de nombreux modules.
* PHP pour la génération dynamique de pages web. PHP est un Langage simple et rapide à mettre en oeuvre pour la création de site web. Bibliothèques très fournies.
* Docker pour le packaging de l'application dans un environnement maîtrisée et cloisonné.

# Livrables

Le livrable est un environnement _Docker_ contenant l'ensemble des services, base de données et IHMs. Le livrable pourra s'installer sur une machine GNU/Linux avec _Docker_ et _docker-compose_ installés.

Le fichier [README](./README.md) décrit la procédure d'installation de l'application. 

Les mises à jour se font en téléchargeant la dernière version de l'application sur _github_. Lors des mises à jours, l'administrateur du système devra suivre la procédure suivante :
- Stopper les services 
```
docker-compose down
```
- Télécharger les mises à jours
```
git pull
```
- Redémarrer les services
```
docker-compose up
```

