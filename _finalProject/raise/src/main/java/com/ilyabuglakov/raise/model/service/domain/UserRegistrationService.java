package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.response.ResponseEntity;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

public interface UserRegistrationService {

    ResponseEntity registerUser(User user) throws DaoOperationException, MessagingException;

    boolean tryConfirm(String key) throws DaoOperationException;

}
