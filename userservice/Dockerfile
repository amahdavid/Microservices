# Stage 1: Build the application using Maven
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/userservice/target/userservice-1.0-SNAPSHOT.jar userservice.jar
RUN java -version
ENV PORT 8081
EXPOSE ${PORT}
ENTRYPOINT ["java", "-jar", "userservice.jar"]
CMD ["--server.port=${PORT}"]