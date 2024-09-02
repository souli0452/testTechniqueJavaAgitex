# Client Statistics Application

## Description

Cette application Spring Boot permet de lire des fichiers de différents formats (CSV, JSON, XML), d'extraire des informations clients, et de calculer la moyenne des salaires par type de profession. L'application expose une API REST pour télécharger les fichiers et consulter les résultats.

## Fonctionnalités

- Lecture des fichiers CSV, JSON, et XML.
- Calcul de la moyenne des salaires par profession.
- API REST pour télécharger des fichiers et obtenir les statistiques.

## Prérequis

- Docker
- Docker Compose (optionnel)
- JDK 17 (si vous souhaitez exécuter localement sans Docker)
- Maven 3.8.x (si vous souhaitez exécuter localement sans Docker)

## Démarrer avec Docker

1. Clonez ce dépôt Git :

    ```bash
    git clone https://github.com/souli0452/testTechniqueJavaAgitex.git
    cd testTechniqueJavaAgitex
    ```

2. Construisez l'image Docker :

    ```bash
    docker build -t client-statistics-app .
    ```

3. Démarrez le conteneur Docker :

    ```bash
    docker run -p 8080:8080 client-statistics-app
    ```

4. L'application sera accessible via `http://localhost:8080`.

## Utilisation de l'API

### Upload de fichier

- **Endpoint** : `/upload`
- **Méthode** : `POST`
- **Paramètre** : `file` (type de fichier)
- **Exemple de commande cURL** :

    ```bash
    curl -X POST -F "file=@/path/to/your/file.csv" http://localhost:8080/upload
    ```

### Calcul de la moyenne des salaires par profession

- **Endpoint** : `/average-salary-by-profession`
- **Méthode** : `GET`
- **Exemple de commande cURL** :

    ```bash
    curl http://localhost:8080/average-salary-by-profession
    ```

## Développement local

Si vous souhaitez exécuter l'application localement sans Docker :

1. Assurez-vous d'avoir Maven et JDK 17 installés.

2. Clonez le dépôt, puis exécutez :

    ```bash
    mvn clean spring-boot:run
    ```

3. L'application sera disponible à l'adresse `http://localhost:8080`.

## Structure du projet

- **src/main/java** : Contient le code source Java.
- **src/main/resources** : Contient les fichiers de configuration.
- **Dockerfile** : Fichier Docker pour containeriser l'application.
- **pom.xml** : Fichier Maven pour gérer les dépendances du projet.

