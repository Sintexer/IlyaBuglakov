package com.ilyabuglakov.raise.command.impl.rest;


import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.ServiceType;
import com.ilyabuglakov.raise.model.service.domain.TestService;
import com.ilyabuglakov.raise.model.service.servlet.RequestService;
import com.ilyabuglakov.raise.model.service.servlet.exception.IllegalRequestParameterException;
import com.ilyabuglakov.raise.storage.PropertiesStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class ConfirmTestCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, CommandException, PersistentException {

        ResponseEntity responseEntity = new ResponseEntity();

        Optional<Integer> optionalTestId = Optional.empty();
        try {
            optionalTestId = RequestService.getInstance().getIntParameter(request, "testId");
        } catch (IllegalRequestParameterException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        if(!optionalTestId.isPresent()){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        TestService testService = (TestService) serviceFactory.createService(ServiceType.TEST);
        testService.changeTestStatus(optionalTestId.get(), TestStatus.CONFIRMED);
        responseEntity.setLink(PropertiesStorage.getInstance().getLinks().getProperty("admin.test.catalog"));
        responseEntity.setRedirect(true);
        return responseEntity;
    }
}
