package com.ilyabuglakov.raise.service.web.user.interfaces;

import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.service.web.user.exception.UserRegistrationServiceException;

public interface UserRegistrationService {

    public void save(User user) throws UserRegistrationServiceException;

}
