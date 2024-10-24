FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/orderservice-1.0-SNAPSHOT.jar orderservice.jar
ENTRYPOINT ["java", "-jar", "orderservice.jar"]
