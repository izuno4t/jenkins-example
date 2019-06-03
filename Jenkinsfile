pipeline {
/*
    agent { 
        docker {
            image 'maven:3.6.1-jdk-8-slim'
            // image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2:z -u root'
            reuseNode true
        }
    }
    */
    agent any
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
            // 並列処理の場合はparallelメソッドを使う
            parallel {
                stage('ステップカウント') {
                    steps {
                     // レポート作成
                     // outputFileとoutputFormatを指定するとエクセルファイルも作成してくれる
                     stepcounter outputFile: 'stepcount.xls',
                     outputFormat: 'excel',
                     settings: [
                         [key:'Java', filePattern: "**/*.java"],
                         [key:'SQL', filePattern: "**/*.sql"],
                         [key:'HTML', filePattern: "**/*.html"],
                         [key:'JavaScript', filePattern: "**/*.js"],
                         [key:'CSS', filePattern: "**/*.css"]
                     ]
                     // 一応エクセルファイルも成果物として保存する
                     archiveArtifacts "stepcount.xls"
                    }
                }
                stage('LOC') {
                    steps {
                        sh 'sloccount --duplicates --wide --details . > sloccount.sc'
                    }
                }
                stage('タスクスキャン') {
                    steps {
                        step([
                            $class: 'TasksPublisher',
                            canComputeNew: true,
                            pattern: '**/*.java',
                            excludePattern: '**/*.Test.java',
                            defaultEncoding: 'UTF-8',
                            // 集計対象を検索するときに大文字小文字を区別するか
                            ignoreCase: true,
                            // 優先度別に集計対象の文字列を指定できる
                            // 複数指定する場合はカンマ区切りの文字列を指定する
                            high: 'FIXME',
                            normal: 'TODO',
                            low: 'XXX'
                        ])
                    }
                }
             }
         }
        stage('後処理'){
            steps {
                step ([
                    $class: 'JUnitResultArchiver',
                    testResults: 'target/surefire-reports/TEST-*.xml'
                ])
                step ([
                    $class: 'sloccountPublish',
                    encoding: 'UTF-8',
                    pattern: 'target/sloccount.scc'
                ])
            }
        }
    }
}