package com.ilyabuglakov.raise.service.web.user.interfaces;

import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.service.web.user.exception.UserSearchServiceException;

import java.util.Optional;

public interface UserSearchService {

    public Optional<User> findByEmail(String email) throws UserSearchServiceException;

}
