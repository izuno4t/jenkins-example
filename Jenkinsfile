pipeline {
    agent {
        dockerfile {
            filename 'Dockerfile'
            dir 'docker/postgres'
            label 'postgres:12_ja'
        }
    }
    stages {
        stage('PreProcess') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
        // stage('build') {
        //     agent {
        //         docker {
        //             image 'azul/zulu-openjdk-alpine:8u202'
        //             args '-v $HOME/.m2:/root/.m2:z -u root'
        //             reuseNode true
        //         }
        //     }
        //     steps {
        //         sh './mvnw clean compile'
        //     }
        // }
        stage('Test & Verify') {
            steps {
                script{
                    docker.image('postgres:12_ja').withRun('-e "POSTGRES_DB=example" -e "POSTGRES_USER=postgres" -e "POSTGRES_PASSWORD=password" -e "POSTGRES_INITDB_ARGS=--encoding=UTF-8 --lc-collate=C --lc-ctype=ja_JP.UTF-8"') { c ->
                        stage('Database Setup') {
                            docker.image('postgres:12_ja').inside("--link ${c.id}:db") {
                                sh "while ! pg_isready -hdb -q -d example -U postgres; do sleep 1; done"
                            }
                            docker.image('azul/zulu-openjdk-alpine:8').inside("-v $HOME/.m2:/root/.m2:z -u root --link ${c.id}:db") {
                                sh "./mvnw clean flyway:migrate -Dflyway.configFiles=./src/main/resources/application.properties -Dflyway.url=jdbc:postgresql://db:5432/example"
                            }
                        }
                        // stage('Test') {
                        //     docker.image('azul/zulu-openjdk-alpine:8u202').inside("-v $HOME/.m2:/root/.m2:z -u root --link ${c.id}:mysql-server") {
                        //         sh "./mvnw clean test -Dspring.datasource.url=jdbc:mysql://mysql-server:3306/example?autoreconnect=true"
                        //     }
                        // }
                        // stage('Verify') {
                        //     docker.image('azul/zulu-openjdk-alpine:8u202').inside("-v $HOME/.m2:/root/.m2:z -u root --link ${c.id}:mysql-server") {
                        //         sh "./mvnw verify -Dspring.datasource.url=jdbc:mysql://mysql-server:3306/example?autoreconnect=true"
                        //     }
                        // }
                    }
                }
            }
        }
    //     stage('レポーティング') {
    //         // 並列処理の場合はparallelメソッドを使う
    //         parallel {
    //             stage('JavaDoc') {
    //                 agent {
    //                     docker {
    //                         image 'azul/zulu-openjdk-alpine:8u202'
    //                         args '-v $HOME/.m2:/root/.m2:z -u root'
    //                         reuseNode true
    //                     }
    //                 }
    //                 steps {
    //                     sh './mvnw javadoc:javadoc'
    //                 }
    //             }
    //             stage('ステップカウント') {
    //                 steps {
    //                  // レポート作成
    //                  // outputFileとoutputFormatを指定するとエクセルファイルも作成してくれる
    //                  stepcounter outputFile: 'stepcount.xls',
    //                  outputFormat: 'excel',
    //                  settings: [
    //                      [key:'Java', filePattern: "src/**/*.java"],
    //                      [key:'SQL', filePattern: "src/**/*.sql"],
    //                      [key:'HTML', filePattern: "src/**/*.html"],
    //                      [key:'JavaScript', filePattern: "src/**/*.js"],
    //                      [key:'CSS', filePattern: "src/**/*.css"]
    //                  ]
    //                  // 一応エクセルファイルも成果物として保存する
    //                  archiveArtifacts "stepcount.xls"
    //                 }
    //             }
    //             stage('LOC') {
    //                 steps {
    //                     sh 'sloccount $WORKSPACE/src --duplicates --wide --details . > target/sloccount.sc'
    //                 }
    //             }
    //             stage('タスクスキャン') {
    //                 steps {
    //                     step([
    //                         $class: 'TasksPublisher',
    //                         canComputeNew: true,
    //                         pattern: '**/*.java',
    //                         excludePattern: '**/*.Test.java',
    //                         defaultEncoding: 'UTF-8',
    //                         // 集計対象を検索するときに大文字小文字を区別するか
    //                         ignoreCase: true,
    //                         // 優先度別に集計対象の文字列を指定できる
    //                         // 複数指定する場合はカンマ区切りの文字列を指定する
    //                         high: 'FIXME',
    //                         normal: 'TODO',
    //                         low: 'XXX'
    //                     ])
    //                 }
    //             }
    //         }
    //     }
    }
    // post {
    //     always {
    //         junit testResults: '**/target/surefire-reports/TEST-*.xml'
    //         jacoco()
    //     }
    //     success {
    //         recordIssues enabledForFailure: true, tools: [mavenConsole(), java(), javaDoc()]
    //         recordIssues enabledForFailure: true, aggregatingResults: true, tools: [checkStyle(), findBugs(), spotBugs(), cpd(pattern: '**/target/cpd.xml'), pmdParser(pattern: '**/target/pmd.xml')]
    //     }
    // }
}

