# Language / Ngôn ngữ

- [English](#english)
- [Tiếng Việt](#tiếng-việt)

---

<a name="english"></a>
# Project Structure

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
    └── docker-compose.yml
```

## Directory Structure

### Base Package (com.example.base)
- **config**: Configuration classes
- **controller**: Base controller classes
- **dto**: Data Transfer Objects
- **exception**: Custom exceptions
- **model**: Base entity classes
- **repository**: Base repository interfaces
- **security**: Security related classes
- **service**: Base service classes

### Demo Package (com.example.demo)
- **controller**: Example controllers
- **dto**: Example DTOs
- **model**: Example entities
- **repository**: Example repositories
- **service**: Example services

### Resources
- **application.yml**: Main configuration
- **application-local.yml**: Local environment configuration
- **application-test.yml**: Test environment configuration

### Docker
- **scripts**: Utility scripts
- **docker-compose.yml**: Container configuration

---

<a name="tiếng-việt"></a>
# Cấu trúc dự án

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
    └── docker-compose.yml
```

## Cấu trúc thư mục

### Gói Base (com.example.base)
- **config**: Các lớp cấu hình
- **controller**: Các lớp controller cơ sở
- **dto**: Các đối tượng chuyển dữ liệu
- **exception**: Các ngoại lệ tùy chỉnh
- **model**: Các lớp entity cơ sở
- **repository**: Các interface repository cơ sở
- **security**: Các lớp liên quan đến bảo mật
- **service**: Các lớp service cơ sở

### Gói Demo (com.example.demo)
- **controller**: Các controller ví dụ
- **dto**: Các DTO ví dụ
- **model**: Các entity ví dụ
- **repository**: Các repository ví dụ
- **service**: Các service ví dụ

### Resources
- **application.yml**: Cấu hình chính
- **application-local.yml**: Cấu hình môi trường local
- **application-test.yml**: Cấu hình môi trường test

### Docker
- **scripts**: Các script tiện ích
- **docker-compose.yml**: Cấu hình container 