package com.sinotrans.security;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import lombok.var;

public class SecurityUtil {
    public static Optional<User> getCurrentUser() {
        var p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (p instanceof User) {
            return Optional.of((User)p);
        }
        return Optional.empty();
    }
}
