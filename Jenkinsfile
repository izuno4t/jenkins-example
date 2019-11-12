pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        timestamps()
    }
    triggers {
        pollSCM('H/5 * * * *')
    }
    stages {
        stage('PreProcess') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
        stage('build') {
            agent {
                docker {
                    image 'openjdk:8'
                    args '-v $HOME/.m2:/root/.m2 -v $HOME/.gradle:/root/.gradle -u root'
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
                            docker.image('openjdk:8').inside("-v $HOME/.m2:/root/.m2:z -v $HOME/.gradle:/root/.gradle -u root --link ${c.id}:mysql-server") {
                                sh "./gradlew flywayMigrate -Dflyway.configFiles=jenkins/application-test.properties"
                            }
                        }
                        stage('Test') {
                            docker.image('openjdk:8').inside("-v $HOME/.m2:/root/.m2:z -v $HOME/.gradle:/root/.gradle  -u root --link ${c.id}:mysql-server") {
                                sh "export SPRING_CONFIG_ADDITIONAL_LOCATION=file:jenkins/ ./gradlew test"
                            }
                        }
                        stage('Verify') {
                            docker.image('openjdk:8').inside("-v $HOME/.m2:/root/.m2:z -v $HOME/.gradle:/root/.gradle  -u root --link ${c.id}:mysql-server") {
                                sh "export SPRING_CONFIG_ADDITIONAL_LOCATION=file:jenkins/ ./gradlew check"
                            }
                        }
                    }
                }
            }
        }
    }
}

