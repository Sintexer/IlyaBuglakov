package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.Dao;
import com.ilyabuglakov.raise.domain.Entity;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Role;
import com.ilyabuglakov.raise.domain.type.Status;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.enterprise.inject.spi.Producer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.function.Function;

@Log4j2
public abstract class BaseDao {

    @Setter
    protected Connection connection;

    protected void executeUpdateQuery(String updateQuery) throws DaoOperationException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(updateQuery);

        } catch (SQLException e) {
            throw new DaoOperationException("Bad update query: " + updateQuery, e);
        } finally {
            closeStatement(statement);
        }
    }

    protected void executeQueryWithoutResult(String query) throws DaoOperationException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new DaoOperationException("Bad query: " + query, e);
        } finally {
            closeStatement(statement);
        }
    }

    protected ResultSet createResultSet(String query, int...statementParameters) throws DaoOperationException {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, statementParameters);
            resultSet = statement.getResultSet();
            if (resultSet.next())
                return resultSet;
            else
                throw new DaoOperationException("Index wasn't returned after " + query + " operation");
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw new DaoOperationException(e);
        } finally {
            closeStatement(statement);
        }
    }

    protected void closeStatement(Statement statement) {
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Exception while closing statement", e);
            }
    }

    protected void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Exception while closing result set", e);
            }
        }
    }

    protected DaoOperationException createBadResultSetException(Exception parent){
        return new DaoOperationException("Bad result set after executing query. Some fields aren't present", parent);
    }

}
