# Étape 1 : Construire l'application
FROM maven:3.8.6-openjdk-17-slim AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers de projet
COPY pom.xml .
COPY src ./src

# Construire l'application
RUN mvn clean package -DskipTests

# Étape 2 : Construire l'image finale
FROM openjdk:17-slim

# Créer un répertoire pour l'application
WORKDIR /app

# Copier l'application jar depuis l'étape de build
COPY --from=build /app/target/testTechniqueJavaAgitex.jar ./app.jar

# Exposer le port 8080
EXPOSE 8080

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
