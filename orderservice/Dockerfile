FROM maven:3.8.6-openjdk-21-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/orderservice-1.0-SNAPSHOT.jar orderservice.jar
ENTRYPOINT ["java", "-jar", "orderservice.jar"]
