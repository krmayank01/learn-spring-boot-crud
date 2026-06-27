# Spring Boot CRUD (Java 21)

A simple learning project that demonstrates a CRUD REST API using Spring Boot.

## What this app includes
- Spring Boot Web for REST endpoints
- Spring Data JPA for persistence
- H2 in-memory database for local testing
- Validation for request body checks
- Global exception handling for clean API errors

## API Endpoints
- `GET /api/products` - list all products
- `GET /api/products/{id}` - get one product
- `POST /api/products` - create product
- `PUT /api/products/{id}` - update product
- `DELETE /api/products/{id}` - delete product

## Run
```bash
./mvnw spring-boot:run
```

## Test
```bash
./mvnw test
```
