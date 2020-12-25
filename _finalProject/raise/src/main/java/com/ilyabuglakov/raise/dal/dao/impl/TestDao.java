package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.Dao;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDaoInterface;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlUpdateBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TestDao is the Dao implementation specifically for Test class
 */
public class TestDao extends BaseDao implements TestDaoInterface {
    @Override
    public long create(Test test) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("test");
        sqlQueryBuilder.addField("id", test.getId());
        sqlQueryBuilder.addField("test_name", test.getTestName());
        sqlQueryBuilder.addField("difficulty", test.getDifficulty());
        String insertQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(insertQuery, Statement.RETURN_GENERATED_KEYS);
        try {
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DaoOperationException("Index wasn't found in INSERT result set");
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Test read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("test");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Test test = buildTest(resultSet);
        closeResultSet(resultSet);
        return test;
    }

    @Override
    public void update(Test test) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder("test");
        sqlQueryBuilder.addField("id", test.getId());
        sqlQueryBuilder.addField("test_name", test.getTestName());
        sqlQueryBuilder.addField("difficulty", test.getDifficulty());
        String updateQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(updateQuery);
    }

    @Override
    public void delete(Test test) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder("test");
        sqlQueryBuilder.addWhere("id", test.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * @param resultSet input result set parameters, taken from sql query execution
     * @return Test from resultSet
     */
    private Test buildTest(ResultSet resultSet) throws DaoOperationException {
        try {
            Test test = Test.builder()
                    .testName(resultSet.getString("test_name"))
                    .difficulty(Integer.parseInt(resultSet.getString("difficulty")))
                    .build();
            test.setId(resultSet.getLong("id"));
            return test;
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
