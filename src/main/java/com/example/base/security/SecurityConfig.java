package com.example.base.security;

import com.example.base.config.ApiRoutes;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
// Bỏ import UserDetailsService nếu không dùng trực tiếp ở đây
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity is deprecated in newer Spring Security versions,
// consider using @EnableMethodSecurity(prePostEnabled = true) instead if applicable
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeRequests(auth -> auth
                // Allow auth endpoints (login, refresh, logout) - ApiRoutes.AUTH giờ là /v1/auth
                .antMatchers(ApiRoutes.AUTH + "/**").permitAll()
                // Allow public endpoints - ApiRoutes.PUBLIC giờ là /v1/public
                .antMatchers(ApiRoutes.PUBLIC + "/**").permitAll()
                // Actuator endpoints (thường không bị ảnh hưởng bởi context-path)
                .antMatchers("/actuator/**").permitAll()
                // Add other public paths like swagger (thường không bị ảnh hưởng bởi context-path)
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                // Allow access to the error path (không bị ảnh hưởng context-path)
                .antMatchers("/error").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Không sử dụng session
            )
            // Thêm bộ lọc JWT trước bộ lọc xử lý username/password
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        System.out.println("SecurityConfig");
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}