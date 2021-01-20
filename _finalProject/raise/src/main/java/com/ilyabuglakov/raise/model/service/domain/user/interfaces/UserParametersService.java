package com.ilyabuglakov.raise.model.service.domain.user.interfaces;

import com.ilyabuglakov.raise.domain.UserTestResult;
import com.ilyabuglakov.raise.model.service.domain.user.exception.UserParametersServiceException;

public interface UserParametersService {
    void saveResult(UserTestResult userTestResult) throws UserParametersServiceException;
}
