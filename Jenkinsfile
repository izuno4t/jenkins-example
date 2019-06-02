pipeline {
    agent { 
        docker { 
            image 'maven:3.6.1-jdk-8'
            args '-v /Users/izuno/.m2:/root/.m2'
        } 
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean site'
            }
        }
        stage('タスクスキャン'){
            steps {
                openTasks canComputeNew: false, defaultEncoding: '', excludePattern: '**/*Test.java', healthy: '', high: 'FIXME', ignoreCase: true, low: 'XXX', normal: 'TODO', pattern: '**/*.java', unHealthy: ''
            }
        }
    }
}