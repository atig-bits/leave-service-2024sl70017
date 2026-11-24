
pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    environment {
        SONAR_PROJECT_KEY = 'leave-service-2024sl70017'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                echo 'Building project and running unit tests...'
                sh 'mvn clean verify'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Code Inspection') {
            steps {
                echo 'Running SonarQube code analysis...'

                withSonarQubeEnv('SonarQube') {
                    withCredentials([
                        string(
                            credentialsId: 'sonarqube-token',
                            variable: 'SONAR_TOKEN'
                        )
                    ]) {
                        sh '''
                            mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922:sonar \
                              -Dsonar.projectKey=$SONAR_PROJECT_KEY \
                              -Dsonar.host.url=$SONAR_HOST_URL \
                              -Dsonar.token=$SONAR_TOKEN \
                              -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                        '''
                    }
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                sh '''
                    docker build -t leave-service-2024sl70017:v2 .
                '''
            }
        }

        stage('Deploy to Staging') {
            steps {
                echo 'Deploying application to staging...'
                sh '''
                    docker rm -f leave-service-staging || true

                    docker run -d \
                      --name leave-service-staging \
                      -e APP_ENV=staging \
                      -p 8085:8080 \
                      leave-service-2024sl70017:v2

                    sleep 3
                    curl --fail http://localhost:8085
                '''
            }
        }
    }
}

