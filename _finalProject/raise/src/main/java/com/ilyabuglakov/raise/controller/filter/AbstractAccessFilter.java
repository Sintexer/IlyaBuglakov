package com.ilyabuglakov.raise.controller.filter;

import com.ilyabuglakov.raise.model.RequestMethod;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public abstract class AbstractAccessFilter implements Filter {

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

    protected Optional<RequestMethod> extractMethod(HttpServletRequest request) {
        try {
            return Optional.of(RequestMethod.valueOf(request.getMethod()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

}
