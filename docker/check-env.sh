#!/bin/bash

echo "Kiểm tra môi trường development..."

# Kiểm tra Docker
if ! command -v docker &> /dev/null; then
    echo "❌ Docker chưa được cài đặt. Vui lòng cài đặt Docker trước."
    exit 1
fi
echo "✅ Docker đã được cài đặt"

# Kiểm tra Docker đang chạy
if ! docker info &> /dev/null; then
    echo "❌ Docker chưa chạy. Vui lòng khởi động Docker."
    exit 1
fi
echo "✅ Docker đang chạy"

# Kiểm tra container
if [ "$(docker ps -q -f name=spring-dev)" ]; then
    echo "✅ Môi trường development đang chạy"
    echo "Thông tin kết nối:"
    echo "Host: localhost"
    echo "Port: 2222"
    echo "Username: developer"
    echo "Password: developer"
else
    echo "❌ Môi trường development chưa chạy"
    echo "Vui lòng chạy: ./docker/dev-env.sh"
    exit 1
fi

# Kiểm tra kết nối SSH
if ! ssh -o StrictHostKeyChecking=no -p 2222 developer@localhost "java -version" &> /dev/null; then
    echo "❌ Không thể kết nối SSH. Vui lòng kiểm tra lại cấu hình."
    exit 1
fi
echo "✅ Có thể kết nối SSH"

echo ""
echo "Môi trường development đã sẵn sàng!"
echo "Bạn có thể bắt đầu phát triển với IDE yêu thích của mình."
echo "Xem hướng dẫn chi tiết trong file GETTING_STARTED.md" 