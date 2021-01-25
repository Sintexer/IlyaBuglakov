package com.ilyabuglakov.raise.command.impl.user;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.model.dto.UserParametersDto;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.auth.AuthService;
import com.ilyabuglakov.raise.model.service.auth.AuthServiceFactory;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.UserService;
import com.ilyabuglakov.raise.model.service.servlet.RequestService;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
public class UserProfileGetCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistentException {
        AuthService authService = AuthServiceFactory.getAuthService();
        ResponseEntity responseEntity = new ResponseEntity();

        Optional<Integer> optionalUserId = RequestService.getInstance().getIntParameter(request, "userId");
        if (!optionalUserId.isPresent() && !authService.isAuthenticated()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        UserService userService = (UserService) serviceFactory.createService(ServiceType.USER);
        UserParametersDto userParametersDto;
        if (optionalUserId.isPresent()) {
            userParametersDto = userService.getUserParameters(optionalUserId.get());
        } else if (authService.isAuthenticated()) {
            userParametersDto = userService.getUserParameters(authService.getEmail());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if (userParametersDto == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        if (authService.isAuthenticated()
                && authService.getEmail().equals(userParametersDto.getUser().getEmail())) {
            responseEntity.setAttribute("isOwner", true);
        }

        responseEntity.setAttribute("userParameters", userParametersDto);
        log.debug(userParametersDto);
        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("user.profile"));
        return responseEntity;
    }
}
