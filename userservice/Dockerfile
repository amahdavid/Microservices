# Stage 1: Build the application using Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests
WORKDIR /app/userservice
RUN mvn clean compile assembly:single -DskipTests  # Use assembly to create a fat jar

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/userservice/target/userservice-1.0-SNAPSHOT-jar-with-dependencies.jar userservice.jar
ENV PORT 8081
EXPOSE ${PORT}
ENTRYPOINT ["java", "-jar", "userservice.jar"]
CMD ["--server.port=${PORT}"]