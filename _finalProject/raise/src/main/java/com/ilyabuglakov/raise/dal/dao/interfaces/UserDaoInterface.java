package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.User;

import java.util.List;

public interface UserDaoInterface extends Dao<User> {
    List<User> readAll() throws DaoOperationException;
}
