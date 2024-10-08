# Yoga App

## Description
Yoga App est une application full-stack permettant de gérer les utilisateurs et les enseignants pour une école de yoga. Le projet comprend une partie backend construite avec Spring Boot, qui gère l'authentification, les données des enseignants et des utilisateurs, ainsi qu'une partie frontend construite avec [Indiquez le framework utilisé, par exemple React, Angular, Vue.js] pour l'interface utilisateur.

## Fonctionnalités
- **Backend (Spring Boot)** :
  - Authentification et autorisation des utilisateurs avec JWT.
  - Gestion des enseignants : création, modification, suppression et affichage des enseignants.
  - API REST sécurisée pour gérer les données des utilisateurs et des enseignants.

- **Frontend ([Nom du framework frontend])** :
  - Interface utilisateur intuitive pour interagir avec l'API backend.
  - Authentification des utilisateurs avec JWT.
  - Formulaires pour gérer les données des enseignants et des utilisateurs.
  - Tableaux de bord pour afficher les informations importantes.

## Structure du projet
Le projet est divisé en deux parties principales : le frontend et le backend.

### Backend
- **Langage** : Java
- **Framework** : Spring Boot
- **Structure** :
  - `src/main/java` : Contient le code de l'application.
    - `controllers` : Gère les requêtes HTTP.
    - `services` : Contient la logique métier.
    - `models` : Définit les entités.
    - `repository` : Interfaces pour l'accès aux données avec JPA.
    - `security` : Configuration de JWT et Spring Security.
  - `src/test/java` : Contient les tests unitaires et d'intégration.

### Frontend
- **Langage** : JavaScript/TypeScript
- **Framework** : [Nom du framework frontend, ex: React, Angular]
- **Structure** :
  - `src/components` : Composants réutilisables de l'interface utilisateur.
  - `src/pages` : Différentes pages de l'application.
  - `src/services` : Services pour interagir avec l'API backend.
  - `src/styles` : Fichiers de style (CSS/SCSS).

## Prérequis
- **Backend** :
  - Java 11 ou supérieur
  - Maven 3.6.3 ou supérieur
  - Une instance de base de données MySQL/PostgreSQL en cours d'exécution (ou H2 pour les tests)

- **Frontend** :
  - Node.js (version 14 ou supérieure)
  - npm ou yarn

## Installation et Exécution

### Backend
1. Clonez le dépôt :
   ```bash
   git clone <repository-url>

2. Accédez au répertoire backend :
cd back

3.Configurez la base de données dans src/main/resources/application.properties.

4.Compilez et exécutez l'application :
mvn clean install
mvn spring-boot:run

### Frontend
1.Accédez au répertoire frontend :
cd front

2.Installez les dépendances :
npm install

3.Lancez l'application frontend :
npm start
L'application sera disponible à l'adresse http://localhost:3000.


###Exécution des Tests
Backend
Pour exécuter les tests unitaires et d'intégration :
mvn test

Frontend
Pour exécuter les tests frontend (si configurés) :

npm test

Déploiement
Backend : Vous pouvez déployer l'application sur un serveur Java comme Tomcat ou l'exécuter directement en utilisant le JAR généré.
Frontend : Vous pouvez construire le frontend pour la production avec :

npm run build
Les fichiers construits seront dans le dossier build et peuvent être déployés sur n'importe quel serveur web.

Contribution
Les contributions sont les bienvenues ! Veuillez forker le dépôt et soumettre une pull request.
