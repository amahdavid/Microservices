FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/userservice-1.0-SNAPSHOT.jar userservice.jar
ENTRYPOINT ["java", "-jar", "userservice.jar"]