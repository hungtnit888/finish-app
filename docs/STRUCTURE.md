# Language / Ngôn ngữ

- [English](#english)
- [Tiếng Việt](#tiếng-việt)

---

<a name="english"></a>
# Project Structure

## Overview

The Finish App is structured as a Kubernetes-based microservices application with the following main components:

```
.
├── k8s/                    # Kubernetes configuration files
│   ├── app/               # Application deployment files
│   ├── databases/         # Database StatefulSets
│   ├── monitoring/        # Prometheus and Grafana setup
│   ├── ingress/          # Ingress configurations
│   ├── env/              # Environment-specific configs
│   │   ├── dev/         # Development environment
│   │   ├── staging/     # Staging environment
│   │   └── prod/        # Production environment
│   └── scripts/          # Deployment and maintenance scripts
├── src/                   # Application source code
│   ├── main/
│   │   ├── java/        # Java source files
│   │   └── resources/   # Application resources
│   └── test/            # Test files
├── docs/                  # Documentation
└── pom.xml               # Maven configuration
```

## Kubernetes Configuration

### Application (k8s/app/)
- Deployment configurations for the Spring Boot application
- Service definitions
- Resource limits and requests
- Health check configurations

### Databases (k8s/databases/)
- PostgreSQL StatefulSet and Service
- MongoDB StatefulSet and Service
- Redis StatefulSet and Service
- Persistent volume claims

### Monitoring (k8s/monitoring/)
- Prometheus deployment and configuration
- Grafana deployment and dashboards
- Service monitors and alerts

### Ingress (k8s/ingress/)
- Ingress rules for all services
- TLS configuration
- Path-based routing

### Environment Configurations (k8s/env/)
- Environment-specific variables
- Secrets management
- Resource quotas
- Network policies

### Utility Scripts (k8s/scripts/)
- Deployment automation
- Backup procedures
- Environment cleanup
- Health checks

## Application Structure

### Source Code (src/)

#### Main Application (src/main/java/)
- Controllers: REST endpoints
- Services: Business logic
- Repositories: Data access
- Models: Domain entities
- Configuration: Application setup

#### Resources (src/main/resources/)
- Application properties
- Logging configuration
- Database migrations
- Static resources

#### Tests (src/test/)
- Unit tests
- Integration tests
- Performance tests

## Security

The application implements multiple security layers:

1. Network Security
   - Network policies
   - TLS encryption
   - Ingress rules

2. Application Security
   - JWT authentication
   - Role-based access control
   - Input validation

3. Data Security
   - Encrypted secrets
   - Secure connections
   - Data encryption

## Monitoring and Observability

1. Metrics Collection
   - Application metrics
   - JVM metrics
   - Custom business metrics

2. Logging
   - Structured logging
   - Log aggregation
   - Error tracking

3. Alerting
   - Resource utilization
   - Error rates
   - Business KPIs

## Deployment Process

1. Environment Setup
   ```bash
   ./k8s/scripts/deploy.sh <environment>
   ```

2. Monitoring Setup
   - Prometheus deployment
   - Grafana configuration
   - Alert rules setup

3. Database Management
   - Initialization
   - Migrations
   - Backups

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for development guidelines and processes.

---

<a name="tiếng-việt"></a>
# Cấu trúc dự án

## Overview

The Finish App is structured as a Kubernetes-based microservices application with the following main components:

```
.
├── k8s/                    # Kubernetes configuration files
│   ├── app/               # Application deployment files
│   ├── databases/         # Database StatefulSets
│   ├── monitoring/        # Prometheus and Grafana setup
│   ├── ingress/          # Ingress configurations
│   ├── env/              # Environment-specific configs
│   │   ├── dev/         # Development environment
│   │   ├── staging/     # Staging environment
│   │   └── prod/        # Production environment
│   └── scripts/          # Deployment and maintenance scripts
├── src/                   # Application source code
│   ├── main/
│   │   ├── java/        # Java source files
│   │   └── resources/   # Application resources
│   └── test/            # Test files
├── docs/                  # Documentation
└── pom.xml               # Maven configuration
```

## Kubernetes Configuration

### Application (k8s/app/)
- Deployment configurations for the Spring Boot application
- Service definitions
- Resource limits and requests
- Health check configurations

### Databases (k8s/databases/)
- PostgreSQL StatefulSet and Service
- MongoDB StatefulSet and Service
- Redis StatefulSet and Service
- Persistent volume claims

### Monitoring (k8s/monitoring/)
- Prometheus deployment and configuration
- Grafana deployment and dashboards
- Service monitors and alerts

### Ingress (k8s/ingress/)
- Ingress rules for all services
- TLS configuration
- Path-based routing

### Environment Configurations (k8s/env/)
- Environment-specific variables
- Secrets management
- Resource quotas
- Network policies

### Utility Scripts (k8s/scripts/)
- Deployment automation
- Backup procedures
- Environment cleanup
- Health checks

## Application Structure

### Source Code (src/)

#### Main Application (src/main/java/)
- Controllers: REST endpoints
- Services: Business logic
- Repositories: Data access
- Models: Domain entities
- Configuration: Application setup

#### Resources (src/main/resources/)
- Application properties
- Logging configuration
- Database migrations
- Static resources

#### Tests (src/test/)
- Unit tests
- Integration tests
- Performance tests

## Security

The application implements multiple security layers:

1. Network Security
   - Network policies
   - TLS encryption
   - Ingress rules

2. Application Security
   - JWT authentication
   - Role-based access control
   - Input validation

3. Data Security
   - Encrypted secrets
   - Secure connections
   - Data encryption

## Monitoring and Observability

1. Metrics Collection
   - Application metrics
   - JVM metrics
   - Custom business metrics

2. Logging
   - Structured logging
   - Log aggregation
   - Error tracking

3. Alerting
   - Resource utilization
   - Error rates
   - Business KPIs

## Deployment Process

1. Environment Setup
   ```bash
   ./k8s/scripts/deploy.sh <environment>
   ```

2. Monitoring Setup
   - Prometheus deployment
   - Grafana configuration
   - Alert rules setup

3. Database Management
   - Initialization
   - Migrations
   - Backups

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for development guidelines and processes. 