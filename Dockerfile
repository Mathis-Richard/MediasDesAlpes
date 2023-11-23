FROM maven:3.9.4-eclipse-temurin-21 AS builder
LABEL stage="mavenbuilder"

RUN rm target/MediasDesAlpes-Launcher.jar

WORKDIR /app

COPY pom.xml ./
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY target/MediasDesAlpes-Launcher.jar app.jar

CMD ["java","-jar","app.jar"]