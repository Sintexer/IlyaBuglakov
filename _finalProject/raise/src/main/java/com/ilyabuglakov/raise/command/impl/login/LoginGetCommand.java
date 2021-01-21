package com.ilyabuglakov.raise.command.impl.login;

import com.ilyabuglakov.raise.command.Command;
import com.ilyabuglakov.raise.command.exception.CommandException;
import com.ilyabuglakov.raise.model.FormConstants;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.storage.PropertiesStorage;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class LoginGetCommand extends Command {
    @Override
    public ResponseEntity execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        log.info("Entered login command");

        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.getAttributes().put("emailLength", FormConstants.EMAIL_LENGTH.getValue());
        responseEntity.getAttributes().put("passwordMax", FormConstants.PASSWORD_MAX.getValue());
        responseEntity.setLink(PropertiesStorage.getInstance().getPages().getProperty("login"));

        return responseEntity;
    }

}