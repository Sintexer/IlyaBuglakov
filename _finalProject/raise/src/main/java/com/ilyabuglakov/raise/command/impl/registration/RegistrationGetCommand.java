package com.ilyabuglakov.raise.command.impl.registration;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.model.FormConstants;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.auth.AuthServiceFactory;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class RegistrationGetCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info(() -> "Entered registration command");
        ResponseEntity responseEntity = new ResponseEntity();
        if(AuthServiceFactory.getAuthService().isAuthenticated()){
            responseEntity.setRedirect(true);
            responseEntity.setLink(PropertiesStorage.getInstance().getLinks().getProperty("root"));
        } else {
            responseEntity.getAttributes().put("emailLength", FormConstants.EMAIL_LENGTH.getValue());
            responseEntity.getAttributes().put("nameLength", FormConstants.NAME_LENGTH.getValue());
            responseEntity.getAttributes().put("surnameLength", FormConstants.SURNAME_LENGTH.getValue());
            responseEntity.getAttributes().put("passwordMin", FormConstants.PASSWORD_MIN.getValue());
            responseEntity.getAttributes().put("passwordMax", FormConstants.PASSWORD_MAX.getValue());
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("registration"));
        }

        return responseEntity;
    }

}
