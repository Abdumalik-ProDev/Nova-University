# AGENT.md — Nova (Universitas Nova)

## 1. Purpose

This document defines **strict engineering standards, architectural constraints, and coding rules** for building the **Nova modular monolith platform**.

All generated or written code **MUST comply** with these rules.

---

## 2. Architecture Overview

Nova is a **modular monolith** with strict module isolation and clear domain boundaries.

### Modules (MANDATORY)

* `student-module`
* `teacher-module`
* `staff-module`
* `security-module`

### Core Principles

* Domain-driven modularization
* Clean architecture layering
* No cross-module leakage
* Microservice-ready boundaries
* Production-grade security by default

---

## 3. Package Structure (STRICT)

Each module MUST follow:

```
com.nova.<module>

├── model
├── dto
├── repository
├── mapper
├── service
├── controller
├── exception
├── config
```

No deviation allowed.

---

## 4. Coding Standards

### 4.1 General Rules

* Code MUST be **senior-level, production-ready**
* No placeholder logic
* No TODOs in final code
* No basic constructors
* No manual getters/setters
* Prefer immutability where possible

---

## 5. Mandatory Annotations

### 5.1 Persistence Layer

All entities MUST use:

* `@Entity`
* `@Table`
* `@Id`
* `@GeneratedValue`
* `@Column`

Validation:

* `@Email`
* '@Pattern`'
* `@NotNull`
* `@Size`

---

### 5.2 Lombok (MANDATORY)

DO NOT write boilerplate manually.

Use:

* `@Data` OR `@Getter` + `@Builder`
* `@AllArgsConstructor`
* `@NoArgsConstructor`
* `@Builder`
* `@Slf4j`

---

### 5.3 Spring Stereotypes

* `@Service`
* `@Repository`
* `@RestController`
* `@Configuration`
* `@Component`

---

### 5.4 OpenAPI / Swagger

All APIs MUST be documented:

* `@Operation`
* `@Tag`
* `@Schema`
* `@Parameter`

---

## 6. DTO Strategy (STRICT)

### MUST USE `record` for DTOs

Example:

```java
public record StudentRequestDTO(
    @NotNull String name,
    @Email String email
) {}
```

### Rules

* DTOs MUST be immutable
* No entity exposure in controllers
* Use mapper layer for conversion

---

## 7. Mapper Layer

* Mandatory separation between entity and DTO
* Use manual mapping OR MapStruct

Example:

```java
@Component
public class StudentMapper {
    public Student toEntity(StudentRequestDTO dto) { ... }
    public StudentResponseDTO toDTO(Student entity) { ... }
}
```

---

## 8. Repository Layer

* Extend `JpaRepository`
* No business logic allowed
* Only persistence operations

---

## 9. Service Layer

* Business logic ONLY
* Annotated with `@Service`
* Use `@Transactional` where needed
* Logging via `@Slf4j`

Example:

```java
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    ...
}
```

---

## 10. Controller Layer

* Annotated with `@RestController`
* Use DTOs only
* No entity exposure
* Must include OpenAPI annotations

---

## 11. Security Standards (CRITICAL)

### 11.1 Authentication

* JWT-based authentication
* Stateless sessions

### 11.2 Password Hashing

* MUST use `BCrypt`
* MUST include salt (handled internally by BCrypt)

```java
BCryptPasswordEncoder
```

---

### 11.3 Authorization (RBAC)

Roles:

* `ROLE_STUDENT`
* `ROLE_TEACHER`
* `ROLE_STAFF`
* `ROLE_ADMIN`

Use:

```java
@PreAuthorize("hasRole('STUDENT')")
```

---

### 11.4 Security Module Responsibilities

* JWT generation & validation
* UserDetailsService
* Password encoding
* Authentication endpoints

---

## 12. Logging (MANDATORY)

Use:

```java
@Slf4j
```

Rules:

* Log at service layer
* Use structured, meaningful logs
* No sensitive data logging

---

## 13. Exception Handling

* Custom exceptions per module
* Global handler using `@RestControllerAdvice`

Example:

```java
@ExceptionHandler(StudentNotFoundException.class)
```

---

## 14. Database Rules

* Use `@Column` explicitly for all fields
* Avoid lazy pitfalls unless controlled
* Use UUID or Long IDs (consistent per module)

---

## 15. Inter-Module Communication

### STRICT RULES

❌ NOT ALLOWED:

* Direct repository access across modules
* Direct entity sharing

✅ ALLOWED:

* DTOs
* Facade interfaces

---

## 16. API Design

* RESTful conventions
* Versioning recommended (`/api/v1`)
* Proper HTTP status codes

---

## 17. Production-Grade Requirements

* Validation on all inputs
* Proper error responses
* Logging coverage
* Security enforced globally
* Clean separation of concerns

---

## 18. Code Quality Expectations

* Readable, minimal, expressive
* No redundancy
* No over-engineering
* Consistent naming

---

## 19. Future Readiness

The system MUST be:

* Microservice-extractable
* Event-driven ready (Kafka later)
* Scalable and secure

---

## 20. Summary

This is a **strict engineering contract**.

Any implementation that:

* breaks modular boundaries
* avoids annotations
* uses boilerplate manually
* ignores DTO records

→ is considered **invalid**.

The goal is to maintain:

> **Clean architecture + production-grade code + microservice readiness**

---

END OF FILE