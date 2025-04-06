# Finish App - Kubernetes Deployment

This project contains the Kubernetes deployment configuration for the Finish App, a Spring Boot application with PostgreSQL, MongoDB, Redis, and monitoring stack (Prometheus & Grafana).

## Prerequisites

- Kubernetes cluster (minikube, kind, or cloud provider)
- kubectl CLI tool
- Helm (for installing Ingress Controller)
- Docker (for building images)

## Architecture

The application consists of the following components:

- Spring Boot Application
- PostgreSQL Database
- MongoDB Database
- Redis Cache
- Prometheus (Monitoring)
- Grafana (Visualization)
- NGINX Ingress Controller

## Directory Structure

```
k8s/
├── app/                    # Application deployment files
├── databases/             # Database deployment files
├── monitoring/            # Monitoring stack files
├── ingress/               # Ingress configuration
├── env/                   # Environment configuration
│   ├── dev/              # Development environment
│   ├── staging/          # Staging environment
│   └── prod/             # Production environment
└── scripts/              # Utility scripts
    ├── deploy.sh         # Deployment script
    ├── cleanup.sh        # Cleanup script
    └── backup.sh         # Backup script
```

## Quick Start

1. Install Ingress Controller:
```bash
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install ingress-nginx ingress-nginx/ingress-nginx
```

2. Deploy to development environment:
```bash
./k8s/scripts/deploy.sh dev
```

3. Deploy to staging environment:
```bash
./k8s/scripts/deploy.sh staging
```

4. Deploy to production environment:
```bash
./k8s/scripts/deploy.sh prod
```

## Environment Configuration

Each environment (dev, staging, prod) has its own configuration stored in the `k8s/env` directory:

- `app-env.yaml`: Contains environment-specific ConfigMap and Secret
- Different log levels and security settings for each environment
- Separate database credentials and JWT secrets

## Accessing Services

After deployment, you can access the services using the following URLs:

- Application: http://app.example.com
- Prometheus: http://prometheus.example.com
- Grafana: http://grafana.example.com

## Monitoring

The application is configured with Prometheus and Grafana for monitoring:

- Prometheus collects metrics from the application
- Grafana provides visualization dashboards
- Default Grafana credentials:
  - Username: admin
  - Password: admin

## Maintenance

### Database Backups

To create a manual backup:
```bash
./k8s/scripts/backup.sh <environment>
```

This will create a backup of:
- PostgreSQL database
- MongoDB database
- Redis data

### Scaling

To scale the application:
```bash
kubectl scale deployment spring-app -n finish-app --replicas=3
```

### Cleanup

To remove all resources for an environment:
```bash
./k8s/scripts/cleanup.sh <environment>
```

## Troubleshooting

1. Check pod status:
```bash
kubectl get pods -n finish-app
kubectl get pods -n monitoring
```

2. View logs:
```bash
kubectl logs -f <pod-name> -n finish-app
```

3. Check ingress configuration:
```bash
kubectl get ingress -n finish-app
kubectl describe ingress app-ingress -n finish-app
```

4. Check service status:
```bash
kubectl get svc -n finish-app
kubectl get svc -n monitoring
```

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details. 