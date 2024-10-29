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
                    // Build commands for both services using Maven
                    sh 'cd userservice && mvn clean package'
                    sh 'cd orderservice && mvn clean package'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    // Run unit tests for both services
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
        stage('Push to Docker Registry') {
            steps {
                script {
                    // Log in to Docker Hub (if required)
                    withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                    }

                    // Push Docker images for both services to Docker Hub
                    sh 'docker push $USERSERVICE_IMAGE'
                    sh 'docker push $ORDERSERVICE_IMAGE'
                }
            }
        }
    }
    post {
        always {
            // Clean up Docker images to save space
            sh 'docker rmi $USERSERVICE_IMAGE || true'
            sh 'docker rmi $ORDERSERVICE_IMAGE || true'
        }
    }
}
