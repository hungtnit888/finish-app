# Language / Ngôn ngữ

- [English](#english)
- [Tiếng Việt](#tiếng-việt)

---

<a name="english"></a>
# Getting Started

## Prerequisites

- Java 17
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 15
- MongoDB 6
- Redis 7

## Installation

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

## Access Applications

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Grafana: http://localhost:3000
- Prometheus: http://localhost:9090

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

---

<a name="tiếng-việt"></a>
# Bắt đầu

## Yêu cầu hệ thống

- Java 17
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 15
- MongoDB 6
- Redis 7

## Cài đặt

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

## Truy cập ứng dụng

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Grafana: http://localhost:3000
- Prometheus: http://localhost:9090

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

## Tài nguyên bổ sung
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Docker Documentation](https://docs.docker.com/)
- [Maven Documentation](https://maven.apache.org/guides/)

## Liên hệ hỗ trợ
Nếu gặp vấn đề, vui lòng:
1. Kiểm tra [../README.md](../README.md) và [STRUCTURE.md](STRUCTURE.md)
2. Tạo issue trên GitHub
3. Liên hệ qua email: your.email@example.com 

