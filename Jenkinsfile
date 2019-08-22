pipeline {
    agent any
    stages {
        stage('PreProcess') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
        stage('build') {
            agent {
                docker {
                    image 'azul/zulu-openjdk-alpine:8u202'
                    args '-v $HOME/.m2:/root/.m2:z -u root'
                    reuseNode true
                }
            }
            steps {
                sh './gradlew clean compileJava CompileTestJava'
            }
        }
        stage('Test & Verify') {
            steps {
                script{
                    docker.image('mysql:5.7').withRun('-e "MYSQL_DATABASE=example" -e "MYSQL_USER=demo" -e "MYSQL_PASSWORD=password" -e "MYSQL_ROOT_PASSWORD=password"') { c ->
                        stage('Database Setup') {
                            docker.image('mysql:5.7').inside("--link ${c.id}:db") {
                                sh "while ! mysqladmin ping -hdb -P3306 --silent; do sleep 1; done"
                            }
                            docker.image('azul/zulu-openjdk-alpine:8u202').inside("-v $HOME/.m2:/root/.m2:z -u root --link ${c.id}:mysql-server") {
                                sh "./gradlew clean flyway:migrate -Dflyway.configFiles=./src/main/resources/application.properties -Dflyway.url=jdbc:mysql://mysql-server:3306/example?autoReconnect=true"
                            }
                        }
                        stage('Test') {
                            docker.image('azul/zulu-openjdk-alpine:8u202').inside("-v $HOME/.m2:/root/.m2:z -u root --link ${c.id}:mysql-server") {
                                sh "./gradlew clean test -Dspring.datasource.url=jdbc:mysql://mysql-server:3306/example?autoreconnect=true"
                            }
                        }
                        stage('Verify') {
                            docker.image('azul/zulu-openjdk-alpine:8u202').inside("-v $HOME/.m2:/root/.m2:z -u root --link ${c.id}:mysql-server") {
                                sh "./gradlew check -Dspring.datasource.url=jdbc:mysql://mysql-server:3306/example?autoreconnect=true"
                            }
                        }
                    }
                }
            }
        }
    }
}

