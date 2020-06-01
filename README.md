# Projet_New_Horizons-
Projet de génie logiciel

Membres du projet : Danaradj Souprayen, Simon Ledoit, Thomas Carpentier, Alexia Serrier, Cédric Pasquier, Tanguy Benard

### Qu'est ce que ce projet ?
Ce projet est une application de chat via réseau local. Elle comporte un serveur gérant un ou des clients et un client pouvant être instancié un nombre souhaité de fois.

### Prérequis
* Avoir Java 13 
* Language level 8
* Récupérer la branche master

### Fonctionnement du serveur
On lance ce dernier sur une machine donnée. Il est alors en permanence en attente de nouveaux clients et utilise des threads pour traiter ces derniers lorsqu'ils se connectent.

### Fonctionnement du client
##### Connexion du client
Contrairement au serveur, le client possède une interface utilisateur.
Au démarrage, le client a le choix entre se connecter avec un compte existant et se créer un compte sur le serveur.
[screenshot1]

[screenshot2]

##### Accès aux discussions
Lorsque le client est connecté, celui ci a accès à plusieurs interfaces :
- la liste des discussions en cours [screenshot3]
- la création d'une nouvelle conversation [screenshot4]
- le chat dans une conversation donnée [screenshot5]
