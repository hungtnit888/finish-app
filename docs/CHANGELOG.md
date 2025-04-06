# Language / Ngôn ngữ

- [English](#english)
- [Tiếng Việt](#tiếng-việt)

---

<a name="english"></a>
# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Initial project setup with Spring Boot 3.2.3
- PostgreSQL 15 integration
- MongoDB 6 integration
- Redis 7 integration
- JWT authentication
- OpenAPI documentation
- Docker configuration
- CI/CD pipeline
- Monitoring setup with Prometheus & Grafana

### Changed
- Updated Spring Boot to 3.2.3
- Upgraded PostgreSQL to 15
- Upgraded MongoDB to 6
- Upgraded Redis to 7

### Fixed
- Security vulnerabilities in dependencies
- Performance issues in database queries
- Memory leaks in Redis cache

### Security
- Implemented JWT authentication
- Added rate limiting
- Enhanced password hashing
- Added CSRF protection

## [1.0.0] - 2024-03-15

### Added
- Initial release
- Basic CRUD operations
- User authentication
- Role-based authorization
- API documentation
- Docker support

---

<a name="tiếng-việt"></a>
# Lịch sử thay đổi

Tất cả các thay đổi đáng chú ý của dự án sẽ được ghi lại trong file này.

Định dạng dựa trên [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
và dự án này tuân theo [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Chưa phát hành]

### Thêm mới
- Thiết lập dự án ban đầu với Spring Boot 3.2.3
- Tích hợp PostgreSQL 15
- Tích hợp MongoDB 6
- Tích hợp Redis 7
- Xác thực JWT
- Tài liệu OpenAPI
- Cấu hình Docker
- Pipeline CI/CD
- Thiết lập giám sát với Prometheus & Grafana

### Thay đổi
- Cập nhật Spring Boot lên 3.2.3
- Nâng cấp PostgreSQL lên 15
- Nâng cấp MongoDB lên 6
- Nâng cấp Redis lên 7

### Sửa lỗi
- Lỗ hổng bảo mật trong các phụ thuộc
- Vấn đề hiệu suất trong truy vấn cơ sở dữ liệu
- Rò rỉ bộ nhớ trong bộ nhớ đệm Redis

### Bảo mật
- Triển khai xác thực JWT
- Thêm giới hạn tốc độ
- Tăng cường mã hóa mật khẩu
- Thêm bảo vệ CSRF

## [1.0.0] - 2024-03-15

### Thêm mới
- Phát hành ban đầu
- Các thao tác CRUD cơ bản
- Xác thực người dùng
- Phân quyền dựa trên vai trò
- Tài liệu API
- Hỗ trợ Docker 