package com.ilyabuglakov.raise.controller.filter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

public abstract class AccessFilter implements Filter {

    protected String extractLink(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int relatedUriBegin = request.getContextPath().length();
        int relatedUriEnd = uri.lastIndexOf(".");
        String link;
        if (relatedUriBegin > 0) {
            if (relatedUriEnd >= 0)
                link = uri.substring(relatedUriBegin, relatedUriEnd);
            else
                link = uri.substring(relatedUriBegin);
        } else
            link = uri;
        return link;
    }

}
