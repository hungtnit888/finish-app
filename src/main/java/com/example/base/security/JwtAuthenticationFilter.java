package com.example.base.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor // Dùng Lombok để inject final fields
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider; // Inject provider
    private final UserDetailsService userDetailsService; // Inject user details service

    // Tên header chứa fingerprint
    private static final String FINGERPRINT_HEADER = "X-Fingerprint";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Bỏ qua filter cho các đường dẫn public (ví dụ: /auth/**) -> Cách tiếp cận đơn giản nhất
        // Nếu không có header Authorization, cho đi tiếp (để login/refresh hoạt động)
        String jwt = getJwtFromRequest(request);
        if (!StringUtils.hasText(jwt)) {
             // Log nếu cần: log.trace("Request URI {} does not contain JWT Header", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        // Nếu có JWT, tiến hành xác thực
        try {
            // Tạm thời bỏ qua fingerprint
            // String requestFingerprint = request.getHeader(FINGERPRINT_HEADER);

            // Chỉ validate token signature & expiration
            if (tokenProvider.validateTokenSignatureAndExpiration(jwt)) {

                // Tạm thời bỏ qua kiểm tra fingerprint
                /*
                String tokenFingerprintHash = tokenProvider.getFingerprintHashFromToken(jwt);
                if (tokenProvider.validateFingerprint(tokenFingerprintHash, requestFingerprint)) {
                */

                    // Token hợp lệ (signature/expiration), tiến hành lấy user và set context
                    String username = tokenProvider.getUsernameFromJWT(jwt);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("doFilterInternal: Set SecurityContextHolder to {} for request URI {}", username, request.getRequestURI());

                /*
                } else {
                    // Fingerprint không khớp
                    log.warn("doFilterInternal: Fingerprint validation failed for token. URI: {}", request.getRequestURI());
                    SecurityContextHolder.clearContext(); // Clear context
                }
                */
            } else {
                 log.warn("doFilterInternal: JWT signature/expiration validation failed. URI: {}", request.getRequestURI());
                 SecurityContextHolder.clearContext(); // Clear context nếu token không hợp lệ
            }
        } catch (Exception ex) {
            // Log lỗi chi tiết hơn thay vì chỉ clear context
            log.error("doFilterInternal: Could not set user authentication in security context for URI {}: {}", request.getRequestURI(), ex.getMessage(), ex);
            SecurityContextHolder.clearContext(); // Đảm bảo context được xóa khi có lỗi
        }

        filterChain.doFilter(request, response); // Chuyển request/response cho filter tiếp theo
    }

    // Helper để lấy token từ header Authorization
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
} 