# User Consumer (Pact) Testing Solution Demo

- Small Java Maven project containing a Pact consumer test for a `user-service`. 
- Tests use JUnit 5, Pact (au.com.dius.pact), and RestAssured.
- Generates pact files that can be published to a Pact Broker or GitHub artifact.
- Setup pact-broker using docker-compose

## Project structure

- `src/test/java/consumer/UserApiTest.java` — Pact consumer test that runs a MockServer and verifies the consumer contract.
- Generated pact files are written to `target/pacts` by default.

## Prerequisites

- Java 17+ (or the Java version configured for the project)
- Maven 3.6+
- IntelliJ IDEA (recommended) on macOS (project tested in IntelliJ IDEA 2025.2.6)
- Docker (for running Pact Broker)
- See more dependencies in `pom.xml`

## Build and run tests for generating pact file(s)

From the project root:

- Clean and run tests:
  ```bash
  mvn clean test
- Run a single test:
  ```bash
  mvn -Dtest=consumer.UserApiTest test

# Publishing pacts
## (1) Setting up dockerized Pact Broker(Optional)
- Run docker compose to start a Pact Broker. Go into docker directory
  ```bash
  docker-compose up -d
  ```
- Publish pacts to the pact-broker
  ```bash
  mvn pact:publish \
  -Dpact.broker.url=http://localhost:9292 \
  -Dpact.broker.username=pact_cheers \
  -Dpact.broker.password=pact_pwd
  ```
## (2) Publish pacts to github artifact(Optional)
- Run the GitHub Action workflow defined in `.github/workflows/consumer-pact-ci.yml` to publish the generated pact files as a GitHub artifact.