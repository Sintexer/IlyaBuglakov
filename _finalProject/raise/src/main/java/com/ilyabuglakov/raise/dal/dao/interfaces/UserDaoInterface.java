package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDaoInterface extends Dao<User> {
    List<User> readAll() throws DaoOperationException;
    Optional<User> findByEmail(String email) throws DaoOperationException;
    Optional<Long> getUserId(String email) throws SQLException, DaoOperationException;
}
