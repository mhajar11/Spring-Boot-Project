<<<<<<< HEAD
# Spring-Boot-Project
Ce projet est une application web Java développée avec Spring Boot, permettant la gestion des équipes et des joueurs. Il utilise une API REST permettant la création, la mise à jour et la suppression des équipes (et des joueurs). Les données sont stockées dans une base de données  MySQL 
=======
# MHAJAR Youness _  FISE3 _ Application distribuée

# Spring Boot Project - Gestion des Équipes

## Description
Ce projet est une application Spring Boot permettant de gérer les équipes. Elle offre une interface simple pour visualiser les équipes et leurs joueurs , ainsi que des endpoints d'API pour interagir avec la base de données .

## Fonctionnalités
- Consulter la liste des équipes.
- Ajouter, mettre à jour et supprimer des équipes via des endpoints REST.
- Ajouter, mettre à jour et supprimer des joueurs via des endpoints REST.

---

## Prérequis
Avant de commencer, assurez-vous que les éléments suivants sont installés sur votre système :
- [Docker](https://www.docker.com/)
- [Java 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Postman](https://www.postman.com/) ou tout autre outil pour tester les APIs.

---

## Configuration du Projet

### Fichiers et structure nécessaires
Assurez-vous que votre répertoire contient les fichiers suivants :

1. **`docker-compose.yml`** :
```yaml
version: '3.8'

services:
  db:
    image: mysql:8.0
    container_name: mysql_db
    restart: always
    environment:
      MYSQL_DATABASE: teams_database
      MYSQL_USER: monuser
      MYSQL_PASSWORD: monpass
      MYSQL_ROOT_PASSWORD: rootpass
    ports:
      - "3307:3306"
    volumes:
      - db_data:/var/lib/mysql
      - ./teams_database.sql:/docker-entrypoint-initdb.d/teams_database.sql

  app:
    build: .
    container_name: spring_boot_app
    restart: always
    depends_on:
      - db
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/teams_database?createDatabaseIfNotExist=true&serverTimezone=UTC
      SPRING_DATASOURCE_USERNAME: monuser
      SPRING_DATASOURCE_PASSWORD: monpass

volumes:
  db_data:
```

2. **`teams_database.sql`** : Fichier SQL contenant les données initiales de la base de données.

3. **Fichier JAR** : `spring-boot-project-0.0.1-SNAPSHOT.jar` 

### Étapes de configuration
1. Placez le fichier `teams_database.sql` dans le même répertoire que le fichier `docker-compose.yml`.
2. Placez le fichier JAR dans le même répertoire aussi .


---

## Exécution du Projet

### Étapes pour exécuter l'application avec Docker
1. **Démarrer les services** :
   Exécutez la commande suivante pour démarrer la base de données et l'application Spring Boot :
   ```bash
   docker-compose up --build
   ```
2. **Accéder à l'application** :
   Une fois les conteneurs démarrés, ouvrez votre navigateur et accédez à l'URL :
   ```
   http://localhost:8080/teamsPage
   ```
   Vous devez regarder une liste de 3 equipes , et quelques joueurs qui existent déja , (sinon il y'a un probleme )

### optionnel : Vérification de la base de données
Pour vérifier le contenu de la base de données MySQL :
1. Connectez-vous au conteneur MySQL :
   ```bash
   docker exec -it mysql_db mysql -u root -p
   ```
2. Entrez le mot de passe `monpass`.
3. Utilisez la base de données :
   ```sql
   USE teams_database;
   ```
4. Consultez les tables ou insérez des données :
   ```sql
   SELECT * FROM teams;
   ```

---

## Endpoints de l'API
Voici les endpoints disponibles avec des exemples d'utilisation :

### 1. Récupérer la liste des équipes
- **Exemple avec Postman** :
-URL : GET http://localhost:8080/api/teams
-Postman :
-Méthode : GET
-Body : Aucun.

- **URL** : `GET /api/teams`
- **Exemple avec cURL** :
  ```bash
  curl -X GET http://localhost:8080/api/teams

### 2. Ajouter une équipe
- **Exemple avec Postman** :
-URL : POST http://localhost:8080/api/teams
-Postman :
-Méthode : POST
-Body (JSON):
  ```json
  {
    "name": "FCB",
    "city": "Barcelone"
  }
  ```

- **Exemple avec cURL** :
  ```bash
  curl -X POST http://localhost:8080/api/teams \
       -H "Content-Type: application/json" \
       -d '{"name": "Team 3", "city": "ville de Team 3"}'
  ```

### 3. Mettre à jour une équipe
- **URL** : `PUT http://localhost:8080/api/teams/{id}`
- **Body** :
  ```json
  {
    "name": "OL",
    "ville": "Lyon"
  }
  ```
- **Exemple avec cURL** :
  ```bash
  curl -X PUT http://localhost:8080/api/teams/1 \
       -H "Content-Type: application/json" \
       -d '{"name": "Updated Team", "city": "Updated city"}'
  ```

### 4. Supprimer une équipe
- **URL** : `DELETE /api/teams/{id}`
- **Exemple avec cURL** :
  ```bash
  curl -X DELETE http://localhost:8080/api/teams/1
  ```

---
### 5.Ajouter un joueur à une équipe
URL : POST http://localhost:8080/api/players
Postman :
Méthode : POST
Body (JSON) :
  ```json
  {
  "name": "Messi",
  "number": 10,
  "team": {
    "id": 1
  }
  }
  ```
### 6. Consulter les joueurs 
URL : GET http://localhost:8080/api/players
Postman :
Méthode : GET
Paramètre : {id} = ID de l'équipe.si un joueur en particulier

### 7.Supprimer un joueur
URL : DELETE http://localhost:8080/api/players/{id}
Postman :
Méthode : DELETE

## Structure des Données
La table `teams` est définie comme suit :
- `id` : Identifiant unique (entier, clé primaire, auto-incrémenté).
- `name` : Nom de l'équipe (texte).
- `ville` : la ville de l'équipe.

---
players :

id : Identifiant unique (entier, clé primaire).
name : Nom du joueur (texte).
number : Numéro du joueur (entier).
team_id : Clé étrangère vers teams.

## Remarques
- Si vous souhaitez exécuter l'application sur un autre port, modifiez le mapping des ports dans `docker-compose.yml`.
- La base de données est initialisée à partir du fichier `teams_database.sql`. Assurez-vous que ce fichier contient les données souhaitées avant de démarrer les conteneurs.


## Alternative : Exécution du Projet sans Docker
Si Docker n'est pas disponible, vous pouvez exécuter le projet directement sur votre machine locale en suivant ces étapes :

# Prérequis
-Installez Java 17+ :
-Installez MySQL 8.0 :
-informations de la base de donnés 
Nom d'utilisateur : monuser
Mot de passe : monpass
Base de données : teams_database

-Importez le fichier teams_database.sql pour initialiser les données :
bash:
mysql -u root -p teams_database < teams_database.sql

->Étapes pour exécuter l'application
Téléchargez le fichier JAR : Placez le fichier .jar (nommé spring-boot-project-0.0.1-SNAPSHOT.jar) dans un répertoire de travail.

Modifiez le fichier de configuration : Assurez-vous que les informations de connexion à la base de données dans application.properties sont correctes :

properties
spring.datasource.url=jdbc:mysql://localhost:3306/teams_database?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=monuser
spring.datasource.password=monpass
spring.jpa.hibernate.ddl-auto=update

->Lancez l'application : Exécutez l'application avec la commande suivante dans le terminal :

bash:
java -jar target/spring-boot-project-0.0.1-SNAPSHOT.jar
Accédez à l'application : Ouvrez votre navigateur et accédez à l'URL suivante :
http://localhost:8080/teamsPage

Vérification des Endpoints
Utilisez Postman ou un autre client HTTP pour interagir avec les endpoints de l'API.
Consultez la section Endpoints de l'API dans ce README pour des exemples d'utilisation.

>>>>>>> 628f70e (initial commit)
