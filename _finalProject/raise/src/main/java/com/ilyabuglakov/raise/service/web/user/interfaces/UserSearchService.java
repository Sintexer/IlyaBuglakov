package com.ilyabuglakov.raise.service.web.user.interfaces;

import com.ilyabuglakov.raise.dal.transaction.exception.TransactionException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.service.web.user.exception.UserSearchException;

import java.util.Optional;

public interface UserSearchService {

    public Optional<User> findByEmail(String email) throws UserSearchException;

}
