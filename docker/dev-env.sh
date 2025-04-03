#!/bin/bash

# Build image nếu chưa có
if [ "$(docker images -q spring-dev-env:1.0 2> /dev/null)" == "" ]; then
    echo "Building development environment..."
    docker build -t spring-dev-env:1.0 -f docker/Dockerfile .
fi

# Kiểm tra container đã chạy chưa
if [ "$(docker ps -q -f name=spring-dev)" ]; then
    echo "Development environment is already running"
    echo "Connection details:"
    echo "Host: localhost"
    echo "Port: 2222"
    echo "Username: developer"
    echo "Password: developer"
    exit 0
fi

# Chạy container
echo "Starting development environment..."
docker run -d \
    --name spring-dev \
    -v $(pwd):/app \
    -v maven-repo:/home/developer/.m2 \
    -p 2222:22 \
    spring-dev-env:1.0

echo "Development environment is ready!"
echo "You can now use any IDE with these settings:"
echo "1. JDK path: /usr/lib/jvm/java-11-openjdk-amd64"
echo "2. Maven path: /usr/share/maven"
echo "3. SSH connection:"
echo "   - Host: localhost"
echo "   - Port: 2222"
echo "   - Username: developer"
echo "   - Password: developer"
echo ""
echo "To stop the environment, run: docker stop spring-dev" 