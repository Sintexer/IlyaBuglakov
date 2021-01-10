package com.ilyabuglakov.raise.service.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class WebService {

    private WebService next;

    public abstract void proceed(HttpServletRequest request, HttpServletResponse servletResponse);

    public WebService setNext(WebService next) {
        this.next = next;
        return this;
    }
}
