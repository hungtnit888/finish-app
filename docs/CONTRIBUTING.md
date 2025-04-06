# Language / Ngôn ngữ

- [English](#english)
- [Tiếng Việt](#tiếng-việt)

---

<a name="english"></a>
# Contributing Guidelines

Thank you for your interest in contributing to our project! This document provides guidelines and instructions for contributing.

## Code Style

1. Follow the Google Java Style Guide
2. Use 4 spaces for indentation
3. Maximum line length: 100 characters
4. Use meaningful variable and method names
5. Add Javadoc comments for public methods

## Git Workflow

1. **Branch Naming**
   - Feature: `feature/feature-name`
   - Bugfix: `bugfix/bug-name`
   - Hotfix: `hotfix/issue-name`
   - Release: `release/version`

2. **Commit Messages**
   - Format: `type(scope): description`
   - Types: feat, fix, docs, style, refactor, test, chore
   - Example: `feat(auth): add JWT authentication`

3. **Pull Request Process**
   - Create PR from feature branch to main
   - Add clear description and related issues
   - Request review from at least 2 maintainers
   - Address all review comments
   - Ensure CI checks pass

## Development Process

1. **Setup**
   ```bash
   git clone https://github.com/yourusername/finish-app.git
   cd finish-app
   mvn clean install
   ```

2. **Create Feature Branch**
   ```bash
   git checkout -b feature/your-feature
   ```

3. **Make Changes**
   - Follow code style guidelines
   - Write unit tests
   - Update documentation

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

4. **Chạy kiểm thử**
   ```bash
   mvn test
   ```

5. **Chất lượng mã nguồn**
   ```bash
   mvn sonar:sonar
   ```

6. **Commit thay đổi**
   ```bash
   git add .
   git commit -m "feat(scope): mô tả"
   ```

7. **Đẩy thay đổi**
   ```bash
   git push origin feature/tính-năng-của-bạn
   ```

## Yêu cầu kiểm thử

1. **Unit Test**
   - Tối thiểu 80% coverage
   - Sử dụng JUnit 5
   - Tuân theo mẫu AAA
   - Sử dụng tên test có ý nghĩa

2. **Kiểm thử tích hợp**
   - Kiểm thử API endpoints
   - Kiểm thử thao tác cơ sở dữ liệu
   - Sử dụng TestContainers

3. **Kiểm thử hiệu suất**
   - Sử dụng JMeter
   - Kiểm thử dưới tải
   - Giám sát metrics

## Tài liệu

1. **Tài liệu code**
   - Javadoc cho các phương thức public
   - Chú thích nội dòng cho logic phức tạp
   - Cập nhật README cho tính năng mới

2. **Tài liệu API**
   - Chú thích OpenAPI
   - Ví dụ request/response
   - Kịch bản lỗi

3. **Tài liệu kiến trúc**
   - Cập nhật biểu đồ
   - Tài liệu hóa quyết định thiết kế
   - Cập nhật hướng dẫn triển khai

## Quy trình review

1. **Review code**
   - Kiểm tra quy tắc viết code
   - Xác minh test coverage
   - Xem xét khía cạnh bảo mật
   - Kiểm tra tác động hiệu suất

2. **Phê duyệt**
   - Cần ít nhất 2 phê duyệt
   - Phải giải quyết tất cả nhận xét
   - Các kiểm tra CI phải pass

3. **Merge**
   - Squash và merge
   - Xóa nhánh tính năng
   - Cập nhật phiên bản nếu cần

## Hỗ trợ

1. **Vấn đề**
   - Sử dụng mẫu issue
   - Cung cấp các bước tái hiện
   - Thêm log liên quan

2. **Thảo luận**
   - Sử dụng GitHub Discussions
   - Tuân theo quy tắc cộng đồng
   - Tôn trọng lẫn nhau

3. **Liên hệ**
   - Email: support@example.com
   - Slack: #project-support 