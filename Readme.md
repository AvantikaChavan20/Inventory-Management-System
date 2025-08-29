# Inventory Management System – Microservices with Spring Boot & Spring Cloud  


## Overview
The Inventory Management System is a distributed microservices-based application developed using Spring Boot and Spring Cloud. It is designed to simulate a real-world inventory/e-commerce platform, enabling seamless management of users, products, inventory, carts, and payments with high scalability and modularity.

This system follows modern enterprise architecture patterns, ensuring each microservice is independently deployable, has its own database, and communicates with others securely via API Gateway and Eureka Service Discovery.


## Key capabilities include:
- Service discovery using **Eureka Server**  
- API routing and centralized access via **Spring Cloud Gateway**  
- Authentication and authorization with **Spring Security + JWT**  
- Independent services for **User, Product, Inventory, Cart, and Payment**  
- Inter-service communication with **OpenFeign**  
- Independent persistence layer per service (**MySQL/H2**)  

---

## Table of Contents
- [Features](#features)  
- [Tech Stack](#tech-stack)  
- [Service Breakdown](#service-breakdown)  
- [Security Workflow](#security-workflow)  
- [Getting Started](#getting-started)  
- [Future Enhancements](#future-enhancements)  
- [License](#license)  

---

## Features
- Service registration and discovery with **Eureka**  
- Centralized API Gateway for routing and security enforcement  
- Token-based authentication with **JWT**  
- Product, inventory, cart, and payment domain microservices  
- Independent databases per service ensuring data isolation  
- Exception handling and structured logging  

---

## Tech Stack
- **Language:** Java 17  
- **Frameworks:** Spring Boot, Spring Cloud  
- **Security:** Spring Security, JWT  
- **Databases:** MySQL, H2 (for development/testing)  
- **Build Tool:** Maven  

---

## Service Breakdown

| Service            | Port  | Responsibility                        |
|---------------------|-------|--------------------------------------|
| Eureka Server       | 8761  | Service registry and discovery       |
| API Gateway         | 8080  | Routing, centralized access, JWT validation |
| User Service        | 8085  | Authentication, user management      |
| Product Service     | 8084  | Product catalog management           |
| Inventory Service   | 8083  | Inventory and stock management       |
| Cart Service        | 8082  | Shopping cart management             |
| Payment Service     | 8086  | Payment processing                   |

---

## Security Workflow
1. A client authenticates via the **User Service**.  
2. On successful login, the User Service issues a **JWT token**.  
3. The client attaches the token to subsequent requests in the **Authorization header**.  
4. The **API Gateway** validates the token before forwarding the request to downstream services.  
5. Domain services trust only requests that pass through the gateway with a valid token.  

---

## Getting Started

### Prerequisites
- Java 17+  
- Maven 3.8+  
- MySQL database running locally  

### Setup and Run
```bash
# Clone the repository
git clone https://git@github.com:AvantikaChavan20/Inventory-Management-System.git
cd inventory-management-system

# Build all modules
mvn clean install

# Start services
cd eureka-server && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
cd user-service && mvn spring-boot:run
cd product-service && mvn spring-boot:run
cd inventory-service && mvn spring-boot:run
cd cart-service && mvn spring-boot:run
cd payment-service && mvn spring-boot:run
```

### Service Ports
- Eureka Server: `8761`  
- API Gateway: `8080`  
- User Service: `8085`  
- Product Service: `8084`  
- Inventory Service: `8083`  
- Cart Service: `8082`  
- Payment Service: `8086`  

--- 

