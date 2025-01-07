# Student Management System

![Student Management System](![integrated](https://github.com/user-attachments/assets/1373d13b-7dc1-4104-93d3-87754afcd5fa)
)

## **WEB SERVICE : REST A.P.I.**

The Student Management System REST API is designed to simplify and streamline the management of students and administrators in an educational environment. This API enables efficient handling of user authentication, student records, and admin operations, ensuring a secure and robust framework for educational institutions.

## Features

- Implements Spring Security and JSON Web Token (JWT) for secure authentication and authorization of users and administrators.
- Provides efficient functionality for pagination, sorting, and searching of student and admin data.
- Includes custom exception handling for a robust and error-resilient system.
- Utilizes custom request and response DTOs for efficient data exchange.
- Designed for ease of integration with any REST-compliant client.
- Built on RESTful architecture with high scalability and reliability.

## Tech Stack

- **Backend:** Java, Spring Boot, Hibernate
- **Database:** MySQL
- **Build Tool:** Maven
- **Testing & Documentation:** Postman

## Dependencies

- Spring Security
- Spring Boot DevTools
- Spring Data JPA
- Hibernate
- MySQL Driver
- Lombok
- Model Mapper
- Validation
- Logger

## User Functionalities

### **Authentication Management**

- Endpoint for User Registration
- Endpoint for User Login

### **Student Management**

- Endpoint for Updating Student Records
- Endpoint for Retrieving Student Details
- Endpoint for Deleting Student Records
- Endpoint for Searching Students by Name, ID, or Other Attributes

### **Admin Management**

- Endpoint for Adding Student Records
- Endpoint for Creating Admin Accounts
- Endpoint for Updating Admin Profiles
- Endpoint for Retrieving Admin Details
- Endpoint for Deleting Admin Accounts

### **General Functionalities**

- Role-based Access Control for Admin and Students
- Pagination and Sorting of Records

## Setting & Installation

### Prerequisites

1. Install Spring Tools Suite:
   ```bash
   https://spring.io/tools
   ```
2. Install MySQL Community Server:
   ```bash
   https://dev.mysql.com/downloads/mysql/
   ```

### Clone the Repository

```bash
git clone https://github.com/YourGitHubUsername/Student-Management-System
```

### Configure MySQL

- Create a New Database in MySQL:
  ```sql
  CREATE DATABASE student_management_db;
  ```

- Update the `application.properties` file with your MySQL credentials:

```properties
spring.datasource.username=your-username
spring.datasource.password=your-password
```

### Run the Application

1. Open the project in Spring Tools Suite.
2. Navigate to the main application class `StudentManagementSystemApplication.java`.
3. Run the application as a Spring Boot App.
4. The default server port is `8080`. To change it, update the `server.port` property in `application.properties`.


## Base URL

```bash
http://localhost:8080
```

## Postman Documentation

Explore API endpoints, requests, and responses:

**POSTMAN DOCUMENTATION:** [Link to Postman Collection](https://documenter.getpostman.com/view/your-collection-id)

## Important Notes

- Ensure cookies are enabled for JWT authentication.
- Admin roles are predefined in the database. Only admins can create other admin accounts.
- JWT tokens expire in 20 minutes, requiring re-login for new sessions.

## Contributions

Contributions are welcome! Fork the repository, make changes, and submit a pull request. Your contributions help enhance the system's capabilities.

## Authors

- [Tejas Vilas Medade](https://github.com/tejasmedade)

## Acknowledgements

- [Synchrony Financial](https://www.synchrony.com/)

