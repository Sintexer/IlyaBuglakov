package com.ilyabuglakov.raise.controller.filter;

import com.ilyabuglakov.raise.model.command.Command;
import com.ilyabuglakov.raise.storage.CommandStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Log4j2
public class UrlAccessFilter implements Filter {
    //    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("Entered filter");
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        log.warn(uri);

        int relatedUriBegin = contextPath.length();
        int relatedUriEnd = uri.lastIndexOf(".");
        String link;
        if (relatedUriBegin > 0) {
            if (relatedUriEnd >= 0)
                link = uri.substring(relatedUriBegin, relatedUriEnd);
            else
                link = uri.substring(relatedUriBegin);
        } else
            link = uri;

        Command command = CommandStorage.getInstance().getCommand(link);
        if (command != null) {
            request.setAttribute("command", command);
            request.getServletContext().getRequestDispatcher("/controller").forward(servletRequest, servletResponse);
        } else {
            log.warn(link);
            filterChain.doFilter(servletRequest, servletResponse);


        }
    }
}