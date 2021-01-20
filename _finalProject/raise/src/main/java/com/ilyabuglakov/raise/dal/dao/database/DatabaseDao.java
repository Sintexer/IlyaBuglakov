package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.TestColumns;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlSelectBuilder;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Log4j2
public abstract class DatabaseDao {

    protected Connection connection;

    protected DatabaseDao(Connection connection) {
        this.connection = connection;
    }

    protected Integer getCount(ResultSet resultSet) throws DaoOperationException {

        Optional<ResultSet> rs = unpackResultSet(resultSet);
        Integer count = 0;
        if (rs.isPresent()) {
            try {
                count = rs.get().getInt("count");
            } catch (SQLException e) {
                throw new DaoOperationException("Can't get row count", e);
            } finally {
                closeResultSet(rs.get());
            }
        }

        return count;
    }

    protected Integer executeReturnId(String query) throws DaoOperationException{
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next())
                return resultSet.getInt(1);
            else
                throw new DaoOperationException();
        } catch (SQLException e) {
            throw new DaoOperationException(e);
        }finally {
            closeResultSet(resultSet);
        }
    }

    protected void executeUpdateQuery(String query) throws DaoOperationException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DaoOperationException("Bad update query: " + query, e);
        } finally {
            closeStatement(statement);
        }
    }

    protected ResultSet createResultSet(String query, int... statementParameters) throws DaoOperationException {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query, statementParameters);
            statement.closeOnCompletion();
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
//                closeStatement(resultSet.getStatement());
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
