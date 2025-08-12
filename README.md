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





**DB connection details:**


Host: localhost
Port: 5433
Database: authdb
Username: postgres
Password: postgres



**📂 Project Structure**

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
 
 
**🚀 How to Run Locally**

1️⃣ Clone the repository

git clone git@github.com:AnshuTechie/auth-service.git
cd auth-service

2️⃣ Start PostgreSQL

docker start auth-service-postgres

3️⃣ Build the project

mvn clean install 

4️⃣ Run the Spring Boot application


mvn spring-boot:run

The service will be available at:
http://localhost:8081

🧪 Running Tests
Run all unit tests:

mvn test


**🔑 API Endpoints**

**1. Register User**

POST /auth/register
Content-Type: application/json

Request Body:


{
  "email": "user@example.com",
  "username": "testuser",
  "password": "pass123",
  "role": "USER"
}


A.Response: If role is Invalid

    Failure: Invalid role (400 Bad Request)

{
  "timestamp": "2025-08-12T10:01:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid role. Allowed values: USER, OWNER, ADMIN",
  "path": "/auth/register"
}



B.Response: If email is already registered

Failure: Email already exists (400 Bad Request)

{
  "timestamp": "2025-08-12T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Email is already registered",
  "path": "/auth/register"
}



C.Response: If if everything is correct

Success : 200(OK)

{
  "message": "User registered successfully",
  "email": "user@example.com"
}


**2. Login**


POST /auth/login
Content-Type: application/json



Request Body:


{
  "email": "user@example.com",
  "password": "pass123"
}



A.Response: If password is wrong

Failure: Wrong password (401 Unauthorized)

{
  "timestamp": "2025-08-12T10:05:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid credentials",
  "path": "/auth/login"
}

B.Response: If email is wrong

Failure: Email not found (404 Not Found)

{
  "timestamp": "2025-08-12T10:06:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found",
  "path": "/auth/login"
}

C.Response: If if everything is correct

Success : 200(OK)


{
  "token": "eyJhbGciOiJIUzI1NiIs...",
  "email": "user@example.com",
  "role": "USER"
}


**3. Get Profile (Authenticated)**

GET /auth/profile
Authorization: Bearer <JWT_TOKEN>

Response:

Profile Info → ID: ccd0cf9d-b841-42b5-8e3e-eb307eec407b, Username: user@example.com



Response: without bearer token

{
  "timestamp": "2025-08-10T09:00:00",
  "status": 403,
  "message": "Access Denied",
  "path": "/admin/dashboard"
}


Hello, user@example.com! This is your profile.
🔒 Role-Based Access
Example protected endpoints (for testing RBAC):

GET /admin/dashboard → requires ADMIN role

GET /owner/dashboard → requires OWNER role

GET /user/dashboard → requires USER role

