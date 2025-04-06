package com.example.base.security;

public enum Permission {
    READ("read"),
    WRITE("write"),
    UPDATE("update"),
    DELETE("delete"),
    ADMIN("admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
} 