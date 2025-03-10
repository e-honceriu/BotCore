FROM maven:3-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar gameservice.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "gameservice.jar"]