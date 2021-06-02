package com.example.loginpanel.security;

public enum UserPermission {

    READ("read"),
    WRITE("write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermisision() {
        return permission;
    }
}
