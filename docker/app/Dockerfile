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
    && rm -rf /var/lib/apt/lists/*

# Create developer user
RUN useradd -m -s /bin/bash developer && \
    echo "developer:developer" | chpasswd && \
    mkdir -p /home/developer/.ssh && \
    chown -R developer:developer /home/developer

# Configure SSH
RUN mkdir /var/run/sshd && \
    echo "PermitRootLogin no" >> /etc/ssh/sshd_config && \
    echo "PasswordAuthentication yes" >> /etc/ssh/sshd_config

# Copy Maven settings
COPY docker/app/settings.xml /home/developer/.m2/settings.xml
RUN chown -R developer:developer /home/developer/.m2

# Set environment variables
ENV JAVA_HOME=/usr/local/openjdk-11
ENV PATH=$PATH:$JAVA_HOME/bin

# Create application directory
WORKDIR /app
RUN chown -R developer:developer /app

# Copy built application
COPY --from=builder /build/target/*.jar /app/app.jar

# Create log directory
RUN mkdir -p /app/logs && \
    chown -R developer:developer /app/logs

# Expose ports
EXPOSE 22 8080

# Switch to developer user
USER developer

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Start SSH server and application
CMD ["/usr/sbin/sshd", "-D"] 