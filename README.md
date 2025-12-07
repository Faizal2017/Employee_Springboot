# 👥 Employee Management System

A robust and secure RESTful API for managing employee information, built with Spring Boot. This application provides comprehensive CRUD operations with role-based access control, ensuring secure and efficient employee data management.

## ✨ Features

- 🔐 **Role-Based Security** - Three-tier access control (EMPLOYEE, MANAGER, ADMIN)
- 📝 **Full CRUD Operations** - Create, Read, Update, and Delete employee records
- 📚 **Swagger Documentation** - Interactive API documentation with OpenAPI 3
- 🗄️ **H2 Database** - Lightweight, file-based database for easy setup
- ✅ **Input Validation** - Request validation using Jakarta Bean Validation
- 🛡️ **Spring Security** - Secure endpoints with HTTP Basic Authentication
- 🚀 **RESTful Architecture** - Clean, REST-compliant API design

## 🛠️ Tech Stack

- **Java** 21
- **Spring Boot** 3.3.5
- **Spring Security** - Authentication & Authorization
- **Spring Data JPA** - Database persistence
- **H2 Database** - In-memory/file-based database
- **SpringDoc OpenAPI** 2.1.0 - API documentation
- **Maven** - Dependency management

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** 21 or higher
- **Maven** 3.6+ (or use the included Maven Wrapper)
- **Git** (optional, for cloning the repository)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd employees
```

### 2. Navigate to Project Directory

```bash
cd employees
```

### 3. Build the Project

Using Maven Wrapper (recommended):

```bash
# Windows
.\mvnw.cmd clean install

# Linux/Mac
./mvnw clean install
```

Or using Maven directly:

```bash
mvn clean install
```

### 4. Run the Application

Using Maven Wrapper:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

Or using Maven directly:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## ⚙️ Configuration

### Default Credentials

The application uses Spring Security with the following default credentials:

- **Username:** `user`
- **Password:** `12345`

### Database Configuration

The application uses H2 database with the following settings:

- **Database URL:** `jdbc:h2:file:./data/employeedb`
- **Username:** `sa`
- **Password:** `password`
- **H2 Console:** Available at `http://localhost:8080/h2-console`

### Application Properties

Key configuration in `application.properties`:

```properties
spring.application.name=employees
springdoc.swagger-ui.path=/docs
spring.datasource.url=jdbc:h2:file:./data/employeedb
spring.jpa.hibernate.ddl-auto=update
```

## 📡 API Endpoints

### Base URL

```
http://localhost:8080/api/employees
```

### Endpoints

| Method   | Endpoint                     | Description         | Required Role  |
| -------- | ---------------------------- | ------------------- | -------------- |
| `GET`    | `/api/employees`             | Get all employees   | EMPLOYEE       |
| `GET`    | `/api/employees/{id}`        | Get employee by ID  | EMPLOYEE       |
| `POST`   | `/api/employees/new`         | Create new employee | MANAGER, ADMIN |
| `PUT`    | `/api/employees/update/{id}` | Update employee     | MANAGER, ADMIN |
| `DELETE` | `/api/employees/delete/{id}` | Delete employee     | ADMIN          |

### Request/Response Examples

#### Create Employee

