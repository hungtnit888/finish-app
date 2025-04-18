package com.example.demo.dto;

import com.example.base.dto.BaseDTO;
import com.example.base.security.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Full name is required")
    private String fullName;

    private Set<Role> roles;
    private boolean enabled;
} 