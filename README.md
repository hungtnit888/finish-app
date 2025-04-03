# Spring Boot Development Environment

## Mục đích
- Tạo môi trường development chuẩn, giống nhau trên mọi máy
- Dễ dàng chia sẻ và sử dụng với mọi IDE
- Không cần cài đặt JDK, Maven trên máy local

## Cài đặt
1. Cài đặt Docker
2. Clone repository
3. Chạy môi trường development:
```bash
chmod +x docker/dev-env.sh
./docker/dev-env.sh
```

## Cấu hình IDE
Sau khi chạy môi trường, cấu hình IDE của bạn với thông tin sau:

### JDK
- Path: `/usr/lib/jvm/java-11-openjdk-amd64`
- Version: Java 11

### Maven
- Path: `/usr/share/maven`
- Version: 3.8.6

## Lợi ích
- Môi trường development giống nhau trên mọi máy
- Không cần cài đặt JDK, Maven trên máy local
- Dễ dàng chia sẻ với team
- Có thể sử dụng với mọi IDE
- Dễ dàng backup và restore môi trường

# Finish App

## Giới thiệu
Finish App là một ứng dụng quản lý công việc được xây dựng bằng Spring Boot, giúp người dùng theo dõi và quản lý các nhiệm vụ một cách hiệu quả.

## Cấu trúc dự án
Để hiểu rõ về cấu trúc dự án, vui lòng xem [docs/STRUCTURE.md](docs/STRUCTURE.md)

## Bắt đầu
Để bắt đầu phát triển với dự án này, vui lòng tham khảo [docs/GETTING_STARTED.md](docs/GETTING_STARTED.md)

## Tính năng chính
- Quản lý công việc
- Phân loại theo dự án
- Theo dõi tiến độ
- Thông báo và nhắc nhở

## Công nghệ sử dụng
- Spring Boot
- PostgreSQL
- MongoDB
- Docker
- Maven

## Yêu cầu hệ thống
- JDK 11
- Docker
- Maven 3.8+
- Git

## Cài đặt
1. Clone repository:
```bash
git clone https://github.com/yourusername/finish-app.git
cd finish-app
```

2. Khởi động môi trường phát triển:
```bash
chmod +x docker/dev-env.sh
./docker/dev-env.sh
```

3. Kiểm tra môi trường:
```bash
chmod +x docker/check-env.sh
./docker/check-env.sh
```

## Cấu hình IDE
Vui lòng tham khảo [docs/GETTING_STARTED.md](docs/GETTING_STARTED.md) để biết hướng dẫn chi tiết về cấu hình IDE.

## Triển khai
Để triển khai ứng dụng, vui lòng tham khảo [docs/STRUCTURE.md](docs/STRUCTURE.md) để biết thêm chi tiết về cấu trúc triển khai.

## Đóng góp
1. Fork repository
2. Tạo branch mới (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add some amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Tạo Pull Request

## Giấy phép
Dự án này được cấp phép theo giấy phép MIT - xem file [LICENSE](LICENSE) để biết thêm chi tiết.

## Liên hệ
- Email: your.email@example.com
- Website: https://yourwebsite.com