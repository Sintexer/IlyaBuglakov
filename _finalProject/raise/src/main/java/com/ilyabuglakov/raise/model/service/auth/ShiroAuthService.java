package com.ilyabuglakov.raise.model.service.auth;

import org.apache.shiro.SecurityUtils;

public class ShiroAuthService implements AuthService{
    @Override
    public boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }
}
