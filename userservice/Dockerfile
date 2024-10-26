# Stage 1: Build the application using Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the entire project to the container (including common module and parent POM)
COPY . .

# Run Maven install on the root POM to build and install all modules
RUN mvn clean install -DskipTests

# Package only the userservice
WORKDIR /app/userservice
RUN mvn clean package -DskipTests && ls /app/userservice/target

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the userservice JAR file from the build stage
COPY --from=build /app/userservice/target/userservice-1.0-SNAPSHOT.jar userservice.jar

# Set environment variable for Render to dynamically set the port
ENV PORT 8081

# Run the application, using the PORT environment variable
ENTRYPOINT ["java", "-jar", "userservice.jar"]
