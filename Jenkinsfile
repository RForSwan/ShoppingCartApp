pipeline {
    agent any

    environment {
          PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
          DOCKERHUB_CREDENTIALS_ID = 'docker_hub'
          DOCKERHUB_REPO = 'kaere/shoppingcartapp'
          DOCKER_IMAGE_TAG = 'latest'
      }

    stages {
        stage('check') {
            steps {
                git branch: 'main',
                        url: 'https://github.com/RForSwan/ShoppingCartApp'
            }
        }
        stage('build') {
            steps {
                sh 'mvn install'
            }
        }
        stage('report') {
            steps {
                sh 'mvn jacoco:report'

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
                sh '''
         docker build \
           -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} \
           .
         '''
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                        credentialsId: "${DOCKERHUB_CREDENTIALS_ID}",
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                 echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                 docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}
             '''
                }
            }
        }
    }
}