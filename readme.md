# User Consumer (Pact) Tests

Small Java Maven project containing a Pact consumer test for a `user-service`. Tests use JUnit 5, Pact (au.com.dius.pact), and RestAssured.

## Project structure

- `src/test/java/consumer/UserApiTest.java` — Pact consumer test that runs a MockServer and verifies the consumer contract.
- Generated pact files are written to `target/pacts` by default.

## Prerequisites

- Java 17+ (or the Java version configured for the project)
- Maven 3.6+
- IntelliJ IDEA (recommended) on macOS (project tested in IntelliJ IDEA 2025.2.6)

## Build and run tests

From the project root:

- Clean and run tests:
  ```bash
  mvn clean test
- Run a single test:
  ```bash
  mvn -Dtest=consumer.UserApiTest test