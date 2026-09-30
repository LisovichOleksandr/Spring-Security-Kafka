# 🔐 Spring Boot JWT Authentication (Access + Refresh Tokens)

A **production-style authentication system** built with **Spring Boot** and **Spring Security**, implementing secure **JWT-based authentication** with **Access Tokens** and **Refresh Tokens**.

This project demonstrates a **stateless security architecture**, **role-based and permission-based authorization**, and modern backend authentication practices.

---

## 🚀 Features

* 🔑 JWT Authentication
* ♻️ Access Token + Refresh Token architecture
* 🔁 Refresh Token Rotation
* 🛡 Role-Based Access Control (RBAC)
* 🔐 Permission-based authorization
* 🚫 Stateless Spring Security configuration
* 🔎 Custom JWT authentication filter
* 🔑 Secure password hashing with BCrypt
* 📦 REST API authentication endpoints

---

## 🧠 Authentication Flow

```
Client Login
     │
     ▼
POST /auth/login
     │
     ▼
Access Token (short-lived)
Refresh Token (long-lived)
     │
     ▼
Client calls API with:

Authorization: Bearer ACCESS_TOKEN
     │
     ▼
Access Token expires
     │
     ▼
POST /auth/refresh
     │
     ▼
New Access Token issued
```

---

## 🛡 Authorization Model

```
ROLE_ADMIN
 ├── USER_READ
 ├── USER_WRITE
 └── USER_DELETE

ROLE_USER
 └── USER_READ
```

Permissions are mapped to roles and checked using **Spring Security authorities**.

---

---

## ⚙️ Technologies

* Java 17+
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Spring Data JPA
* Hibernate
* BCrypt Password Encoder
* Maven

---

## 🔐 Authentication Endpoints

### Login

```
POST /auth/login
```

Request:

```json
{
  "email": "user@email.com",
  "password": "password"
}
```

Response:

```json
{
  "accessToken": "JWT_TOKEN",
  "refreshToken": "REFRESH_TOKEN"
}
```

---

### Refresh Access Token

```
POST /auth/refresh
```

Request:

```json
{
  "refreshToken": "REFRESH_TOKEN"
}
```

Response:

```json
{
  "accessToken": "NEW_ACCESS_TOKEN",
  "refreshToken": "NEW_REFRESH_TOKEN"
}
```

---

## 🔒 Security Principles

This project follows modern backend security practices:

* Short-lived **Access Tokens**
* Long-lived **Refresh Tokens**
* **Stateless authentication**
* **Role & Permission based authorization**
* **Secure password storage with BCrypt**

---

## ▶️ Running the Project

Clone the repository:

```
git clone https://github.com/LisovichOleksandr/Spring-Security-2.0.git
```

Navigate to the project directory:

```
cd your-repository
```

Run the application:

```
./mvnw spring-boot:run
```

The API will start at:

```
http://localhost:8080
```

---

## 📚 Learning Goals

This project demonstrates how to implement:

* JWT authentication with **Spring Security**
* **Access Token + Refresh Token architecture**
* **Secure stateless API authentication**
* **Role and permission based authorization**

---

## 👨‍💻 Author Lisovich Oleksander

Backend security learning project focused on **modern authentication architecture with Spring Boot**.
