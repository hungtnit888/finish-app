# Language / Ngôn ngữ

- [English](#english)
- [Tiếng Việt](#tiếng-việt)

---

<a name="english"></a>
# Getting Started with Finish App

This guide will help you set up and run the Finish App on your Kubernetes cluster.

## Prerequisites

Before you begin, ensure you have the following installed:

- Kubernetes cluster (minikube, kind, or cloud provider)
- kubectl CLI tool
- Helm package manager
- Git

## Setup Steps

1. Clone the repository:
```bash
git clone https://github.com/yourusername/finish-app.git
cd finish-app
```

2. Install Ingress Controller:
```bash
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install ingress-nginx ingress-nginx/ingress-nginx
```

3. Deploy the application:

For development:
```bash
./k8s/scripts/deploy.sh dev
```

For staging:
```bash
./k8s/scripts/deploy.sh staging
```

For production:
```bash
./k8s/scripts/deploy.sh prod
```

## Verifying the Installation

1. Check if all pods are running:
```bash
kubectl get pods -n finish-app
kubectl get pods -n monitoring
```

2. Check services:
```bash
kubectl get svc -n finish-app
kubectl get svc -n monitoring
```

3. Check ingress:
```bash
kubectl get ingress -n finish-app
```

## Accessing the Application

Once deployed, you can access the following services:

- Application: http://app.example.com
- Prometheus: http://prometheus.example.com
- Grafana: http://grafana.example.com

Note: Update your DNS or hosts file to point these domains to your cluster's IP address.

## Environment Configuration

The application supports three environments:

1. Development (dev)
   - Debug level logging
   - Development mode features enabled
   - Less strict security settings

2. Staging
   - Info level logging
   - Production-like environment
   - Staging data and configurations

3. Production (prod)
   - Warning level logging
   - Maximum security settings
   - Production-ready configurations

## Monitoring

The application comes with built-in monitoring:

1. Prometheus
   - Metrics collection
   - Query interface at http://prometheus.example.com

2. Grafana
   - Pre-configured dashboards
   - Access at http://grafana.example.com
   - Default credentials:
     - Username: admin
     - Password: admin

## Maintenance

### Backups

Create database backups:
```bash
./k8s/scripts/backup.sh <environment>
```

### Cleanup

Remove environment:
```bash
./k8s/scripts/cleanup.sh <environment>
```

## Troubleshooting

### Common Issues

1. Pods not starting
```bash
kubectl describe pod <pod-name> -n finish-app
```

2. Service not accessible
```bash
kubectl get svc -n finish-app
kubectl describe svc <service-name> -n finish-app
```

3. Ingress issues
```bash
kubectl describe ingress -n finish-app
```

### Viewing Logs

Application logs:
```bash
kubectl logs -f <pod-name> -n finish-app
```

Database logs:
```bash
kubectl logs -f <database-pod-name> -n finish-app
```

## Next Steps

- Read the [Architecture Documentation](STRUCTURE.md)
- Check the [Security Guidelines](SECURITY.md)
- Review the [Contributing Guide](CONTRIBUTING.md)

---

<a name="tiếng-việt"></a>
# Bắt đầu

## Yêu cầu hệ thống

- Java 17
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 15
- MongoDB 6
- Redis 7

## Cài đặt

1. Clone repository:
```bash
git clone https://github.com/yourusername/finish-app.git
cd finish-app
```

2. Build ứng dụng:
```bash
mvn clean install
```

3. Chạy với Docker Compose:
```bash
docker-compose -f docker/docker-compose.yml up -d
```

## Truy cập ứng dụng

- API: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- Grafana: http://localhost:3000
- Prometheus: http://localhost:9090

## Phát triển

1. **Phát triển cục bộ**
```bash
mvn spring-boot:run -Dspring.profiles.active=local
```

2. **Chạy kiểm thử**
```bash
mvn test
```

3. **Di chuyển cơ sở dữ liệu**
```bash
mvn liquibase:update
```

4. **Chất lượng mã nguồn**
```bash
mvn sonar:sonar
```

## Giám sát & Bảo trì

1. **Quản lý Redis**
```bash
# Sao lưu
./docker/scripts/redis-backup.sh

# Khôi phục
./docker/scripts/redis-restore.sh redis_backup_20240315_120000.rdb.gz

# Giám sát
nohup ./docker/scripts/redis-monitor.sh &
```

2. **Sao lưu cơ sở dữ liệu**
```bash
./docker/scripts/backup.sh
```

3. **Khôi phục hệ thống**
```bash
./docker/scripts/recovery.sh
```

## Tài nguyên bổ sung
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Docker Documentation](https://docs.docker.com/)
- [Maven Documentation](https://maven.apache.org/guides/)

## Liên hệ hỗ trợ
Nếu gặp vấn đề, vui lòng:
1. Kiểm tra [../README.md](../README.md) và [STRUCTURE.md](STRUCTURE.md)
2. Tạo issue trên GitHub
3. Liên hệ qua email: your.email@example.com 

