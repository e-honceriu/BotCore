FROM maven:3-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

RUN apt-get update && \
    apt-get install -y \
    python3 \
    python3-pip \
    ffmpeg \
    && rm -rf /var/lib/apt/lists/*

RUN pip3 install yt-dlp spotdl

WORKDIR /app

COPY --from=build /app/target/*.jar musicservice.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "musicservice.jar"]