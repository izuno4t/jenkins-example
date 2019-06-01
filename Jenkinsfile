pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('clean') {
            steps {
                sh './mvnw clean'
            }
        }
        stage('site') {
            steps {
                sh './mvnw site'
            }
        }
    }
}