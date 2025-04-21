package com.example.demo.controller;

import com.example.base.config.ApiRoutes;
import com.example.base.controller.BaseController;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.USERS)
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController extends BaseController<UserService, UserDTO, Long> {
    public UserController(UserService userService) {
        super(userService);
    }

    @Override
    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
        throw new UnsupportedOperationException("Use specific endpoint for creating user with password");
    }

    @PostMapping("/with-password")
    @Operation(summary = "Create a new user with password")
    public ResponseEntity<UserDTO> createUserWithPassword(@Valid @RequestBody UserDTO userDTO,
                                                         @RequestParam String password) {
        return ResponseEntity.ok(this.service.createUser(userDTO, password));
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<UserDTO> update(@PathVariable Long id,
                                           @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(this.service.updateUser(id, userDTO));
    }

    @Override
    @GetMapping
    @Operation(summary = "Get all users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(this.service.getAllUsers());
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }
} 