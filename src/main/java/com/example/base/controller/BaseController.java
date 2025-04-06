package com.example.base.controller;

import com.example.base.dto.BaseDTO;
import com.example.base.service.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class BaseController<T extends BaseService<?, D, ID>, D extends BaseDTO, ID extends Serializable> {

    protected final T service;

    protected BaseController(T service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create a new resource")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Resource created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<D> create(@RequestBody D dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing resource")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resource updated successfully"),
        @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public ResponseEntity<D> update(
            @Parameter(description = "ID of the resource to update") @PathVariable ID id,
            @RequestBody D dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a resource")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Resource deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the resource to delete") @PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a resource by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resource found"),
        @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public ResponseEntity<D> findById(
            @Parameter(description = "ID of the resource to retrieve") @PathVariable ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "Get all resources")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resources retrieved successfully")
    })
    public ResponseEntity<List<D>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/page")
    @Operation(summary = "Get all resources with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resources retrieved successfully")
    })
    public ResponseEntity<Page<D>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }
} 