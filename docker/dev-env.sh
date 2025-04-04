#!/bin/bash

# Kiểm tra Docker đã cài đặt chưa
if ! command -v docker &> /dev/null; then
    echo "Docker chưa được cài đặt. Vui lòng cài đặt Docker trước."
    exit 1
fi

# Cấp quyền thực thi cho các script
echo "Cấp quyền thực thi cho các script..."
chmod +x $(dirname "$0")/db-env.sh
chmod +x $(dirname "$0")/check-env.sh

echo "Starting development environment..."

# Build image từ thư mục gốc
echo "Building development image..."
cd ..
docker build -t spring-dev-env:1.0 -f docker/Dockerfile .

# Kiểm tra container đã tồn tại chưa
if docker ps -a | grep -q spring-dev; then
    echo "Container spring-dev đã tồn tại. Đang xóa..."
    docker stop spring-dev
    docker rm spring-dev
fi

# Tạo và chạy container
echo "Creating and starting development container..."
docker run -d \
    --name spring-dev \
    -p 8080:8080 \
    -p 2222:22 \
    -v $(pwd):/app \
    -v ~/.m2:/home/developer/.m2 \
    spring-dev-env:1.0

# Kiểm tra container đã chạy chưa
if docker ps | grep -q spring-dev; then
    echo "Development environment is ready!"
    echo "Container name: spring-dev"
    echo "SSH port: 2222"
    echo "Application port: 8080"
    echo ""
    echo "Để cập nhật thư viện trong Docker:"
    echo "1. Sửa file pom.xml trên máy host"
    echo "2. Chạy lệnh: docker exec -it spring-dev mvn dependency:resolve"
    echo "3. Nếu cần copy .m2 từ container ra host:"
    echo "   docker cp spring-dev:/home/developer/.m2 ~/.m2"
    echo ""
    echo "Để quản lý các container database:"
    echo "1. Khởi động: ./docker/db-env.sh start"
    echo "2. Dừng: ./docker/db-env.sh stop"
    echo "3. Khởi động lại: ./docker/db-env.sh restart"
else
    echo "Failed to start development environment"
    exit 1
fi 