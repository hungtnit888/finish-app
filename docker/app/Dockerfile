# Build stage
FROM maven:3.8.6-openjdk-11-slim AS builder

# Copy source code
WORKDIR /build
COPY pom.xml .
COPY src ./src

# Build application
RUN mvn clean package -DskipTests

# Development stage
FROM openjdk:11-jdk-slim

# Install essential tools
RUN apt-get update && apt-get install -y \
    git \
    openssh-server \
    curl \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Create developer user
RUN useradd -m -s /bin/bash developer && \
    echo "developer:developer" | chpasswd && \
    mkdir -p /home/developer/.ssh && \
    chown -R developer:developer /home/developer

# Configure SSH
RUN mkdir /var/run/sshd && \
    echo "PermitRootLogin no" >> /etc/ssh/sshd_config && \
    echo "PasswordAuthentication yes" >> /etc/ssh/sshd_config && \
    ssh-keygen -A

# Copy Maven settings
COPY docker/app/settings.xml /home/developer/.m2/settings.xml
RUN chown -R developer:developer /home/developer/.m2

# Set environment variables
ENV JAVA_HOME=/usr/local/openjdk-11
ENV PATH=$PATH:$JAVA_HOME/bin

# Create application directory
WORKDIR /app
RUN chown -R developer:developer /app

# Create log directory
RUN mkdir -p /app/logs && \
    chown -R developer:developer /app/logs

# Copy built application from builder stage
COPY --from=builder /build/target/*.jar /app/app.jar
RUN chown developer:developer /app/app.jar

# Download Spring Boot plugin
RUN mvn dependency:get -Dartifact=org.springframework.boot:spring-boot-maven-plugin:2.7.18

# Create startup script
RUN echo '#!/bin/bash' > /start.sh && \
    echo '/usr/sbin/sshd -D &' >> /start.sh && \
    echo 'if [ "$MODE" = "development" ]; then' >> /start.sh && \
    echo '  cd /app && mvn spring-boot:run' >> /start.sh && \
    echo 'else' >> /start.sh && \
    echo '  cd /app && java -jar app.jar' >> /start.sh && \
    echo 'fi' >> /start.sh && \
    chmod +x /start.sh

# Expose ports
EXPOSE 22 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD sleep 10 && curl -f http://localhost:8080/api/actuator/health || exit 1

# Start both SSH server and application as root
USER root
CMD ["/bin/bash", "/start.sh"]