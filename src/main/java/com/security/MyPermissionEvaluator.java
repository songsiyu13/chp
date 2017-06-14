package com.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by 滩涂上的芦苇 on 2017/4/6.
 */
public class MyPermissionEvaluator implements PermissionEvaluator {
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission)
    {
        if ("read".equals(targetDomainObject)) {
            return this.hasPermission(authentication, permission);
        }
        return false;
    }

    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId, String targetType, Object permission) {
        return true;
    }

    private boolean hasPermission(Authentication authentication, Object permission) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(permission)) {
                return true;
            }
        }
        return false;
    }
}
