# Stage 1: Build the application using Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the entire project to the container (including common module and parent POM)
COPY . .

# Package only the userservice
WORKDIR /app/userservice
RUN mvn clean package -DskipTests && ls /app/userservice/target

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/userservice/target/userservice-1.0-SNAPSHOT.jar userservice.jar
ENTRYPOINT ["java", "-jar", "userservice.jar"]
