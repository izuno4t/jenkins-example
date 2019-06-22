# jenkins-pipeline-example
Jenkins Pipeline のサンプル


## Flyway

https://flywaydb.org/

* マイグレーション

```bash
./mvnw flyway:migrate -Dflyway.configFiles=./src/main/resources/application.properties
```

* スキーマ情報

```bash
./mvnw flyway:info -Dflyway.configFiles=./src/main/resources/application.properties
```

## 備忘

### ポートの自動選択をしたい

MySQL を立ち上げたときに、ポート番号を指定してバインディングしていると複数立ち上がった時にバインディングできずにエラーになる。
Jenkins のプラグインに [Port Allocator Plugin](https://wiki.jenkins.io/display/JENKINS/Port+Allocator+Plugin) があるので、これが Pipleline に対応してくれるといい感じ。
一応リクエストは出ているけど。。。

* [Port Allocator Pipeline support](https://issues.jenkins-ci.org/browse/JENKINS-31449)
