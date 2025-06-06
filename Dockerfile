# Étape 1 : construction de l'application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : exécution de l'application
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /app/target/cd-backend-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=10000
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "app.jar"]
