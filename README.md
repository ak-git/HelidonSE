# Simple Java "Hello World" Application with Gradle and Docker support

## Run using Gradle

```shell
./gradlew run -t
```

## [Semantic versioning](https://zoltanaltfatter.com/2020/04/10/semantic-versioning-with-jgitver/)

### Plugin [fr.brouillard.oss.gradle.jgitver](https://github.com/jgitver/gradle-jgitver-plugin)

```shell
./gradlew version
```

```shell
./gradlew version | grep Version | awk '{ print $2 }'
```

### Update version and add git tag

```shell
git tag 2025.01
```

## Update dependencies

### Plugin [com.github.ben-manes.versions](https://github.com/ben-manes/gradle-versions-plugin)

```shell
./gradlew dependencyUpdates
```

## Check dependencies

### Plugin [dependency-analysis-gradle-plugin](https://github.com/autonomousapps/dependency-analysis-gradle-plugin)

```shell
./gradlew buildHealth
```

## Run docker image

```shell
docker run --rm a002k/simple-app
```

```shell
docker run -d --name simple-app --rm a002k/simple-app
```

- ```-i``` Keep STDIN open even if not attached
- ```--rm``` Remove container after stop
- ```-d``` Detach mode, Run container in background and print container ID

and follow ```-f``` logs

```shell
docker logs -f simple-app
```

to stop

```shell
docker stop simple-app
```

## Run docker image as docker-compose

```shell
docker compose run -d --name simple-app --rm simple-app
```

and follow ```-f``` logs

```shell
docker compose logs -f simple-app
```

to stop

```shell
docker compose down
```

[![Github Action Badge](https://github.com/ak-git/SimpleApp/actions/workflows/actions.yml/badge.svg)](https://github.com/ak-git/SimpleApp/actions/workflows/actions.yml/badge.svg)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=bugs)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=ak-git_SimpleApp&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=ak-git_SimpleApp)
