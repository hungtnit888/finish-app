# Hướng dẫn bắt đầu với Spring Boot Development Environment

## Bước 1: Cài đặt Docker
1. Truy cập https://www.docker.com/products/docker-desktop
2. Tải và cài đặt Docker Desktop phù hợp với hệ điều hành của bạn
3. Khởi động Docker Desktop và đợi đến khi biểu tượng Docker chuyển sang màu xanh

## Bước 2: Lấy mã nguồn
1. Mở Terminal (Linux/Mac) hoặc Command Prompt (Windows)
2. Di chuyển đến thư mục bạn muốn lưu project
3. Chạy lệnh:
```bash
git clone [đường dẫn repository]
cd [tên thư mục project]
```

## Bước 3: Khởi động môi trường development
1. Mở Terminal trong thư mục project
2. Chạy lệnh:
```bash
chmod +x docker/dev-env.sh
./docker/dev-env.sh
```
3. Đợi đến khi thấy thông báo "Development environment is ready!"

## Bước 4: Cấu hình IDE
### Với IntelliJ IDEA
1. Mở IntelliJ IDEA
2. Mở project của bạn
3. Vào File > Project Structure
4. Trong Project Settings > Project:
   - Chọn "New" > "JDK"
   - Chọn "Remote JDK"
   - Điền thông tin:
     - Host: localhost
     - Port: 2222
     - Username: developer
     - Password: developer
     - JDK path: /usr/lib/jvm/java-11-openjdk-amd64
5. Vào File > Settings > Build, Execution, Deployment > Maven
   - Maven home directory: /usr/share/maven

### Với VS Code
1. Mở VS Code
2. Cài đặt extension "Remote - SSH"
3. Nhấn F1, gõ "Remote-SSH: Connect to Host"
4. Chọn "Add New SSH Host"
5. Nhập: developer@localhost:2222
6. Nhập password: developer

## Bước 5: Kiểm tra môi trường
1. Mở Terminal trong IDE
2. Chạy lệnh:
```bash
java -version
mvn -version
```
3. Nếu hiển thị đúng phiên bản Java 11 và Maven 3.8.6 là thành công

## Bước 6: Bắt đầu phát triển
1. Mở file pom.xml để xem các dependencies
2. Mở file src/main/java để xem mã nguồn
3. Bạn có thể:
   - Sửa code
   - Build project: mvn clean install
   - Chạy ứng dụng: mvn spring-boot:run

## Lưu ý quan trọng
- Luôn chạy môi trường development trước khi mở IDE
- Nếu gặp lỗi kết nối, kiểm tra Docker đã chạy chưa
- Để dừng môi trường: docker stop spring-dev
- Để xóa môi trường: docker rm spring-dev 