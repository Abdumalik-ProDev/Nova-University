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

1. Clone the repository:
   ```bash
   git clone <https://github.com/Abdumalik-ProDev/Nova-University/.git>
   cd Nova-University
   ```

2. Set up the database:
   - Create a PostgreSQL database named `nova_db`
   - Update `.env` file with your database credentials

3. Build the project:
   ```bash
   ./mvnw clean install
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

   Or run individual modules:
   ```bash
   cd student-module
   ../mvnw spring-boot:run
   ```

## API Documentation

Once the application is running, access Swagger UI at:
- http://localhost:8080/swagger-ui.html

## Docker

To run with Docker:

1. Build the images:
   ```bash
   docker-compose build
   ```

2. Start the services:
   ```bash
   docker-compose up
   ```

## Environment Variables

Copy `.env` and configure as needed:

- `DB_HOST`: Database host
- `DB_PORT`: Database port
- `DB_NAME`: Database name
- `DB_USERNAME`: Database username
- `DB_PASSWORD`: Database password
- `JWT_SECRET`: JWT secret key
- `JWT_EXPIRATION`: JWT token expiration time
- `SERVER_PORT`: Server port

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
