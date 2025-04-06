package com.example.base.controller;

import com.example.base.service.CacheService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
@RequiredArgsConstructor
@Tag(name = "Cache", description = "Cache management APIs")
public class CacheController {

    private final CacheService cacheService;

    @GetMapping("/{id}")
    @Operation(summary = "Get cached data", description = "Retrieves data from cache or generates new data if not cached")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Data not found")
    })
    public ResponseEntity<String> getData(
            @Parameter(description = "Data identifier") @PathVariable String id) {
        return ResponseEntity.ok(cacheService.getData(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update cached data", description = "Updates data in both cache and storage")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Data updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<String> updateData(
            @Parameter(description = "Data identifier") @PathVariable String id,
            @Parameter(description = "New data value") @RequestBody String data) {
        return ResponseEntity.ok(cacheService.updateData(id, data));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete cached data", description = "Removes data from both cache and storage")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Data deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Data not found")
    })
    public ResponseEntity<Void> deleteData(
            @Parameter(description = "Data identifier") @PathVariable String id) {
        cacheService.deleteData(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    @Operation(summary = "Clear all cache", description = "Removes all entries from both cache and storage")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cache cleared successfully")
    })
    public ResponseEntity<Void> clearCache() {
        cacheService.clearCache();
        return ResponseEntity.noContent().build();
    }
} 