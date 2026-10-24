package com.tuanh.ecommerce.util;

import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {
    public static String getCurrentUsernameInSecurityContext(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
