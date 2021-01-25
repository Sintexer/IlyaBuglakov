package com.ilyabuglakov.raise.command.impl.login;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.auth.AuthService;
import com.ilyabuglakov.raise.model.service.auth.AuthServiceFactory;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
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
public class LoginPostCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        log.info("posted to Login");

        Subject currentUser = SecurityUtils.getSubject();
        log.debug(currentUser);

        ResponseEntity responseEntity = new ResponseEntity();
        AuthService authService = AuthServiceFactory.getAuthService();
        if(!authService.isAuthenticated()){
            boolean success = authService.login(request.getParameter("username"), request.getParameter("password"));
            if(success){
                String previousUrl = authService.getPreviousUrl(request,
                        PropertiesStorage.getInstance().getLinks().getProperty("root"));
                responseEntity.setRedirect(true);
                responseEntity.setLink(previousUrl);
            }else{
                responseEntity.getAttributes().put("loginFailed", true);
                responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("login"));
            }
        }else {
            responseEntity.getAttributes().put("alreadyLogged", true);
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("login"));
        }
        return responseEntity;
//        if (!currentUser.isAuthenticated()) {
//            log.info("login auth");
//            UsernamePasswordToken token = new UsernamePasswordToken(
//                    request.getParameter("username"),
//                    request.getParameter("password"));
//            try {
//                currentUser.login(token);
//                log.info("after login");
//                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
//                if (savedRequest != null) {
//                    log.info("redirect to prev page");
//                    WebUtils.redirectToSavedRequest(request, response, savedRequest.getRequestUrl());
//                } else {
//                    log.info("redirect to home page");
//                    responseEntity.setLink(PropertiesStorage.getInstance().getLinks().getProperty("root"));
//                    responseEntity.setRedirect(true);
//                    return responseEntity;
//                }
//                return null;
//            } catch (UnknownAccountException | IncorrectCredentialsException e) {
//                responseEntity.getAttributes().put("loginFailed", true);
//                responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("login"));
//            }
//        } else {
//            responseEntity.getAttributes().put("alreadyLogged", true);
//            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("login"));
//        }

    }
}
