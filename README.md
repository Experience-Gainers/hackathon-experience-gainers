# challenger-hackathon Project

This project uses **Quarkus** Framework

Currently, it's being exposed on http://51.250.12.148:8080/q/swagger-ui/#/

Metrics: http://51.250.12.148:8080/q/metrics

Health: http://51.12.148:8080/q/health

## Running the application

```shell script
./mvnw compile quarkus:dev
```

## Packaging the application

Supports GraalVM Native Executable build! Yay!

```shell script
./mvnw clean package -Pnative -Dquarkus.native.container-build=true
```

## Tech Stack:

- Quarkus
- RESTEasy Reactive
- Hibernate Reactive with Panache
- PostgreSQL
- SmallRye Health
- SmallRye OpenAPI
- SmallRye Fault Tolerance
- GraalVM Native Executable
- Docker
- SmallRye Metrics

## Hint!

_You can use `java -jar challenge-hackathon-1.0-SNAPSHOT-runner.jar` to run the application as we've already included
it!_

OpenAPI spec can be found under `src/main/resources/openapi.yml`

### Additional comments from the developer

Output tokens for the final Client are fake. Real tokens are being associated with fakePaymentTokens and retrieved by
the second one on request.

I've tried to achieve full reactive support. Corner-cases where pipeline needed to be blocked at some point are put away
into a different thread not to block.
