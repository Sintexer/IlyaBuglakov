package com.ilyabuglakov.raise.command.impl;

import com.ilyabuglakov.raise.command.Command;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class IndexPostCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("Entered POST index command");
//        User user = User.builder()
//                .email("ilboogl@gmail.com")
//                .password("121212")
//                .name("Ilya")
//                .surname("Buglakov")
//                .status(Status.ACTIVE)
//                .build();
        Subject currentUser = SecurityUtils.getSubject();
        log.warn(currentUser.isAuthenticated());
        if (!currentUser.isAuthenticated()) {
            log.info("auth");
            UsernamePasswordToken token
                    = new UsernamePasswordToken("ilboogl@gmail.com", "121212");
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                log.error("Username Not Found!", uae);
            } catch (IncorrectCredentialsException ice) {
                log.error("Invalid Credentials!", ice);
            } catch (LockedAccountException lae) {
                log.error("Your Account is Locked!", lae);
            } catch (AuthenticationException ae) {
                log.error("Unexpected Error!", ae);
            }
        }
    }
}
