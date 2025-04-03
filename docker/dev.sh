#!/bin/bash

# Build image nếu chưa có
if [ "$(docker images -q spring-dev-env:1.0 2> /dev/null)" == "" ]; then
    echo "Building development image..."
    docker build -t spring-dev-env:1.0 -f docker/Dockerfile .
fi

# Kiểm tra container đã chạy chưa
if [ "$(docker ps -q -f name=spring-dev)" ]; then
    echo "Development container is already running"
    echo "To connect: ssh developer@localhost -p 2222"
    exit 0
fi

# Chạy container
echo "Starting development container..."
docker run -d \
    --name spring-dev \
    -v $(pwd):/app \
    -v maven-repo:/home/developer/.m2 \
    -p 2222:22 \
    -p 8080:8080 \
    -p 5005:5005 \
    spring-dev-env:1.0

echo "Container started. You can now:"
echo "1. Connect via SSH: ssh developer@localhost -p 2222"
echo "2. Build project: mvn clean install"
echo "3. Run application: mvn spring-boot:run"
echo "4. Run with debug: mvn spring-boot:run -Dspring-boot.run.jvmArguments=\"-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005\""
echo ""
echo "To stop the container, run: docker stop spring-dev" 