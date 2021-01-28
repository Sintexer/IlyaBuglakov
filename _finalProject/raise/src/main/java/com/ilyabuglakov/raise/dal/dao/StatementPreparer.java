package com.ilyabuglakov.raise.dal.dao;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@FunctionalInterface
public interface StatementPreparer {
    PreparedStatement prepareStatement() throws SQLException, DaoOperationException;
}
