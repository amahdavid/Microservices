# Stage 1: Build the application using Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the entire project to the container (including common module and parent POM)
COPY . .

# Run Maven install on the root POM to build and install all modules
RUN mvn clean install -DskipTests

# Package only the orderservice
WORKDIR /app/orderservice
RUN mvn clean package -DskipTests && ls /app/orderservice/target

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/orderservice/target/orderservice-1.0-SNAPSHOT.jar orderservice.jar
ENTRYPOINT ["java", "-jar", "orderservice.jar"]
