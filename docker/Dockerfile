FROM maven:3.8.6-openjdk-11-slim

# Cài đặt các công cụ cần thiết
RUN apt-get update && apt-get install -y \
    git \
    openssh-server \
    && rm -rf /var/lib/apt/lists/*

# Tạo user developer
RUN useradd -m -s /bin/bash developer && \
    echo "developer:developer" | chpasswd && \
    mkdir -p /home/developer/.ssh && \
    chown -R developer:developer /home/developer

# Cấu hình SSH
RUN mkdir /var/run/sshd && \
    echo "PermitRootLogin no" >> /etc/ssh/sshd_config && \
    echo "PasswordAuthentication yes" >> /etc/ssh/sshd_config

# Cấu hình Maven
COPY docker/settings.xml /home/developer/.m2/settings.xml
RUN chown -R developer:developer /home/developer/.m2

# Cấu hình Java và Maven
ENV JAVA_HOME=/usr/local/openjdk-11
ENV PATH=$PATH:$JAVA_HOME/bin:/usr/share/maven/bin

# Expose ports
EXPOSE 22 8080

# Start SSH server
CMD ["/usr/sbin/sshd", "-D"]

# Copy pom.xml và install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Không cần ENTRYPOINT vì container này chỉ dùng để cung cấp môi trường