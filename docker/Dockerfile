FROM maven:3.8.6-openjdk-11-slim

# Cài đặt các công cụ cần thiết
RUN apt-get update && apt-get install -y \
    git \
    curl \
    vim \
    && rm -rf /var/lib/apt/lists/*

# Tạo user để tránh chạy với root
RUN useradd -m -s /bin/bash developer
RUN chown -R developer:developer /app

# Cấu hình Maven
COPY settings.xml /home/developer/.m2/settings.xml
RUN chown -R developer:developer /home/developer/.m2

# Cấu hình SSH để IDE có thể kết nối
RUN apt-get update && apt-get install -y openssh-server
RUN mkdir /var/run/sshd
RUN echo 'developer:developer' | chpasswd
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -i 's/#PasswordAuthentication yes/PasswordAuthentication yes/' /etc/ssh/sshd_config

# Cấu hình môi trường
USER developer
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME/bin

WORKDIR /app

# Khởi động SSH
USER root
EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]

# Copy pom.xml và install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

EXPOSE 8080

# Không cần ENTRYPOINT vì container này chỉ dùng để cung cấp môi trường