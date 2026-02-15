# Simple HelidonSE Application with Gradle and Docker support

## A simple service to greet you. Examples:

### Get default greeting message:

http://localhost:8080/simple-greet

```shell
curl -X GET http://localhost:8080/simple-greet
```

http://localhost:8080/greet

```shell
curl -X GET http://localhost:8080/greet
```

### Get greeting message for Joe:

http://localhost:8080/greet/Joe

```shell
curl -X GET http://localhost:8080/greet/Joe
```

### Change greeting

```shell
curl -X PUT -d 'Howdy' http://localhost:8080/greet/greeting
```

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
git tag 2026.01
```

## Update dependencies

### Plugin [com.github.ben-manes.versions](https://github.com/ben-manes/gradle-versions-plugin)

```shell
./gradlew dependencyUpdates --no-parallel
```

## Check dependencies

### Plugin [dependency-analysis-gradle-plugin](https://github.com/autonomousapps/dependency-analysis-gradle-plugin)

```shell
./gradlew buildHealth
```

## Run docker image

```shell
docker run --rm -p 8080:8080 a002k/helidon-se
```

```shell
docker run -d --name helidon-se --rm -p 8080:8080 a002k/helidon-se
```

- ```-i``` Keep STDIN open even if not attached
- ```--rm``` Remove container after stop
- ```-d``` Detach mode, Run container in background and print container ID

and follow ```-f``` logs

```shell
docker logs -f helidon-se
```

to stop

```shell
docker stop helidon-se
```

## Run docker image as docker-compose

```shell
docker compose run -d --name helidon-se --rm helidon-se
```

Use ```./.env``` file or overwrite as

```shell
APP_GREETING=Greet docker compose up -d
```

and follow ```-f``` logs

```shell
docker compose logs -f helidon-se
```

to stop

```shell
docker compose down
```

[![Github Action Badge](https://github.com/ak-git/HelidonSE/actions/workflows/actions.yml/badge.svg)](https://github.com/ak-git/HelidonSE/actions/workflows/actions.yml/badge.svg)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ak-git_HelidonSE&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ak-git_HelidonSE)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=ak-git_HelidonSE&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=ak-git_HelidonSE)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=ak-git_HelidonSE&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=ak-git_HelidonSE)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=ak-git_HelidonSE&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=ak-git_HelidonSE)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ak-git_HelidonSE&metric=bugs)](https://sonarcloud.io/summary/new_code?id=ak-git_HelidonSE)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=ak-git_HelidonSE&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=ak-git_HelidonSE)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=ak-git_HelidonSE&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=ak-git_HelidonSE)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=ak-git_HelidonSE&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=ak-git_HelidonSE)
