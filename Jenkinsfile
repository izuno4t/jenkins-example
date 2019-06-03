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
                     'ステップカウント': {
                         // レポート作成
                         // outputFileとoutputFormatを指定するとエクセルファイルも作成してくれる
                         stepcounter outputFile: 'stepcount.xls', outputFormat: 'excel', settings: [
                             [key:'Java', filePattern: "${javaDir}/**/*.java"],
                             [key:'SQL', filePattern: "${resourcesDir}/**/*.sql"],
                             [key:'HTML', filePattern: "${resourcesDir}/**/*.html"],
                             [key:'JS', filePattern: "${resourcesDir}/**/*.js"],
                             [key:'CSS', filePattern: "${resourcesDir}/**/*.css"]
                         ]
                         // 一応エクセルファイルも成果物として保存する
                         archiveArtifacts "stepcount.xls"
                     },
                     'タスクスキャン': {
                         step([
                             $class: 'TasksPublisher',
                             pattern: './**',
                             // 集計対象を検索するときに大文字小文字を区別するか
                             ignoreCase: true,
                             // 優先度別に集計対象の文字列を指定できる
                             // 複数指定する場合はカンマ区切りの文字列を指定する
                             high: 'System.out.System.err',
                             normal: 'TODO,FIXME,XXX',
                         ])
                     }
                 )
             }
         }
        stage('Post'){
            steps {
                step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml' ])
            }
        }
    }
}