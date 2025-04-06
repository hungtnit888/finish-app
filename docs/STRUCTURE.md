# Project Structure Documentation

## Overview
This document describes the structure and organization of the Finish App project, a Spring Boot application with comprehensive DevOps setup.

## Directory Structure

```
finish-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/base/
│   │   │       ├── config/         # Configuration classes
│   │   │       │   ├── RedisConfig.java
│   │   │       │   ├── SecurityConfig.java
│   │   │       │   └── WebConfig.java
│   │   │       ├── controller/     # REST controllers
│   │   │       ├── model/          # Entity and DTO classes
│   │   │       ├── repository/     # Data access layer
│   │   │       ├── service/        # Business logic
│   │   │       ├── security/       # Security configuration
│   │   │       └── util/           # Utility classes
│   │   └── resources/
│   │       ├── db/                # Database migrations
│   │       │   └── changelog/     # Liquibase changelogs
│   │       ├── application.yml    # Main configuration
│   │       └── application-local.yml # Local environment config
│   └── test/                      # Test classes
├── docker/
│   ├── app/                       # Application Dockerfile
│   ├── config/                    # Configuration files
│   │   ├── prometheus/           # Prometheus config
│   │   └── grafana/              # Grafana dashboards
│   └── scripts/                   # Utility scripts
│       ├── backup.sh             # Backup script
│       ├── recovery.sh           # Recovery script
│       ├── redis-backup.sh       # Redis backup
│       ├── redis-restore.sh      # Redis restore
│       └── redis-monitor.sh      # Redis monitoring
├── docs/                          # Documentation
└── pom.xml                        # Maven configuration
```

## Key Components

### 1. Source Code (`src/`)
- **Java Source (`src/main/java/`)**
  - Configuration classes for Redis, Security, and Web
  - REST controllers with OpenAPI documentation
  - Entity and DTO classes
  - Repository interfaces for data access
  - Service layer for business logic
  - Security configuration and utilities

- **Resources (`src/main/resources/`)**
  - Database migrations using Liquibase
  - Application properties for different environments
  - Logging configuration

### 2. Docker Configuration (`docker/`)
- **Application (`docker/app/`)**
  - Dockerfile for building the application image
  - Maven settings for Docker builds

- **Configuration (`docker/config/`)**
  - Prometheus configuration for metrics collection
  - Grafana dashboards for visualization
  - Redis configuration

- **Scripts (`docker/scripts/`)**
  - Backup and recovery scripts for all services
  - Redis-specific management scripts
  - Monitoring and maintenance utilities

### 3. Documentation (`docs/`)
- Project structure documentation
- Getting started guide
- API documentation
- Deployment guides

## Technology Stack

### Core Technologies
- Spring Boot 2.7.18
- Java 11
- Maven 3.6+

### Databases
- PostgreSQL 15 (Relational Database)
- MongoDB 6 (NoSQL Database)
- Redis 7 (Caching)

### Monitoring & Logging
- Prometheus for metrics collection
- Grafana for visualization
- Sentry for error tracking
- Redis monitoring scripts

### DevOps Tools
- Docker for containerization
- Docker Compose for orchestration
- GitHub Actions for CI/CD
- Liquibase for database migrations

## Conventions

### Package Structure
- Follows standard Spring Boot package structure
- Clear separation of concerns
- Consistent naming conventions

### Naming Conventions
- Classes: PascalCase
- Methods: camelCase
- Variables: camelCase
- Constants: UPPER_SNAKE_CASE
- Packages: lowercase

### Code Style
- Follows Google Java Style Guide
- Uses Lombok for reducing boilerplate code
- Implements proper logging
- Includes comprehensive documentation

## Security

### Authentication & Authorization
- JWT-based authentication
- Role-based access control
- Secure password handling
- CSRF protection

### Data Protection
- Encrypted database connections
- Secure Redis configuration
- Environment-based secrets
- Regular security updates

## Monitoring

### Application Monitoring
- Prometheus metrics
- Grafana dashboards
- Health checks
- Performance monitoring

