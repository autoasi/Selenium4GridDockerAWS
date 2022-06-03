pipeline {
    agent any

    stages {
        stage('Building') {
            steps {
                echo 'Building the Dev project.'
            }
        }
        stage('Testing') {
            steps {
                echo 'Checking out the code from GitHub.'
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'c6a40ca6-d21e-4046-bf38-137f1032873a', url: 'https://github.com/autoasi/Selenium4GridDockerAWS.git']]])
                echo 'Executing the tests via Maven CLI.'
                sh 'mvn clean test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the code on Staging server.'
            }
        }
    }
}
