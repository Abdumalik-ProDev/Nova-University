# Nova - Modular Monolith Platform

## Overview

Nova is a modular monolith platform built with Spring Boot, designed for managing students, teachers, staff, and security in an educational institution. It follows clean architecture principles, domain-driven design, and is microservice-ready.

## Architecture

The platform consists of four main modules:

- **student-module**: Manages student data and operations
- **teacher-module**: Manages teacher data and operations
- **staff-module**: Manages staff data and operations
- **security-module**: Handles authentication, authorization, and JWT management

Each module follows a strict package structure:
- `model`: JPA entities
- `dto`: Data Transfer Objects (records)
- `repository`: Data access layer
- `service`: Business logic
- `controller`: REST API endpoints
- `mapper`: Entity-DTO conversion
- `exception`: Custom exceptions
- `config`: Configuration classes
- `client`: Feign clients for inter-module communication

## Features

- JWT-based authentication and authorization
- Role-based access control (STUDENT, TEACHER, STAFF, ADMIN)
- BCrypt password hashing
- OpenAPI/Swagger documentation
- Feign clients for inter-module communication
- Global exception handling
- Validation on all inputs
- Structured logging with SLF4J

## Prerequisites

- Java 17
- Maven 3.6+
- PostgreSQL
- Docker (optional)

## Getting Started

The recommended way to run this application is using **Docker Compose**, as this ensures the complex initialization of the four separate PostgreSQL databases, port isolation, and the execution of the full microservices cluster works flawlessly.

### 1. Run using Docker Compose (Recommended)

1. Clone the repository:
   ```bash
   git clone https://github.com/Abdumalik-ProDev/Nova-University.git
   cd Nova-University
   ```

2. Build and launch all services:
   ```bash
   docker compose build
   docker compose up -d
   ```
   *Note: This automatically provisions PostgreSQL on port 5433 (to avoid conflicts with local DBs), executes `init.sql` to initialize `nova_student`, `nova_teacher`, `nova_staff`, and `nova_security` databases, builds all applications leveraging Eclipse Temurin JDK 17, and spins up the four microservices.*

3. Monitor logs to ensure they've fully started:
   ```bash
   docker compose logs -f
   ```

### 2. Run Locally (Manual Mode)

If you prefer to run it locally without Docker for development:
1. Ensure Java 17 and Maven 3.6+ are installed.
2. Ensure you have a local PostgreSQL DB available.
3. You must execute `./init.sql` against your PostgreSQL server to create the isolated databases.
4. Modify the `application.yaml` or set environment variables to point to your DB credentials.
5. In separate terminal instances, run each module:
   ```bash
   ./mvnw -pl security-module spring-boot:run
   ./mvnw -pl staff-module spring-boot:run
   ./mvnw -pl student-module spring-boot:run
   ./mvnw -pl teacher-module spring-boot:run
   ```

## API Documentation (Swagger)

Because Nova is structured as independent microservices, each module serves its own isolated Swagger API specification.
Once the backend is fully booted via Docker, you can access their APIs at:

- **Security Module** (Port 8081): http://localhost:8081/swagger-ui/index.html
- **Staff Module** (Port 8082): http://localhost:8082/swagger-ui/index.html
- **Student Module** (Port 8083): http://localhost:8083/swagger-ui/index.html
- **Teacher Module** (Port 8084): http://localhost:8084/swagger-ui/index.html

## Environment Settings & Common Pitfalls

- **Port Collisions**: If `docker compose up -d` fails, ensure ports `8081` through `8084` are strictly available. PostgreSQL is safely mapped to `5433` by default to not disrupt your existing `5432` daemons.
- **Lombok Compilation Errors**: If you encounter `java.lang.ExceptionInInitializerError: com.sun.tools.javac.code.TypeTag` locally, you are likely using JDK 21+ with `lombok:1.18.34`. To prevent this, strictly use **JDK 17** locally, or always compile inside the Docker container (`docker compose build`) which safely enforces Alpine Java 17.
- **Eureka Discovery**: You may see "Eureka HTTP Client" warnings log natively. The codebase anticipates a future Eureka server. We've safely disabled this in Docker Compose by declaring `eureka.client.enabled=false`.

## Security

- All endpoints are secured with JWT
- Passwords are hashed using BCrypt
- Role-based permissions are enforced

## Contributing

1. Follow the coding standards in `AGENTS.md`
2. Use the provided package structure
3. Ensure all code is tested
4. Update documentation as needed

## License

This project is licensed under the MIT License.
