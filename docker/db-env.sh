#!/bin/bash

# Kiểm tra Docker đã cài đặt chưa
if ! command -v docker &> /dev/null; then
    echo "Docker chưa được cài đặt. Vui lòng cài đặt Docker trước."
    exit 1
fi

# Kiểm tra Docker đang chạy chưa
if ! docker info &> /dev/null; then
    echo "Docker daemon chưa chạy. Vui lòng khởi động Docker."
    exit 1
fi

# Hàm kiểm tra và khởi động MongoDB
start_mongodb() {
    if ! docker ps | grep -q mongodb; then
        echo "Đang khởi động MongoDB..."
        docker run -d --name mongodb -p 27017:27017 mongo:latest
        echo "✅ MongoDB đã được khởi động"
    else
        echo "✅ MongoDB đã chạy"
    fi
}

# Hàm kiểm tra và khởi động PostgreSQL
start_postgres() {
    if ! docker ps | grep -q postgres; then
        echo "Đang khởi động PostgreSQL..."
        docker run -d --name postgres -p 5432:5432 \
            -e POSTGRES_PASSWORD=postgres \
            -e POSTGRES_USER=postgres \
            -e POSTGRES_DB=finish_app \
            postgres:13
        echo "✅ PostgreSQL đã được khởi động"
    else
        echo "✅ PostgreSQL đã chạy"
    fi
}

# Hàm dừng và xóa container
stop_containers() {
    echo "Đang dừng các container..."
    docker stop mongodb postgres 2>/dev/null || true
    docker rm mongodb postgres 2>/dev/null || true
    echo "✅ Đã dừng và xóa các container"
}

# Xử lý lệnh
case "$1" in
    "start")
        start_mongodb
        start_postgres
        ;;
    "stop")
        stop_containers
        ;;
    "restart")
        stop_containers
        start_mongodb
        start_postgres
        ;;
    *)
        echo "Sử dụng: $0 {start|stop|restart}"
        echo "  start   - Khởi động các container database"
        echo "  stop    - Dừng và xóa các container"
        echo "  restart - Khởi động lại các container"
        exit 1
        ;;
esac

echo ""
echo "Thông tin kết nối:"
echo "MongoDB:"
echo "  - Port: 27017"
echo "  - URL: mongodb://localhost:27017"
echo ""
echo "PostgreSQL:"
echo "  - Port: 5432"
echo "  - Username: postgres"
echo "  - Password: postgres"
echo "  - Database: finish_app" 