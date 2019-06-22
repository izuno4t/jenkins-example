# jenkins-pipeline-example
Jenkins Pipeline のサンプル


## Flyway

https://flywaydb.org/

* マイグレーション

```
./mvnw flyway:migrate -Dflyway.configFiles=./src/main/resources/application.properties
```

* スキーマ情報

```
./mvnw flyway:info -Dflyway.configFiles=./src/main/resources/application.properties
```