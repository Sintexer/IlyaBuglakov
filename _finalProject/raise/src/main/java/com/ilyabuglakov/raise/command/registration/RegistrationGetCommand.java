package com.ilyabuglakov.raise.command.registration;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.model.FormConstants;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class RegistrationGetCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("Entered registration command");


        specifyFormParameters(request,
                FormConstants.EMAIL_LENGTH.getValue(), FormConstants.NAME_LENGTH.getValue(),
                FormConstants.SURNAME_LENGTH.getValue(), FormConstants.PASSWORD_MIN.getValue(),
                FormConstants.PASSWORD_MAX.getValue());

        request.getRequestDispatcher(PropertiesStorage.getInstance().getPages().getProperty("registration"))
                .forward(request, response);
    }

    private void specifyFormParameters(HttpServletRequest request,
                                       int emailLength,
                                       int nameLength, int surnameLength,
                                       int passwordMin, int passwordMax){
        request.setAttribute("emailLength", emailLength);
        request.setAttribute("nameLength", nameLength);
        request.setAttribute("surnameLength", surnameLength);
        request.setAttribute("passwordMin", passwordMin);
        request.setAttribute("passwordMax", passwordMax);
    }

}
