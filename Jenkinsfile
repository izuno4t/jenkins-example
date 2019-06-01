pipeline {
    agent { docker { image 'maven:3.6.1' } }
    stages {
        stage('clean') {
            steps {
                sh './mvnw clean'
            }
        }
        stage('build') {
            steps {
                sh './mvnw compile'
            }
        }
        stage('site') {
            steps {
                sh './mvnw site'
            }
        }
    }
}