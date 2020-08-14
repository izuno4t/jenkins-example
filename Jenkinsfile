pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile'
            dir 'docker/node'
            args '-v /.cache/ -v /.bower/  -v /.config/configstore/'
        }
    }
    environment {
        HOME = '.'
    }
    stages {
        stage('Test') {
            steps {
                sh 'npm install'
                // sh 'npm install node-sass'
                sh 'npm test'
            }
        }
    }
}
