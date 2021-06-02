package com.example.loginpanel.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {

    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(UserPermission.READ,UserPermission.WRITE)),
    MODERATOR(Sets.newHashSet(UserPermission.WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
        .map(p -> new SimpleGrantedAuthority(p.getPermisision()))
        .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE"+this.name()));
        return permissions;
    }
}
