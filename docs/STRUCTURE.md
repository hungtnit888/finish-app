# Cấu trúc dự án

## Tổng quan
```
project/
├── docker/                  # Cấu hình môi trường phát triển
├── deployment/             # Cấu hình triển khai
├── src/                    # Mã nguồn
├── docs/                   # Tài liệu
└── README.md              # Hướng dẫn tổng quan
```

## Chi tiết từng thư mục

### 1. Thư mục `docker/`
Chứa các file cấu hình cho môi trường phát triển
```
docker/
├── Dockerfile             # Cấu hình container phát triển
├── dev-env.sh            # Script khởi động môi trường
├── check-env.sh          # Script kiểm tra môi trường
└── settings.xml          # Cấu hình Maven
```

#### Mô tả file:
- **Dockerfile**: Cấu hình container với:
  - JDK 11
  - Maven 3.8.6
  - Git
  - SSH server
  - Các công cụ phát triển khác

- **dev-env.sh**: Script để:
  - Build image
  - Tạo container
  - Cấu hình SSH
  - Mount volume

- **check-env.sh**: Script kiểm tra:
  - Docker installation
  - Container status
  - SSH connection

- **settings.xml**: Cấu hình Maven:
  - Repository mirrors
  - Proxy settings
  - Local repository

### 2. Thư mục `deployment/`
Chứa cấu hình triển khai và các service
```
deployment/
├── docker-compose.yml     # Cấu hình các service
├── nginx/                # Cấu hình Nginx
│   └── nginx.conf
└── scripts/              # Scripts triển khai
    ├── deploy.sh
    └── backup.sh
```

#### Mô tả file:
- **docker-compose.yml**: Cấu hình:
  - Spring Boot application
  - PostgreSQL database
  - MongoDB
  - Volumes
  - Networks

- **nginx/**: Cấu hình:
  - Reverse proxy
  - SSL/TLS
  - Load balancing

- **scripts/**: Các script:
  - deploy.sh: Triển khai ứng dụng
  - backup.sh: Backup dữ liệu

### 3. Thư mục `src/`
Chứa mã nguồn ứng dụng
```
src/
├── main/
│   ├── java/            # Mã nguồn Java
│   ├── resources/       # Tài nguyên
│   └── webapp/         # Web resources
└── test/               # Test cases
```

### 4. Thư mục `docs/`
Chứa tài liệu dự án
```
docs/
├── api/                # API documentation
├── architecture/       # Kiến trúc hệ thống
└── guides/            # Hướng dẫn
```

## Quy ước đặt tên
1. **Thư mục**: lowercase với dấu gạch ngang (kebab-case)
   - Ví dụ: `deployment-scripts`, `api-docs`

2. **File cấu hình**: lowercase với dấu chấm
   - Ví dụ: `application.yml`, `pom.xml`

3. **File script**: lowercase với dấu gạch ngang
   - Ví dụ: `deploy-app.sh`, `backup-db.sh`

## Quy tắc quản lý
1. **Version Control**:
   - Sử dụng Git
   - Branch naming: feature/, bugfix/, hotfix/
   - Commit message theo quy ước

2. **Dependencies**:
   - Quản lý bằng Maven
   - Cập nhật version trong pom.xml
   - Document trong README.md

3. **Documentation**:
   - Cập nhật khi thay đổi cấu trúc
   - Giữ README.md luôn mới nhất
   - Comment code rõ ràng

## Hướng dẫn sử dụng
1. **Development**:
   ```bash
   ./docker/dev-env.sh
   ./docker/check-env.sh
   ```

2. **Deployment**:
   ```bash
   cd deployment
   docker-compose up -d
   ```

3. **Backup**:
   ```bash
   ./deployment/scripts/backup.sh
   ``` 