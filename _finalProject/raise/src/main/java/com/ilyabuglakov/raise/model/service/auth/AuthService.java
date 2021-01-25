package com.ilyabuglakov.raise.model.service.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    boolean isAuthenticated();
    boolean login(String username, String password);
    String getPreviousUrl(HttpServletRequest request, String defaultUrl);
}
