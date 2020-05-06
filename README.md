# jenkins-pipeline-example
Jenkins Pipeline のサンプル

## PostgreSQL with Docker Compose


## PostgreSQL with Docker

`ja_JP.UTF-8`を扱えるようにレジストリのイメージに変更を加えてビルド

```bash
docker build -t postgres:12_ja . -f docker/postgres/Dockerfile
```

起動時にデータベースを作成して起動

```bash
docker run -d -p 5432:5432 -e "POSTGRES_DB=example" -e "POSTGRES_USER=postgres" -e "POSTGRES_PASSWORD=password" -e "POSTGRES_INITDB_ARGS=--encoding=UTF-8 --lc-collate=C --lc-ctype=ja_JP.UTF-8" postgres:12_ja
```

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
