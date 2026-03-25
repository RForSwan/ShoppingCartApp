pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
    }

    environment {
          PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
          DOCKERHUB_CREDENTIALS_ID = 'docker_hub'
          DOCKERHUB_REPO = 'kaere/shoppingcartapp'
          DOCKER_IMAGE_TAG = 'latest'
      }

    stages {
        stage('check') {
            steps {
                git branch: 'master',
                        url: 'https://github.com/RForSwan/ShoppingCartApp'
            }
        }
        stage('build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('report') {
            steps {
                bat 'mvn jacoco:report'

            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Build Docker image') {
            steps {
                script{
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                  docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                      docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                  }
                }
            }
        }
    }
}