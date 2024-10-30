# Order and User Microservices Project

This project demonstrates a comprehensive microservices architecture using Spring Boot, Docker, and CI/CD practices. It was developed as a hands-on approach to learn and implement core principles of Spring microservices, containerization, and DevOps automation.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Setup Instructions](#setup-instructions)
- [Testing the Services](#testing-the-services)
- [Deployment](#deployment)
- [CI/CD Pipeline](#cicd-pipeline)
- [Future Improvements](#future-improvements)

---

## Overview
The main goal of this project is to build a scalable and maintainable microservices architecture, exposing REST APIs for managing users and orders. Each service is deployed independently, containerized, and orchestrated through a Jenkins CI/CD pipeline. This project was built to develop practical skills in backend development, microservices, and DevOps tooling.

## Features
- **Spring Boot Microservices**: Separate `UserService` and `OrderService` with individual controllers, services, and repositories.
- **REST API Endpoints**: Endpoints for creating, reading, and managing user and order data.
- **Exception Handling**: Custom exception handling for resource not found and validation errors.
- **Containerized Services**: Dockerized each service with its own Dockerfile for seamless deployment.
- **Automated CI/CD**: Jenkins pipeline automates building, testing, and deploying services to Render.
- **Integration Testing**: Unit tests for services and controllers to ensure robust application logic.

## Architecture
This project follows a microservices-based architecture. Each service is independently deployable, interacts with other services as needed, and has its own database.

```
+--------------------+       +--------------------+
|   User Service     |<----->|  Order Service     |
| (Spring Boot App)  |       | (Spring Boot App)  |
+--------------------+       +--------------------+
      |                               |
      |                               |
   Docker                         Docker
      |                               |
+-------------+               +-------------+
| Jenkins CI  |               |    Render   |
+-------------+               +-------------+
```

## Tech Stack
- **Backend Framework**: Spring Boot
- **Build Tool**: Maven
- **Containerization**: Docker
- **Deployment**: Render
- **CI/CD**: Jenkins
- **Testing**: JUnit, Mockito
- **API Testing**: Postman

## Setup Instructions

### Prerequisites
- [Java 11+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://docs.docker.com/get-docker/)
- [Jenkins](https://www.jenkins.io/download/)

### Running Locally

1. **Clone the repository**:
   ```bash
   git clone https://github.com/username/orderservice-microservices.git
   cd orderservice-microservices
   ```

2. **Build the Services**:
   ```bash
   mvn clean install
   ```

3. **Run the Services**:
    - You can run each service individually by navigating to its folder and executing:
      ```bash
      mvn spring-boot:run
      ```
    - Or, use Docker to run them in containers:
      ```bash
      docker-compose up --build
      ```

4. **Access the APIs**:
    - `UserService`: `http://localhost:8081/users`
    - `OrderService`: `http://localhost:8082/orders`

## Testing the Services

You can test the endpoints locally using Postman or any other API testing tool. Here are some example requests:

- **Create User**: `POST http://localhost:8081/users`
- **Create Order**: `POST http://localhost:8082/orders`

## Deployment

Each service is deployed individually on Render with Docker containers. The Dockerfile for each service allows them to be run independently in the cloud.

1. **Docker Build**:
   ```bash
   docker build -t username/orderservice .
   ```

2. **Deploy on Render**: After pushing to your Docker registry, configure Render to pull the image and deploy.

## CI/CD Pipeline

A Jenkins pipeline is set up for CI/CD. The Jenkinsfile automates building, testing, and deploying to Render.

- **Build**: The pipeline checks out code and builds it using Maven.
- **Test**: Executes unit tests and integration tests.
- **Deploy**: Uses Docker to deploy each service on Render if tests pass.

### Sample Jenkinsfile

```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -t username/orderservice .'
                sh 'docker push username/orderservice'
                // Deploy to Render
            }
        }
    }
}
```

## Future Improvements
- Add API Gateway for better request routing.
- Implement authentication and authorization (e.g., OAuth2).
- Migrate to a Kubernetes cluster for advanced container orchestration.