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
            recordIssues enabledForFailure: true, tools:  [taskScanner(excludePattern: '**/test/**', highTags: 'FIXME', ignoreCase: true, includePattern: '**/*.java,**/*.sql,**/*.xml,**/*.properties', lowTags: 'XXX', normalTags: 'TODO')]
        }
    }
}

