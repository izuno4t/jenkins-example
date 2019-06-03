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
            }
        }
        /*
        stage('site') {
            steps {
                sh 'mvn site'
            }
        }
        */
        stage('静的コード解析') {
            steps {
                // 並列処理の場合はparallelメソッドを使う
                parallel(
                    'タスクスキャン': {
                        step (
                            openTasks canComputeNew: false, defaultEncoding: 'UTF-8', pattern: '**/*.java', excludePattern: '**/*Test.java', ignoreCase: true, high: 'FIXME', normal: 'TODO', low: 'XXX', healthy: '', unHealthy: ''
                        )
                    }
                )
            }

            post {
                always {
                   // JavaDocの警告を収集
                    step([
                        $class: 'WarningsPublisher',
                        consoleParsers: [
                            [parserName: 'JavaDoc Tool']
                        ],
                        canComputeNew: false,
                        canResolveRelativesPaths: false,
                        usePreviousBuildAsReference: true
                    ])
                }
            }
        }
        stage('Post'){
            steps {
                step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml' ])
            }
        }
    }
}