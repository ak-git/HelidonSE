# Simple HelidonSE "Hello World" Application with Gradle and Docker support

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
curl -X PUT -H "Content-Type: application/json" -d 'Howdy' http://localhost:8080/greet/greeting
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
git tag 2025.02
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
docker run --rm a002k/helidon-se
```

```shell
docker run -d --name helidon-se --rm a002k/helidon-se
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

and follow ```-f``` logs

```shell
docker compose logs -f helidon-se
```

to stop

```shell
docker compose down
```