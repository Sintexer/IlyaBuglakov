package com.ilyabuglakov.raise.command.impl.user;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.dto.UserInfoDto;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.UserService;
import com.ilyabuglakov.raise.model.service.user.UserInfoChangeService;
import com.ilyabuglakov.raise.model.service.validator.UserCredentialsValidator;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class UserProfileChangeSaveCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PersistentException {
        Subject subject = SecurityUtils.getSubject();

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .name(request.getParameter("name"))
                .surname(request.getParameter("surname"))
                .oldPassword(request.getParameter("oldPassword"))
                .newPassword(request.getParameter("newPassword"))
                .newPasswordRepeat(request.getParameter("newPasswordRepeat"))
                .build();

        UserService userService = (UserService) serviceFactory.createService(ServiceType.USER);
        User user = userService.getUser((String) subject.getPrincipal()).orElseThrow(PersistentException::new);

        userInfoDto.setUser(user);
        ResponseEntity responseEntity = userService.changeUserInfo(userInfoDto);

        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("user.profile.change"));
        return responseEntity;
    }
}
