# Nimbleways spring boot boilerplate

## Prerequisites

- In order to use this boilerplate you need to have java 17+ installed
- You need the `make` command, and a unix based OS to run the configured commands in makefile
- Use the `mvn` wrapper (mvnw)

## Features implemented in this boilerplate

- Docker-compose file to run a local postgres database
- Usage of liquibase to generate and apply database migrations
- Mapstruct to map DTOs with entities
- Lombok for handy annotations  
- 3 types of tests :
  - unit test
  - integration test of controller layer
  - integration test of service and repository layers using H2 database
- Jacoco for test coverage
- PMD for static code analysis
- Spring Security with JWT for authentication and authorization
- Dependency Check to analyze our dependencies and make sure they are not vulnerable

## How to

### Generate a migration

Assuming you created a new entity you need to run the command :
``
cd api
make makeMigration MIGRATION_LABEL="migration-name"
``

/!\ Warning : always double-check the generated migrations to ensure their correctness

### Apply migrations

``
cd api
make applyMigrations
``

### Access Swagger UI

Go to `localhost:8080/swagger-ui.html`

By default Swagger is enabled. To disable Swagger, set the environment variable `ENABLE_SWAGGER` to `false`.

### Run tests and get Test coverage Report

To run tests and run jacoco
``
cd api
./mvnw verify
``

To get Jacoco coverage report
``
./mvnw jacoco:report
``
Then open this file with your favorite browser `api/target/site/index.html`
You can change the coverage threshold configured with Jacoco in the pom.xml

### TODO

#### Security
- Configure spring security refresh and access token
- Configure CORS
  
#### Data
- Use specifications to query data
- Use @Lazy annotations
- Use Entity Graphs

#### Code quality
- Implement appropriate PMD ruleset
- Archunit

#### External API calls
- Retrofit
