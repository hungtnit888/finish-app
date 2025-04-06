# Language / Ngôn ngữ

- [English](#english)
- [Tiếng Việt](#tiếng-việt)

---

<a name="english"></a>
# Spring Boot Application

A modern enterprise-grade Spring Boot application with robust architecture and best practices.

## Tech Stack

- **Backend**: Spring Boot 3.2.3
- **Databases**: 
  - PostgreSQL 15
  - MongoDB 6
  - Redis 7
- **Security**: Spring Security, JWT
- **API Documentation**: OpenAPI 3.0
- **Monitoring**: Prometheus, Grafana
- **Containerization**: Docker
- **CI/CD**: GitHub Actions

## Prerequisites

- Java 17
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 15
- MongoDB 6
- Redis 7

## Quick Start

1. Clone repository:
```bash
git clone https://github.com/yourusername/finish-app.git
cd finish-app
```

2. Build application:
```bash
mvn clean install
```

3. Run with Docker Compose:
```bash
docker-compose -f docker/docker-compose.yml up -d
```

4. Access applications:
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Grafana: http://localhost:3000
- Prometheus: http://localhost:9090

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── com.example.base/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   └── Application.java
│   │   └── com.example.demo/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       ├── application.yml
│       ├── application-local.yml
│       └── application-test.yml
├── test/
│   └── java/
│       └── com.example.base/
└── docker/
    ├── scripts/
    │   ├── backup.sh
    │   ├── recovery.sh
    │   ├── redis-backup.sh
    │   ├── redis-restore.sh
    │   └── redis-monitor.sh
    └── docker-compose.yml
```

## Features

1. **Security**
   - JWT Authentication
   - Role-based Authorization
   - Password Encryption
   - CSRF Protection
   - Rate Limiting

2. **API Documentation**
   - OpenAPI 3.0
   - Swagger UI
   - Detailed API Descriptions

3. **Monitoring**
   - Prometheus Metrics
   - Grafana Dashboards
   - Health Checks
   - Performance Monitoring

4. **Database**
   - PostgreSQL for Relational Data
   - MongoDB for Document Storage
   - Redis for Caching
   - Database Migrations

5. **Testing**
   - Unit Tests
   - Integration Tests
   - Test Containers
   - Mock Testing

6. **DevOps**
   - Docker Containerization
   - CI/CD Pipeline
   - Backup & Recovery
   - Monitoring Scripts

## Development

1. **Local Development**
```bash
mvn spring-boot:run -Dspring.profiles.active=local
```

2. **Run Tests**
```bash
mvn test
```

3. **Database Migrations**
```bash
mvn liquibase:update
```

4. **Code Quality**
```bash
mvn sonar:sonar
```

## Monitoring & Maintenance

1. **Redis Management**
```bash
# Backup
./docker/scripts/redis-backup.sh

# Restore
./docker/scripts/redis-restore.sh redis_backup_20240315_120000.rdb.gz

# Monitor
nohup ./docker/scripts/redis-monitor.sh &
```

2. **Database Backup**
```bash
./docker/scripts/backup.sh
```

3. **System Recovery**
```bash
./docker/scripts/recovery.sh
```

## Contributing

1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Create Pull Request

## License

This project is licensed under the MIT License.

---

<a name="tiếng-việt"></a>
# Ứng dụng Spring Boot

Một ứng dụng Spring Boot doanh nghiệp hiện đại với kiến trúc và các phương pháp thực hành tốt nhất.

## Công nghệ sử dụng

- **Backend**: Spring Boot 3.2.3
- **Cơ sở dữ liệu**: 
  - PostgreSQL 15
  - MongoDB 6
  - Redis 7
- **Bảo mật**: Spring Security, JWT
- **Tài liệu API**: OpenAPI 3.0
- **Giám sát**: Prometheus, Grafana
- **Container hóa**: Docker
- **CI/CD**: GitHub Actions

## Yêu cầu hệ thống

- Java 17
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 15
- MongoDB 6
- Redis 7

## Hướng dẫn nhanh

1. Clone repository:
```bash
git clone https://github.com/yourusername/finish-app.git
cd finish-app
```

2. Build ứng dụng:
```bash
mvn clean install
```

3. Chạy với Docker Compose:
```bash
docker-compose -f docker/docker-compose.yml up -d
```

4. Truy cập ứng dụng:
- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Grafana: http://localhost:3000
- Prometheus: http://localhost:9090

## Cấu trúc dự án

```
src/
├── main/
│   ├── java/
│   │   ├── com.example.base/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   └── Application.java
│   │   └── com.example.demo/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       ├── application.yml
│       ├── application-local.yml
│       └── application-test.yml
├── test/
│   └── java/
│       └── com.example.base/
└── docker/
    ├── scripts/
    │   ├── backup.sh
    │   ├── recovery.sh
    │   ├── redis-backup.sh
    │   ├── redis-restore.sh
    │   └── redis-monitor.sh
    └── docker-compose.yml
```

## Tính năng

1. **Bảo mật**
   - Xác thực JWT
   - Phân quyền theo vai trò
   - Mã hóa mật khẩu
   - Bảo vệ CSRF
   - Giới hạn tốc độ

2. **Tài liệu API**
   - OpenAPI 3.0
   - Swagger UI
   - Mô tả chi tiết API

3. **Giám sát**
   - Metrics Prometheus
   - Dashboard Grafana
   - Kiểm tra sức khỏe
   - Giám sát hiệu suất

4. **Cơ sở dữ liệu**
   - PostgreSQL cho dữ liệu quan hệ
   - MongoDB cho lưu trữ tài liệu
   - Redis cho bộ nhớ đệm
   - Di chuyển cơ sở dữ liệu

5. **Kiểm thử**
   - Kiểm thử đơn vị
   - Kiểm thử tích hợp
   - Test Containers
   - Kiểm thử giả lập

6. **DevOps**
   - Container hóa với Docker
   - Pipeline CI/CD
   - Sao lưu & Khôi phục
   - Script giám sát

## Phát triển

1. **Phát triển cục bộ**
```bash
mvn spring-boot:run -Dspring.profiles.active=local
```

2. **Chạy kiểm thử**
```bash
mvn test
```

3. **Di chuyển cơ sở dữ liệu**
```bash
mvn liquibase:update
```

4. **Chất lượng mã nguồn**
```bash
mvn sonar:sonar
```

## Giám sát & Bảo trì

1. **Quản lý Redis**
```bash
# Sao lưu
./docker/scripts/redis-backup.sh

# Khôi phục
./docker/scripts/redis-restore.sh redis_backup_20240315_120000.rdb.gz

# Giám sát
nohup ./docker/scripts/redis-monitor.sh &
```

2. **Sao lưu cơ sở dữ liệu**
```bash
./docker/scripts/backup.sh
```

3. **Khôi phục hệ thống**
```bash
./docker/scripts/recovery.sh
```

## Đóng góp

1. Fork repository
2. Tạo nhánh tính năng
3. Commit thay đổi
4. Push lên nhánh
5. Tạo Pull Request

## Giấy phép

Dự án này được cấp phép theo Giấy phép MIT.