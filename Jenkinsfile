pipeline {
    agent any

    environment {
        USERSERVICE_IMAGE = 'amahchika/userservice:latest'
        ORDERSERVICE_IMAGE = 'amahchika/orderservice:latest'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Replace with your build commands, e.g., Maven or Gradle for both services
                    // Assuming you are building both services separately
                    sh 'cd userservice && mvn clean package'
                    sh 'cd orderservice && mvn clean package'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    // Run your tests here for both services
                    sh 'cd userservice && mvn test'
                    sh 'cd orderservice && mvn test'
                }
            }
        }
        stage('Dockerize') {
            steps {
                script {
                    // Build Docker images for both services
                    sh 'cd userservice && docker build -t $USERSERVICE_IMAGE .'
                    sh 'cd orderservice && docker build -t $ORDERSERVICE_IMAGE .'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Push Docker images for both services to Docker Hub
                    sh 'docker push $USERSERVICE_IMAGE'
                    sh 'docker push $ORDERSERVICE_IMAGE'
                }
            }
        }
    }
}
