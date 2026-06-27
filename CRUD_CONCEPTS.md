# CRUD Concepts in Spring Boot (Learning Notes)

## What is CRUD?
CRUD stands for:
- **Create**: Add new data
- **Read**: Fetch existing data
- **Update**: Modify existing data
- **Delete**: Remove data

In this project, CRUD is implemented for a `Product` resource.

## Core Spring Boot Layers

### 1. Entity Layer (`Product`)
- Represents the database table structure.
- Annotated with `@Entity`.
- Fields become columns, and `@Id` marks the primary key.

### 2. Repository Layer (`ProductRepository`)
- Interface that extends `JpaRepository<Product, Long>`.
- Gives ready-made methods like `save`, `findAll`, `findById`, `delete`.
- No boilerplate SQL needed for basic operations.

### 3. Service Layer (`ProductService`)
- Holds business logic.
- Coordinates repository operations.
- Good place for validations and rules beyond basic field checks.

### 4. Controller Layer (`ProductController`)
- Exposes HTTP endpoints.
- Maps requests to service methods.
- Uses annotations like `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`.

## Validation and Error Handling
- Request validation uses `@Valid` and constraints (`@NotBlank`, `@DecimalMin`).
- `GlobalExceptionHandler` converts exceptions into clean HTTP responses.
- `ResourceNotFoundException` maps to `404 Not Found`.

## Data Flow Example (Create)
1. Client sends `POST /api/products` with JSON body.
2. Controller validates and forwards data to service.
3. Service saves using repository.
4. Database generates ID.
5. Created product is returned with `201 Created`.

## Why this structure matters
- Keeps code maintainable and testable.
- Separates responsibilities clearly.
- Makes it easier to grow from simple CRUD to real-world projects.
