package com.ilyabuglakov.raise.model.service.domain.utils.user.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.User;

import java.util.Optional;

public interface UserSearchService {

    public Optional<User> findByEmail(String email) throws DaoOperationException;

    public Optional<User> findById(Integer userId) throws DaoOperationException;

}
