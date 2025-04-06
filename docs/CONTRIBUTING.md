# Language / Ngôn ngữ

- [English](#english)
- [Tiếng Việt](#tiếng-việt)

---

<a name="english"></a>
# Contributing to Finish App

## Overview

Thank you for considering contributing to Finish App! This document provides guidelines and instructions for contributing to our Kubernetes-based application.

## Development Environment Setup

1. Install Prerequisites:
   - Kubernetes cluster (minikube/kind for local development)
   - kubectl
   - Helm
   - Java 17
   - Maven

2. Clone the repository:
```bash
git clone https://github.com/yourusername/finish-app.git
cd finish-app
```

3. Set up local development environment:
```bash
# Start minikube
minikube start

# Enable ingress
minikube addons enable ingress

# Install dependencies
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
```

## Development Workflow

1. Create a new branch:
```bash
git checkout -b feature/your-feature
```

2. Make your changes following our coding standards

3. Test your changes:
```bash
# Run unit tests
mvn test

# Deploy to local cluster
./k8s/scripts/deploy.sh dev
```

4. Submit a pull request

## Kubernetes Development

### Directory Structure

```
k8s/
├── app/                    # Application deployment files
├── databases/             # Database StatefulSets
├── monitoring/            # Prometheus and Grafana setup
├── ingress/              # Ingress configurations
├── env/                  # Environment-specific configs
└── scripts/              # Utility scripts
```

### Best Practices

1. Resource Configuration
   - Use resource limits and requests
   - Implement health checks
   - Configure proper security contexts

2. Environment Management
   - Keep environment-specific configs in k8s/env/
   - Use ConfigMaps and Secrets appropriately
   - Document environment variables

3. Monitoring
   - Add relevant metrics
   - Create useful dashboards
   - Configure meaningful alerts

## Testing

1. Unit Tests
```bash
mvn test
```

2. Integration Tests
```bash
mvn verify
```

3. Kubernetes Tests
```bash
# Test deployment
./k8s/scripts/deploy.sh dev

# Verify services
kubectl get pods -n finish-app
```

## Documentation

1. Code Documentation
   - Document all public APIs
   - Add meaningful comments
   - Update README.md when needed

2. Kubernetes Configuration
   - Document resource requirements
   - Explain configuration options
   - Update deployment guides

## Pull Request Process

1. Update Documentation
   - Add/update relevant documentation
   - Include configuration changes
   - Document new features

2. Testing
   - Add appropriate tests
   - Ensure all tests pass
   - Test deployment process

3. Review
   - Request review from maintainers
   - Address review comments
   - Update PR as needed

## Style Guide

### Code Style

1. Java
   - Follow Google Java Style Guide
   - Use meaningful variable names
   - Add proper documentation

2. Kubernetes Configuration
   - Use consistent indentation
   - Follow Kubernetes naming conventions
   - Document all configurations

### Commit Messages

1. Format
```
type(scope): description

[optional body]
[optional footer]
```

2. Types
   - feat: New feature
   - fix: Bug fix
   - docs: Documentation
   - chore: Maintenance
   - refactor: Code restructuring

## Release Process

1. Version Bump
   - Update version in pom.xml
   - Update Kubernetes configurations
   - Update documentation

2. Testing
   - Run all tests
   - Deploy to staging
   - Verify functionality

3. Release
   - Create release branch
   - Tag release
   - Deploy to production

## Getting Help

- Join our Slack channel
- Check existing issues
- Contact maintainers

## Code of Conduct

Please read and follow our [Code of Conduct](CODE_OF_CONDUCT.md).

## License

By contributing, you agree that your contributions will be licensed under the project's MIT License.

---

<a name="tiếng-việt"></a>
# Hướng dẫn đóng góp

Cảm ơn bạn đã quan tâm đến việc đóng góp cho dự án của chúng tôi! Tài liệu này cung cấp các hướng dẫn và chỉ dẫn cho việc đóng góp.

## Quy tắc viết code

1. Tuân theo Google Java Style Guide
2. Sử dụng 4 khoảng trắng cho thụt lề
3. Độ dài tối đa dòng: 100 ký tự
4. Sử dụng tên biến và phương thức có ý nghĩa
5. Thêm chú thích Javadoc cho các phương thức public

## Quy trình Git

1. **Đặt tên nhánh**
   - Tính năng: `feature/tên-tính-năng`
   - Sửa lỗi: `bugfix/tên-lỗi`
   - Sửa khẩn cấp: `hotfix/tên-vấn-đề`
   - Phát hành: `release/phiên-bản`

2. **Tin nhắn commit**
   - Định dạng: `type(scope): mô tả`
   - Loại: feat, fix, docs, style, refactor, test, chore
   - Ví dụ: `feat(auth): thêm xác thực JWT`

3. **Quy trình Pull Request**
   - Tạo PR từ nhánh tính năng vào main
   - Thêm mô tả rõ ràng và các vấn đề liên quan
   - Yêu cầu review từ ít nhất 2 người bảo trì
   - Giải quyết tất cả nhận xét review
   - Đảm bảo các kiểm tra CI đều pass

## Quy trình phát triển

1. **Cài đặt**
   ```bash
   git clone https://github.com/yourusername/finish-app.git
   cd finish-app
   mvn clean install
   ```

2. **Tạo nhánh tính năng**
   ```bash
   git checkout -b feature/tính-năng-của-bạn
   ```

3. **Thực hiện thay đổi**
   - Tuân theo quy tắc viết code
   - Viết unit test
   - Cập nhật tài liệu

4. **Run Tests**
   ```bash
   mvn test
   ```

5. **Code Quality**
   ```bash
   mvn sonar:sonar
   ```

6. **Commit Changes**
   ```bash
   git add .
   git commit -m "feat(scope): description"
   ```

7. **Push Changes**
   ```bash
   git push origin feature/your-feature
   ```

## Testing Requirements

1. **Unit Tests**
   - Minimum 80% coverage
   - Use JUnit 5
   - Follow AAA pattern
   - Use meaningful test names

2. **Integration Tests**
   - Test API endpoints
   - Test database operations
   - Use TestContainers

3. **Performance Tests**
   - Use JMeter
   - Test under load
   - Monitor metrics

## Documentation

1. **Code Documentation**
   - Javadoc for public methods
   - Inline comments for complex logic
   - README updates for new features

2. **API Documentation**
   - OpenAPI annotations
   - Example requests/responses
   - Error scenarios

3. **Architecture Documentation**
   - Update diagrams
   - Document design decisions
   - Update deployment guides

## Review Process

1. **Code Review**
   - Check code style
   - Verify test coverage
   - Review security aspects
   - Check performance impact

2. **Approval**
   - At least 2 approvals required
   - All comments must be addressed
   - CI checks must pass

3. **Merging**
   - Squash and merge
   - Delete feature branch
   - Update version if needed

## Support

1. **Issues**
   - Use issue templates
   - Provide reproduction steps
   - Add relevant logs

2. **Discussions**
   - Use GitHub Discussions
   - Follow community guidelines
   - Be respectful

3. **Contact**
   - Email: support@example.com
   - Slack: #project-support

## Kubernetes Development

### Directory Structure

```
k8s/
├── app/                    # Application deployment files
├── databases/             # Database StatefulSets
├── monitoring/            # Prometheus and Grafana setup
├── ingress/              # Ingress configurations
├── env/                  # Environment-specific configs
└── scripts/              # Utility scripts
```

### Best Practices

1. Resource Configuration
   - Use resource limits and requests
   - Implement health checks
   - Configure proper security contexts

2. Environment Management
   - Keep environment-specific configs in k8s/env/
   - Use ConfigMaps and Secrets appropriately
   - Document environment variables

3. Monitoring
   - Add relevant metrics
   - Create useful dashboards
   - Configure meaningful alerts

## Testing

1. Unit Tests
```bash
mvn test
```

2. Integration Tests
```bash
mvn verify
```

3. Kubernetes Tests
```bash
# Test deployment
./k8s/scripts/deploy.sh dev

# Verify services
kubectl get pods -n finish-app
```

## Documentation

1. Code Documentation
   - Document all public APIs
   - Add meaningful comments
   - Update README.md when needed

2. Kubernetes Configuration
   - Document resource requirements
   - Explain configuration options
   - Update deployment guides

## Pull Request Process

1. Update Documentation
   - Add/update relevant documentation
   - Include configuration changes
   - Document new features

2. Testing
   - Add appropriate tests
   - Ensure all tests pass
   - Test deployment process

3. Review
   - Request review from maintainers
   - Address review comments
   - Update PR as needed

## Style Guide

### Code Style

1. Java
   - Follow Google Java Style Guide
   - Use meaningful variable names
   - Add proper documentation

2. Kubernetes Configuration
   - Use consistent indentation
   - Follow Kubernetes naming conventions
   - Document all configurations

### Commit Messages

1. Format
```
type(scope): description

[optional body]
[optional footer]
```

2. Types
   - feat: New feature
   - fix: Bug fix
   - docs: Documentation
   - chore: Maintenance
   - refactor: Code restructuring

## Release Process

1. Version Bump
   - Update version in pom.xml
   - Update Kubernetes configurations
   - Update documentation

2. Testing
   - Run all tests
   - Deploy to staging
   - Verify functionality

3. Release
   - Create release branch
   - Tag release
   - Deploy to production

## Getting Help

- Join our Slack channel
- Check existing issues
- Contact maintainers

## Code of Conduct

Please read and follow our [Code of Conduct](CODE_OF_CONDUCT.md).

## License

By contributing, you agree that your contributions will be licensed under the project's MIT License. 