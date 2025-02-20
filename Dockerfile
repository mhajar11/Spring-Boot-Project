# Étape 1 : Image de base
FROM openjdk:17-jdk-slim

# Étape 2 : Copie du JAR de l'application
COPY target/spring-boot-project-0.0.1-SNAPSHOT.jar app.jar

# Étape 3 : Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app.jar"]
