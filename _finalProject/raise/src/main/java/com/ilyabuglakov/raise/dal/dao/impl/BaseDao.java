package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Log4j2
public abstract class BaseDao {

    protected Connection connection;

    protected BaseDao(Connection connection) {
        this.connection = connection;
    }

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

    protected ResultSet createResultSet(String query, int... statementParameters) throws DaoOperationException {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query, statementParameters);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            return resultSet;
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw new DaoOperationException(e);
        }
    }

    protected Optional<ResultSet> unpackResultSet(ResultSet resultSet) throws DaoOperationException {
        try {
            if(resultSet.next())
                return Optional.of(resultSet);
        } catch (SQLException e) {
            throw new DaoOperationException("Can't access ResultSet", e);
        }
        return Optional.empty();
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
                closeStatement(resultSet.getStatement());
                resultSet.close();
            } catch (SQLException e) {
                log.error("Exception while closing result set", e);
            }
        }
    }

    protected DaoOperationException createBadResultSetException(Exception parent) {
        return new DaoOperationException("Bad result set after executing query. Some fields aren't present", parent);
    }

}
