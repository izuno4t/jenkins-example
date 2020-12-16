pipeline {
    agent any
    stages {
        stage('PreProcess') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
    }
    post {
        success {
            recordIssues enabledForFailure: true, tools: taskScanner()
        }
    }
}

