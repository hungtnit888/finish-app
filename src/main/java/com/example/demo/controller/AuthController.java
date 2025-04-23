package com.example.demo.controller;

import com.example.base.config.ApiRoutes;
import com.example.base.dto.LoginRequestDTO;
import com.example.base.dto.JwtResponseDTO;
import com.example.base.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(ApiRoutes.AUTH) // Maps to /api/v1/auth
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for user authentication")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    // TODO: Add a registration endpoint if needed

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and return JWT")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody LoginRequestDTO loginRequest,
            HttpServletRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtResponseDTO(jwt));
    }

    // Tạm thời comment out các endpoint refresh/logout vì chúng phụ thuộc fingerprint/cookie
    /*
    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token using Refresh Token from cookie")
    public ResponseEntity<?> refreshToken(...) { ... }

    @PostMapping("/logout")
    @Operation(summary = "Logout user and clear refresh token")
    public ResponseEntity<?> logoutUser(...) { ... }
    */
} 