package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Role;

import java.sql.SQLException;
import java.util.Set;

public interface RoleDao extends Dao<Role> {
    Set<String> getPermissions(Integer id) throws DaoOperationException;
    Set<String> getPermissions(String name) throws DaoOperationException, SQLException;
    Set<String> getRoleNames(Integer userId) throws SQLException, DaoOperationException;
}
