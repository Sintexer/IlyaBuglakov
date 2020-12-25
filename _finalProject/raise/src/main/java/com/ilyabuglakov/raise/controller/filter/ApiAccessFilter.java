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

@WebFilter(urlPatterns = "/" +
        "api/*")
@Log4j2
public class ApiAccessFilter extends AccessFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String link = extractLink(request);

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