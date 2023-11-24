FROM maven:3.9.4-eclipse-temurin-21 AS builder
LABEL stage="mavenbuilder"

WORKDIR /app

COPY pom.xml ./
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY  --from=builder /app/target/MediasDesAlpes-Launcher.jar app.jar

CMD ["java","-jar","app.jar"]