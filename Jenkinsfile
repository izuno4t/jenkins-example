pipeline {
    agent {
        docker { image 'node:12-alpine' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'chown -R 501:20 /.npm'
                sh 'npm install'
                sh 'npm test'
            }
        }
    }
}
