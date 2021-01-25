package com.ilyabuglakov.raise.model.service.servlet;

import javax.servlet.http.Cookie;

public class CookieService {

    public static Cookie createLocaleCookie(String locale){
        Cookie cookie = new Cookie("userLocale", locale);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

}
