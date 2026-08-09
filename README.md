# Simple Authentication App

A simple Spring Boot application built to understand the fundamentals of authentication and authorization using Spring Security and JWT.

---

# What is Authentication?

Authentication is the process of verifying the identity of a user.

In simple terms:

> "Who are you?"

When a user provides a username and password, the system verifies the credentials and determines whether the user is allowed to access the application.

---

# Project Objective

The goal of this project is to learn:

- Spring Boot fundamentals
- Layered Architecture
- Spring Security basics
- JWT Authentication
- Password Encryption using BCrypt
- DTO Pattern
- REST API Development

---

# Project Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Project structure:

```text
src/main/java/com/example/auth

├── controller
│   └── AuthController.java
│
├── dto
│   ├── RegisterRequest.java
│   ├── LoginRequest.java
│   └── AuthResponse.java
│
├── entity
│   └── User.java
│
├── repository
│   └── UserRepository.java
│
├── service
│   ├── AuthService.java
│   └── AuthServiceImpl.java
│
├── security
│   ├── JwtService.java
│   └── SecurityConfig.java
│
└── Application.java
```

---

# Development Steps

## 1. Create the User Entity

The User entity represents the `users` table inside the database.

Example attributes:

- id
- username
- password
- role

Responsibilities:

- Store user information.
- Represent a database record.
- Serve as the application's core authentication model.

---

## 2. Create RegisterRequest DTO

DTO stands for Data Transfer Object.

The purpose of this DTO is to receive registration data from the client.

Example:

```json
{
  "username": "med",
  "password": "123456"
}
```

Why use DTOs?

- Prevent exposing entities directly.
- Improve security.
- Validate incoming data.
- Decouple API contracts from database models.

---

## 3. Create LoginRequest DTO

The LoginRequest DTO is used when a user wants to authenticate.

Example:

```json
{
  "username": "med",
  "password": "123456"
}
```

Responsibilities:

- Receive login credentials.
- Transfer authentication data to the service layer.

---

## 4. Create AuthResponse DTO

The AuthResponse DTO is returned after a successful authentication.

Example:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Responsibilities:

- Return JWT tokens.
- Standardize authentication responses.

---

## 5. Create UserRepository

The repository layer is responsible for interacting directly with the database.

Example responsibilities:

- Save users.
- Find users by username.
- Update user information.
- Delete users.

Example method:

```java
Optional<User> findByUsername(String username);
```

Benefits:

- Separates persistence logic from business logic.
- Simplifies database operations through Spring Data JPA.

---

## 6. Create AuthService

The service layer contains the business logic of the application.

Example methods:

```java
AuthResponse register(RegisterRequest request);

AuthResponse login(LoginRequest request);
```

Responsibilities:

- Handle registration logic.
- Handle authentication logic.
- Validate user data.
- Generate JWT tokens.

---

## 7. Create AuthServiceImpl

AuthServiceImpl contains the actual implementation of the authentication process.

### Register Flow

1. Check if username already exists.
2. Encode password.
3. Save user in database.
4. Generate JWT token.
5. Return AuthResponse.

### Login Flow

1. Verify username.
2. Verify password.
3. Authenticate user.
4. Generate JWT token.
5. Return AuthResponse.

---

# Password Encryption

Passwords should never be stored in plain text.

❌ Bad:

```text
username : med
password : 123456
```

✅ Good:

```text
username : med
password : $2a$10$L8f...
```

Spring Security uses BCrypt:

```java
passwordEncoder.encode(password);
```

Benefits:

- Protects user credentials.
- Makes password recovery impossible from database leaks.

---

## 8. Create JwtService

JWT stands for JSON Web Token.

A JWT allows the application to identify authenticated users without storing server-side sessions.

Example Token:

```text
eyJhbGciOiJIUzI1NiJ9...
```

Responsibilities:

- Generate tokens.
- Validate tokens.
- Extract username from token.
- Check expiration dates.

Typical methods:

```java
generateToken()

extractUsername()

isTokenValid()

isTokenExpired()
```

---

## 9. Create SecurityConfig

SecurityConfig is responsible for configuring Spring Security.

Responsibilities:

- Disable CSRF for REST APIs.
- Define public endpoints.
- Define protected endpoints.
- Configure password encoding.
- Configure authentication manager.

Example:

```java
.requestMatchers("/api/auth/**")
.permitAll()
```

This allows:

```text
POST /api/auth/register
POST /api/auth/login
```

to be accessed without authentication.

All other endpoints require a valid JWT token.

---

## 10. Create AuthController

The controller acts as the entry point of the application.

Responsibilities:

- Receive HTTP requests.
- Validate request bodies.
- Call service methods.
- Return HTTP responses.

Example endpoints:

```http
POST /api/auth/register
```

```http
POST /api/auth/login
```

---

# Authentication Flow

## Register

```text
Client
   ↓
POST /api/auth/register
   ↓
AuthController
   ↓
AuthService
   ↓
Check username
   ↓
Encode password
   ↓
Save User
   ↓
Generate JWT
   ↓
Return Token
```

---

## Login

```text
Client
   ↓
POST /api/auth/login
   ↓
AuthController
   ↓
AuthService
   ↓
Verify Credentials
   ↓
Generate JWT
   ↓
Return Token
```

---

# Using the JWT Token

After login, the client receives a JWT token.

Example:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

For protected endpoints, the token must be included in the request header:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

The server validates the token before granting access.

---

# Technologies Used

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- H2 / MySQL / PostgreSQL
- Maven
- Swagger OpenAPI

---

# Future Improvements

After completing this project, consider implementing:

- CustomUserDetailsService
- JwtAuthenticationFilter
- Role-Based Authorization
- Refresh Tokens
- Email Verification
- Password Reset
- Account Activation
- OAuth2 Authentication (Google, GitHub)

---

# Conclusion

This project introduces the essential concepts of authentication in Spring Boot.

By completing it, you will understand:

- How authentication works
- How Spring Security operates
- How JWT tokens are generated and validated
- How to secure REST APIs
- How to structure a Spring Boot application using a layered architecture
