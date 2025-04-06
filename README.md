# Finish App - Enterprise Spring Boot Application

## Overview
Finish App is a robust enterprise-grade Spring Boot application that demonstrates best practices in building scalable, maintainable, and production-ready applications.

## Tech Stack
- **Core Framework**: Spring Boot 2.7.18
- **Security**: Spring Security with JWT
- **Databases**: 
  - PostgreSQL 15 (Relational Database)
  - MongoDB 6 (NoSQL Database)
  - Redis 7 (Caching)
- **API Documentation**: OpenAPI 3.0 (Swagger)
- **Monitoring & Logging**:
  - Prometheus
  - Grafana
  - Sentry
- **Containerization**: Docker
- **CI/CD**: GitHub Actions
- **Database Migration**: Liquibase
- **Testing**: JUnit 5, TestContainers

## Prerequisites
- Java 11 or later
- Maven 3.6 or later
- Docker and Docker Compose
- PostgreSQL 15
- MongoDB 6
- Redis 7

## Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/finish-app.git
cd finish-app
```

### 2. Build the Application
```bash
mvn clean install
```

### 3. Run with Docker Compose
```bash
cd docker
docker-compose up -d
```

The application will be available at:
- Main Application: http://localhost:8080
- API Documentation: http://localhost:8080/swagger-ui.html
- Grafana Dashboard: http://localhost:3000
- Prometheus: http://localhost:9090

## Project Structure
```
finish-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/base/
│   │   │       ├── config/         # Configuration classes
│   │   │       ├── controller/     # REST controllers
│   │   │       ├── model/          # Entity and DTO classes
│   │   │       ├── repository/     # Data access layer
│   │   │       ├── service/        # Business logic
│   │   │       ├── security/       # Security configuration
│   │   │       └── util/           # Utility classes
│   │   └── resources/
│   │       ├── db/                # Database migrations
│   │       └── application.yml     # Application configuration
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

## Features
- **Security**: JWT-based authentication and authorization
- **Caching**: Redis-based caching with configurable TTL
- **Monitoring**: 
  - Prometheus metrics
  - Grafana dashboards
  - Sentry error tracking
- **Database**: 
  - PostgreSQL for relational data
  - MongoDB for document storage
  - Redis for caching
- **CI/CD**: Automated build, test, and deployment
- **Backup & Recovery**: Automated backup and recovery scripts

## Development
1. **Local Development**
   ```bash
   mvn spring-boot:run -Dspring.profiles.active=local
   ```

2. **Running Tests**
   ```bash
   mvn test
   ```

3. **Database Migrations**
   ```bash
   mvn liquibase:update
   ```

## Monitoring & Maintenance
1. **Redis Management**
   - Backup: `./docker/scripts/redis-backup.sh`
   - Restore: `./docker/scripts/redis-restore.sh <backup_file>`
   - Monitor: `./docker/scripts/redis-monitor.sh`

2. **System Monitoring**
   - Access Grafana at http://localhost:3000
   - Default credentials: admin/admin

3. **Logs**
   - Application logs: `docker-compose logs -f app`
   - Redis logs: `docker-compose logs -f redis`

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License
This project is licensed under the MIT License - see the LICENSE file for details.