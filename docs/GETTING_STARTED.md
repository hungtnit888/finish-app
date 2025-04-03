# Hướng dẫn bắt đầu

## Tổng quan
Tài liệu này hướng dẫn cách thiết lập môi trường phát triển cho dự án Finish App. Để biết thêm thông tin về dự án, vui lòng xem [../README.md](../README.md). Để hiểu rõ về cấu trúc dự án, vui lòng xem [STRUCTURE.md](STRUCTURE.md).

## Yêu cầu hệ thống
- JDK 11
- Docker
- Maven 3.8+
- Git
- IDE (IntelliJ IDEA hoặc VS Code)

## Cài đặt môi trường phát triển

### 1. Cài đặt Docker
```bash
# Ubuntu/Debian
sudo apt-get update
sudo apt-get install docker.io docker-compose

# Kiểm tra cài đặt
docker --version
docker-compose --version
```

### 2. Clone repository
```bash
git clone https://github.com/yourusername/finish-app.git
cd finish-app
```

### 3. Khởi động môi trường
```bash
chmod +x docker/dev-env.sh
./docker/dev-env.sh
```

### 4. Kiểm tra môi trường
```bash
chmod +x docker/check-env.sh
./docker/check-env.sh
```

## Cấu hình IDE

### IntelliJ IDEA
1. Mở project trong IntelliJ IDEA
2. Cấu hình JDK 11:
   - File -> Project Structure -> Project
   - Chọn JDK 11 từ thư mục `/usr/lib/jvm/java-11-openjdk-amd64`
3. Cấu hình Maven:
   - File -> Settings -> Build, Execution, Deployment -> Build Tools -> Maven
   - Maven home directory: `/usr/share/maven`
   - User settings file: `docker/settings.xml`

### VS Code
1. Cài đặt extensions:
   - Java Extension Pack
   - Spring Boot Extension Pack
   - Docker
2. Mở project
3. Cấu hình JDK và Maven trong settings.json:
```json
{
  "java.home": "/usr/lib/jvm/java-11-openjdk-amd64",
  "maven.executable.path": "/usr/share/maven/bin/mvn",
  "maven.terminal.customEnv": [
    {
      "environmentVariable": "MAVEN_OPTS",
      "value": "-Dmaven.repo.local=/home/developer/.m2/repository"
    }
  ]
}
```

## Phát triển

### 1. Build project
```bash
mvn clean install
```

### 2. Chạy ứng dụng
```bash
mvn spring-boot:run
```

### 3. Kiểm tra
- Truy cập: http://localhost:8080
- API docs: http://localhost:8080/swagger-ui.html

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

