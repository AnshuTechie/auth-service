# Auth Service (Spring Boot + JWT + PostgreSQL)

A Spring Boot microservice that provides **user registration, login, and role-based authentication** using **JWT**.  
Supports three roles:
- `USER`
- `OWNER`
- `ADMIN`

---

## 📜 Features
- User Registration (with role selection)
- Login with JWT token generation
- Role-based access control (`@PreAuthorize` & Security Config)
- Password hashing with BCrypt
- Global exception handling with custom error responses
- Unit tests (JUnit + Mockito)
- Integration tests for controllers

---

## ⚙ Prerequisites

Before running the service, ensure you have:

- **Java 21+**
- **Maven 3.9+**
- **Docker** (for PostgreSQL container)
- **Postman** (optional, for API testing)

---

## 🐳 Running PostgreSQL with Docker

Start the database container:
```bash
docker run -d \
  --name auth-service-postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_DB=authdb \
  -p 5433:5432 \
  -v auth_pg_data:/var/lib/postgresql/data \
  postgres:15





DB connection details:


Host: localhost
Port: 5433
Database: authdb
Username: postgres
Password: postgres



📂 Project Structure

src/
 ├── main/java/com/authentication/auth_service/
 │    ├── config/              # Spring Security config
 │    ├── controller/          # REST controllers
 │    ├── dto/                 # Request & Response DTOs
 │    ├── exception/           # Custom exceptions + global handler
 │    ├── filter/              # JWT authentication filter
 │    ├── model/               # Entity & enums
 │    ├── repository/          # Spring Data JPA Repositories
 │    ├── service/             # Business logic
 │    └── util/                # JWT utilities
 └── test/java/...              # Unit & integration tests
 
 
🚀 How to Run Locally
1️⃣ Clone the repository

git clone https://github.com/your-username/auth-service.git
cd auth-service

2️⃣ Start PostgreSQL

docker start auth-service-postgres

3️⃣ Build the project
mvn clean install -DskipTests

4️⃣ Run the Spring Boot application


mvn spring-boot:run

The service will be available at:
http://localhost:8081

🧪 Running Tests
Run all unit tests:

mvn test


🔑 API Endpoints
1. Register User

POST /auth/register
Content-Type: application/json

Request Body:


{
  "email": "user@example.com",
  "username": "testuser",
  "password": "pass123",
  "role": "USER"
}


Response:


{
  "message": "User registered successfully",
  "email": "user@example.com"
}
2. Login


POST /auth/login
Content-Type: application/json



Request Body:


{
  "email": "user@example.com",
  "password": "pass123"
}


Response:


{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "email": "user@example.com",
  "role": "USER"
}
3. Get Profile (Authenticated)

GET /auth/profile
Authorization: Bearer <JWT_TOKEN>
Response:


Hello, user@example.com! This is your profile.
🔒 Role-Based Access
Example protected endpoints (for testing RBAC):

GET /admin/dashboard → requires ADMIN role

GET /owner/dashboard → requires OWNER role

GET /user/dashboard → requires USER role

If you try without the correct role, you'll get:

{
  "timestamp": "2025-08-10T09:00:00",
  "status": 403,
  "message": "Access Denied",
  "path": "/admin/dashboard"
}