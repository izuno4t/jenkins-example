pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile'
            dir 'docker/node'
            args '-v /.cache/ -v /.bower/  -v /.config/configstore/'
        }
    }
    stages {
        stage('Test') {
            steps {
                sh 'npm install'
                sh 'npm test'
            }
        }
    }
}