### Database Monitoring
- PostgreSQL monitoring
- MongoDB monitoring
- Redis monitoring
- Connection pool metrics

### Logging
- Structured logging
- Log aggregation
- Error tracking
- Audit logging

## Backup & Recovery

### Automated Backups
- Database backups
- Redis data backups
- Configuration backups
- Scheduled backup jobs

### Recovery Procedures
- Database recovery
- Redis data recovery
- Configuration recovery
- Disaster recovery plans

## Core Application Components

### Base Entities
- `BaseEntity.java`: Abstract base class for all entities
  - Common fields (id, createdDate, lastModifiedDate)
  - Audit fields (createdBy, lastModifiedBy)
  - Version control for optimistic locking

### DTOs (Data Transfer Objects)
- `BaseDTO.java`: Base class for all DTOs
- `UserDTO.java`: User data transfer object
- `RoleDTO.java`: Role data transfer object
- `PermissionDTO.java`: Permission data transfer object
- `ErrorDTO.java`: Error response object
- `ValidationErrorDTO.java`: Validation error details

### Repository Interfaces
- `BaseRepository.java`: Generic repository interface
- `UserRepository.java`: User data access
- `RoleRepository.java`: Role data access
- `PermissionRepository.java`: Permission data access
- Custom query methods with @Query annotations
- Native SQL queries for complex operations

### Service Layer
- `BaseService.java`: Generic service interface
- `UserService.java`: User business logic
- `RoleService.java`: Role management
- `PermissionService.java`: Permission handling
- `CacheService.java`: Redis caching operations
- Transaction management with @Transactional
- Business logic validation
- Exception handling

### Controller Layer
- `BaseController.java`: Generic controller with common endpoints
- `UserController.java`: User management endpoints
- `RoleController.java`: Role management endpoints
- `PermissionController.java`: Permission management endpoints
- `CacheController.java`: Cache management endpoints
- Request/Response validation
- Error handling
- API documentation with Swagger annotations

### Exception Handling
- `GlobalExceptionHandler.java`: Centralized exception handling
- Custom exceptions:
  - `ResourceNotFoundException.java`
  - `ValidationException.java`
  - `SecurityException.java`
  - `BusinessException.java`
- Exception response formatting
- Error logging and monitoring

## Security Configuration

### JWT Configuration
- `JwtTokenProvider.java`: JWT token generation and validation
- `JwtAuthenticationFilter.java`: JWT token processing
- `JwtConfig.java`: JWT configuration properties
- Token expiration handling
- Token refresh mechanism

### Security Filters
- `AuthenticationFilter.java`: Authentication processing
- `AuthorizationFilter.java`: Authorization checks
- `CorsFilter.java`: Cross-origin resource sharing
- `RequestLoggingFilter.java`: Request logging
- Filter chain configuration

### Role-based Access Control
- `SecurityConfig.java`: Main security configuration
- `Role.java`: Role enumeration
- `Permission.java`: Permission enumeration
- `SecurityUtils.java`: Security utility methods
- Method-level security with @PreAuthorize
- URL-based security with antMatchers

## API Documentation

### OpenAPI/Swagger Configuration
- `OpenApiConfig.java`: Swagger configuration
- API documentation settings
- Security scheme configuration
- Response documentation
- Example values

### API Documentation
- Controller documentation
- Model documentation
- Operation documentation
- Parameter documentation
- Response documentation
- Security requirements

## Testing

### Unit Tests
- `BaseTest.java`: Base test class
- Service layer tests
- Repository layer tests
- Utility class tests
- Mock configurations
- Test data builders

### Integration Tests
- `BaseIntegrationTest.java`: Base integration test
- Controller integration tests
- Security integration tests
- Database integration tests
- Cache integration tests
- Test containers configuration

### Test Configurations
- `TestConfig.java`: Test configuration
- Mock beans configuration
- Test database configuration
- Test security configuration
- Test cache configuration
- Test properties 