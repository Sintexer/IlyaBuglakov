package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.model.response.ResponseEntity;

public interface UserAccessValidationService {
    ResponseEntity isAllowedToCreateTest(String email) throws DaoOperationException;
}
