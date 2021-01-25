package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Role;
import com.ilyabuglakov.raise.domain.type.UserRole;

import java.util.Set;

public interface RoleDao extends Dao<Role> {
    Set<String> getPermissions(Integer id) throws DaoOperationException;

    Set<UserRole> getUserRoles(Integer userId) throws DaoOperationException;

    void createUserRoles(Integer userId, Set<UserRole> userRoles) throws DaoOperationException;
}
