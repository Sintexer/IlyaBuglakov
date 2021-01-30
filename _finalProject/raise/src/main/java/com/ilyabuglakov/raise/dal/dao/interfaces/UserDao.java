package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.UsrKey;
import com.ilyabuglakov.raise.domain.type.Status;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findByEmail(String email) throws DaoOperationException;

    Optional<User> findUserInfo(Integer id) throws DaoOperationException;

    void createKey(String key, Integer userId, LocalDateTime timestamp) throws DaoOperationException;

    Optional<UsrKey> findKey(String key) throws DaoOperationException;

    void updateStatus(Integer id, Status status) throws DaoOperationException;

    void deleteKey(String key) throws DaoOperationException;
}
