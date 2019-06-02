pipeline {
    agent { 
        docker {
            image 'maven:3.6.1-jdk-8-slim'
            // image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2:z -u root'
            reuseNode true
        }
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
                junit '**/test-reports/*.xml'
            }
        }
        stage('site') {
            steps {
                sh 'mvn site'
            }
        }
        stage('タスクスキャン'){
            steps {
                openTasks canComputeNew: false, defaultEncoding: '', excludePattern: '**/*Test.java', healthy: '', high: 'FIXME', ignoreCase: true, low: 'XXX', normal: 'TODO', pattern: '**/*.java', unHealthy: ''
            }
        }
    }
}