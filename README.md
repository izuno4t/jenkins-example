# jenkins-example

![Java CI with Maven](https://github.com/izuno4t/jenkins-example/workflows/Java%20CI%20with%20Maven/badge.svg)

Jenkins で使うための Maven POM 設定のサンプル

## maven

* 依存関係の最新化

自動で更新

```bash
mvn versions:use-latest-versions
```

最新化の確認

```bash
mvn versions:display-dependency-updates
```

[mavenでdependencyを自動的に更新](https://qiita.com/yuichielectric/items/db724a053b11a666c1fe)
