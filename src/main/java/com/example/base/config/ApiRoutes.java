package com.example.base.config;

public class ApiRoutes {
    public static final String API_BASE = "/api";
    public static final String V1 = "/v1";
    
    // Auth routes
    public static final String AUTH = API_BASE + V1 + "/auth";
    public static final String LOGIN = AUTH + "/login";
    public static final String REGISTER = AUTH + "/register";
    public static final String REFRESH_TOKEN = AUTH + "/refresh-token";
    
    // User routes
    public static final String USERS = API_BASE + V1 + "/users";
    public static final String USER_BY_ID = USERS + "/{id}";
    public static final String USER_PROFILE = USERS + "/profile";
    
    // Admin routes
    public static final String ADMIN = API_BASE + V1 + "/admin";
    public static final String ADMIN_USERS = ADMIN + "/users";
    
    // Public routes
    public static final String PUBLIC = API_BASE + V1 + "/public";
    
    private ApiRoutes() {
        // Private constructor to prevent instantiation
    }
} 