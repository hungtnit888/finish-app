# Hướng dẫn bắt đầu

## Tổng quan
Tài liệu này hướng dẫn cách thiết lập môi trường phát triển cho dự án Finish App. Để biết thêm thông tin về dự án, vui lòng xem [../README.md](../README.md). Để hiểu rõ về cấu trúc dự án, vui lòng xem [STRUCTURE.md](STRUCTURE.md).

## Yêu cầu hệ thống
- JDK 11
- Docker
- Maven 3.8+
- Git
- IDE (IntelliJ IDEA hoặc VS Code)

## Cài đặt môi trường

1. Cài đặt Docker
```bash
# Ubuntu
sudo apt-get update
sudo apt-get install docker.io docker-compose

# Thêm user vào group docker
sudo usermod -aG docker $USER
newgrp docker
```

2. Kiểm tra cài đặt
```bash
docker --version
docker-compose --version
```

## Khởi động môi trường development

1. Clone repository
```bash
git clone <repository_url>
cd finish-app
```

2. Khởi động môi trường
```bash
cd docker
docker-compose up -d
```

3. Kiểm tra các service
```bash
docker-compose ps
```

## Cấu hình IDE

### IntelliJ IDEA

1. Mở project
- File -> Open -> Chọn thư mục project

2. Cấu hình Java SDK
- File -> Project Structure -> Project
- Project SDK: chọn Java 11
- Project language level: 11

3. Cấu hình Maven
- Settings -> Build, Execution, Deployment -> Build Tools -> Maven
- Maven home: `/home/developer/.m2`
- User settings file: `docker/app/settings.xml`

4. Cấu hình Remote Development
- Settings -> Build, Execution, Deployment -> Docker
- Thêm Docker connection: tcp://localhost:2375

### Visual Studio Code

1. Cài đặt extensions
- Java Extension Pack
- Spring Boot Extension Pack
- Docker
- Remote Development

2. Mở project
- File -> Open Folder -> Chọn thư mục project

3. Cấu hình Java
- Command Palette (Ctrl+Shift+P)
- Java: Configure Java Runtime
- Chọn Java 11

4. Cấu hình Remote Development
- Command Palette
- Remote-Containers: Open Folder in Container
- Chọn thư mục project

## Kiểm tra cài đặt

1. Kiểm tra ứng dụng
```bash
curl http://localhost:8080/actuator/health
```

2. Kiểm tra databases
```bash
# PostgreSQL
psql -h localhost -p 5432 -U postgres -d finish_app

# MongoDB
mongosh mongodb://localhost:27017
```

3. Kiểm tra monitoring
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000

## Phát triển

### 1. Truy cập container development
```bash
ssh developer@localhost -p 2222
# Password: developer
```

### 2. Build project
```bash
cd /app
mvn clean install
```

### 3. Chạy tests
```bash
mvn test
```

### 4. Restart application
```bash
docker-compose restart app
```

## Debugging

1. Remote debugging
- Port: 5005
- IDE configuration:
  - IntelliJ IDEA: Run -> Edit Configurations -> Remote JVM Debug
  - VS Code: launch.json -> Java Debug Configuration

2. Logs
```bash
# Application logs
docker-compose logs -f app

# Database logs
docker-compose logs -f postgres
docker-compose logs -f mongodb
```

## Tips

1. Hot reload
- Spring Boot DevTools đã được cấu hình
- Các thay đổi trong code sẽ tự động reload

2. Database migrations
- Liquibase tự động chạy khi khởi động
- Files migration trong src/main/resources/db/changelog

3. API Documentation
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI docs: http://localhost:8080/v3/api-docs

4. Monitoring
- Spring Boot Actuator: http://localhost:8080/actuator
- Prometheus metrics: http://localhost:8080/actuator/prometheus
- Grafana dashboards: http://localhost:3000

## Xử lý sự cố

### 1. Container không khởi động
- Kiểm tra logs: `docker logs spring-dev`
- Kiểm tra cấu hình: `docker inspect spring-dev`

### 2. Maven build failed
- Kiểm tra settings.xml
- Kiểm tra network connection
- Clear Maven cache: `mvn clean`

## Tài nguyên bổ sung
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Docker Documentation](https://docs.docker.com/)
- [Maven Documentation](https://maven.apache.org/guides/)

## Liên hệ hỗ trợ
Nếu gặp vấn đề, vui lòng:
1. Kiểm tra [../README.md](../README.md) và [STRUCTURE.md](STRUCTURE.md)
2. Tạo issue trên GitHub
3. Liên hệ qua email: your.email@example.com 

