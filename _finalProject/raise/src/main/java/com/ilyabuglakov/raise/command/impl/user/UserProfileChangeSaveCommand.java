package com.ilyabuglakov.raise.command.impl.user;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.UserService;
import com.ilyabuglakov.raise.model.service.user.UserInfoChangeService;
import com.ilyabuglakov.raise.model.service.validator.UserCredentialsValidator;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserProfileChangeSaveCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, PersistentException {
        Subject subject = SecurityUtils.getSubject();
        ResponseEntity responseEntity = new ResponseEntity();
        //todo dto for change results
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordRepeat = request.getParameter("newPasswordRepeat");
        boolean somethingChanged = false;
        boolean somethingWrong = false;

        UserService userService = (UserService) serviceFactory.createService(ServiceType.USER);
        User user = userService.getUser((String) subject.getPrincipal()).orElseThrow(PersistentException::new);

        UserInfoChangeService userInfoChangeService = new UserInfoChangeService();
        if (!name.isEmpty()) {
            responseEntity.setAttribute("nameChanged",
                    userInfoChangeService.changeName(user, name));
            somethingChanged = true;
        }
        if (!surname.isEmpty()) {
            responseEntity.setAttribute("surnameChanged",
                    userInfoChangeService.changeSurname(user, surname));
            somethingChanged = true;
        }

        if (!oldPassword.isEmpty() && !newPassword.isEmpty() && !newPasswordRepeat.isEmpty()) {
            UserCredentialsValidator userCredentialsValidator = new UserCredentialsValidator();
            if(userCredentialsValidator.isCorrectOldPassword(user, oldPassword)){
                responseEntity.setAttribute("passwordChanged",
                        userInfoChangeService.changePassword(user, newPassword, newPasswordRepeat));
            } else{
                somethingWrong = true;
                responseEntity.setAttribute("incorrectOldPassword", true);
            }
        }

        if(somethingChanged){
            userService.updateUser(user);
        }

        responseEntity.setAttribute("userParameters", userService.getUserParameters(user.getId()));
        responseEntity.setAttribute("somethingChanged", somethingChanged);
        responseEntity.setAttribute("somethingWrong", somethingWrong);
        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("user.profile.change"));
        return responseEntity;
    }
}
