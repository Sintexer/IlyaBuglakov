package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.response.ResponseEntity;

public interface UserRegistrationService {

    ResponseEntity registerUser(User user) throws DaoOperationException;

}
