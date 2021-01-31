package com.ilyabuglakov.raise.command.impl.registration;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.UserRegistrationService;
import com.ilyabuglakov.raise.model.service.request.extractor.UserExtractor;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Any command, tht use transaction from request attributes should rollback transaction
 * before throwing an exception.
 */
@Log4j2
public class RegistrationPostCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DaoOperationException {
        log.debug(() -> "registration from posted");

        User user = new UserExtractor().extractFrom(request);

        UserRegistrationService userRegistrationService =
                (UserRegistrationService) serviceFactory.createService(ServiceType.USER_REGISTRATION);

        ResponseEntity responseEntity = null;
        try {
            responseEntity = userRegistrationService.registerUser(user);
        } catch (MessagingException e) {
            response.sendError(500);
            return null;
        }

        if (!responseEntity.isErrorOccurred()) {
            responseEntity.setRedirect(true);
            responseEntity.setLink(PropertiesStorage.getInstance().getLinks().getProperty("auth.confirm.email"));
        } else{
            responseEntity.setAttribute("emailPrevVal", user.getEmail());
            responseEntity.setAttribute("namePrevVal", user.getName());
            responseEntity.setAttribute("surnamePrevVal", user.getSurname());
            responseEntity.setAttribute("registrationFailed", true);
            responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("registration"));
        }
        return responseEntity;
    }
}