```http
POST /api/employees/new
Content-Type: application/json
Authorization: Basic <base64-encoded-credentials>

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

#### Update Employee

```http
PUT /api/employees/update/1
Content-Type: application/json
Authorization: Basic <base64-encoded-credentials>

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane.smith@example.com"
}
```

#### Get All Employees

```http
GET /api/employees
Authorization: Basic <base64-encoded-credentials>
```

#### Get Employee by ID

```http
GET /api/employees/1
Authorization: Basic <base64-encoded-credentials>
```

#### Delete Employee

```http
DELETE /api/employees/delete/1
Authorization: Basic <base64-encoded-credentials>
```

## 🔐 Security

### Role-Based Access Control

The application implements role-based security with three levels:

- **EMPLOYEE** - Can view employee records (GET operations)
- **MANAGER** - Can view and modify employee records (GET, POST, PUT operations)
- **ADMIN** - Full access including deletion (GET, POST, PUT, DELETE operations)

### Authentication

The API uses **HTTP Basic Authentication**. Include credentials in the `Authorization` header:

```
Authorization: Basic <base64(username:password)>
```

### Public Endpoints

The following endpoints are publicly accessible (no authentication required):

- `/h2-console/**` - H2 Database Console
- `/swagger-ui/**` - Swagger UI
- `/v3/api-docs/**` - OpenAPI documentation
- `/docs` - Swagger UI (alternative path)

## 📚 API Documentation

### Swagger UI

Once the application is running, access the interactive API documentation at:

```
http://localhost:8080/docs
```

or

```
http://localhost:8080/swagger-ui/index.html
```

The Swagger UI provides:

- Complete API endpoint documentation
- Interactive testing interface
- Request/response schemas
- Authentication support

## 🗄️ Database Access

### H2 Console

Access the H2 Database Console at:

```
http://localhost:8080/h2-console
```

**Connection Settings:**

- **JDBC URL:** `jdbc:h2:file:./data/employeedb`
- **Username:** `sa`
- **Password:** `password`

### Database Schema

The `employee` table structure:

| Column       | Type    | Description                  |
| ------------ | ------- | ---------------------------- |
| `id`         | BIGINT  | Primary key (auto-generated) |
| `first_name` | VARCHAR | Employee's first name        |
| `last_name`  | VARCHAR | Employee's last name         |
| `email`      | VARCHAR | Employee's email address     |

## 📁 Project Structure

```
employees/
├── src/
│   ├── main/
│   │   ├── java/com/example/employees/
│   │   │   ├── controller/          # REST controllers
│   │   │   │   ├── EmployeeController.java
│   │   │   │   └── HomeController.java
│   │   │   ├── entity/              # JPA entities
│   │   │   │   └── Employee.java
│   │   │   ├── dao/                 # Data access layer
│   │   │   │   └── EmployeeRepository.java
│   │   │   ├── service/             # Business logic
│   │   │   │   ├── EmployeeService.java
│   │   │   │   └── EmployeeServiceImpl.java
│   │   │   ├── request/             # DTOs
│   │   │   │   └── EmployeeRequest.java
│   │   │   ├── security/            # Security configuration
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── SwaggerConfig.java
│   │   │   └── EmployeesApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                        # Test files
└── pom.xml                          # Maven configuration
```

## 🧪 Testing

Run the test suite:

```bash
mvn test
```

Or using Maven Wrapper:

```bash
.\mvnw.cmd test
```

## 📝 Usage Examples

### Using cURL

#### Get All Employees

```bash
curl -u user:12345 http://localhost:8080/api/employees
```

#### Create Employee

```bash
curl -X POST -u user:12345 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john.doe@example.com"}' \
  http://localhost:8080/api/employees/new
```

#### Get Employee by ID

```bash
curl -u user:12345 http://localhost:8080/api/employees/1
```

#### Update Employee

```bash
curl -X PUT -u user:12345 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","lastName":"Smith","email":"jane.smith@example.com"}' \
  http://localhost:8080/api/employees/update/1
```

#### Delete Employee

```bash
curl -X DELETE -u user:12345 http://localhost:8080/api/employees/delete/1
```

## 🐛 Troubleshooting

### Port Already in Use

If port 8080 is already in use, change it in `application.properties`:

```properties
server.port=8081
```

### Database Connection Issues

Ensure the `data` directory exists in the project root. The H2 database file will be created automatically on first run.

### Authentication Errors

Verify you're using the correct credentials:

- Username: `user`
- Password: `12345`

Ensure the `Authorization` header is properly formatted for HTTP Basic Auth.

## 📄 License

This project is open source and available under the MIT License.

## 👤 Author

Created with ❤️ using Spring Boot

---

**Happy Coding! 🚀**
