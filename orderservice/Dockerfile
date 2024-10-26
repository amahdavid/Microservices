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

# Copy the orderservice JAR file from the build stage
COPY --from=build /app/orderservice/target/orderservice-1.0-SNAPSHOT.jar orderservice.jar

# Set default port environment variable, allowing override by the container runtime
ENV PORT=8082

# Expose the port to make it accessible from outside the container
EXPOSE ${PORT}

# Run the application, using the PORT environment variable
ENTRYPOINT ["java", "-jar", "orderservice.jar"]
CMD ["--server.port=${PORT}"]
