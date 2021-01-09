package com.ilyabuglakov.raise.controller.filter;


import com.ilyabuglakov.raise.model.RequestMethod;
import com.ilyabuglakov.raise.model.command.Command;
import com.ilyabuglakov.raise.storage.CommandStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = "/api/*")
@Log4j2
public class ApiAccessFilter extends AccessFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Optional<RequestMethod> method = extractMethod(request);
        if (method.isPresent()) {
            String link = extractLink(request);
            Command command = CommandStorage.getInstance().getCommand(link, method.get());
            if (command != null) {
                request.setAttribute("command", command);
                request.getServletContext().getRequestDispatcher("/controller").forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}