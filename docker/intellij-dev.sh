#!/bin/bash

# Build image nếu chưa có
if [ "$(docker images -q spring-dev-env:1.0 2> /dev/null)" == "" ]; then
    echo "Building development image..."
    docker build -t spring-dev-env:1.0 -f docker/Dockerfile .
fi

# Kiểm tra container đã chạy chưa
if [ "$(docker ps -q -f name=spring-dev)" ]; then
    echo "Development container is already running"
    echo "SSH connection details:"
    echo "Host: localhost"
    echo "Port: 2222"
    echo "Username: developer"
    echo "Password: developer"
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

echo "Container started. You can now configure IntelliJ IDEA:"
echo "1. Open IntelliJ IDEA"
echo "2. Go to File > Project Structure"
echo "3. Under Project Settings > Project:"
echo "   - Set Project SDK to Remote JDK"
echo "   - Click '...' and add new Remote JDK"
echo "   - Choose SSH Credentials"
echo "   - Host: localhost"
echo "   - Port: 2222"
echo "   - Username: developer"
echo "   - Password: developer"
echo "   - JDK path: /usr/lib/jvm/java-11-openjdk-amd64"
echo ""
echo "4. Under Project Settings > Modules:"
echo "   - Set Module SDK to the Remote JDK you just created"
echo ""
echo "5. Go to File > Settings > Build, Execution, Deployment > Maven"
echo "   - Set Maven home directory to: /usr/share/maven"
echo ""
echo "To stop the container, run: docker stop spring-dev" 