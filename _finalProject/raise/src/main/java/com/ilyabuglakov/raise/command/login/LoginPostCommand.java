package com.ilyabuglakov.raise.command.login;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class LoginPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        log.info("posted to Login");

        Subject currentUser = SecurityUtils.getSubject();
        log.warn(currentUser);

        if (!currentUser.isAuthenticated()) {
            log.info("login auth");
            UsernamePasswordToken token = new UsernamePasswordToken(
                    request.getParameter("username"),
                    request.getParameter("password"));
            try {
                currentUser.login(token);

            } catch (UnknownAccountException | IncorrectCredentialsException e) {
                request.setAttribute("loginFailed", true);
                request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("login"))
                        .forward(request, response);
                return;
            }
        } else {
            request.setAttribute("alreadyLogged", true);
            request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("login"))
                    .forward(request, response);
            return;
        }

        log.info("after login");
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        if(savedRequest!=null){
            log.info("redirect to prev page");
            WebUtils.redirectToSavedRequest(request, response, savedRequest.getRequestUrl());
        } else {
            log.info("redirect to home page");
            response.sendRedirect(PropertiesStorage.getInstance().getLinks().getProperty("root"));
        }

    }
}
