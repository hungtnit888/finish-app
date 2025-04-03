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

### SSH Connection
- Host: localhost
- Port: 2222
- Username: developer
- Password: developer

## Lợi ích
- Môi trường development giống nhau trên mọi máy
- Không cần cài đặt JDK, Maven trên máy local
- Dễ dàng chia sẻ với team
- Có thể sử dụng với mọi IDE
- Dễ dàng backup và restore môi trường