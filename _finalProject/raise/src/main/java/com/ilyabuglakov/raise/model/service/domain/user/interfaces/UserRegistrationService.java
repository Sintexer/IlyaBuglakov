package com.ilyabuglakov.raise.model.service.domain.user.interfaces;

import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.service.domain.user.exception.UserRegistrationServiceException;

public interface UserRegistrationService {

    public void save(User user) throws UserRegistrationServiceException;

}
