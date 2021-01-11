package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDaoInterface;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlUpdateBuilder;
import com.ilyabuglakov.raise.model.service.validator.ResultSetValidator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * TestDao is the Dao implementation specifically for Test class
 */
public class TestDao extends BaseDao implements TestDaoInterface {

    public TestDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Test test) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("test");
        sqlQueryBuilder.addField("id", test.getId());
        sqlQueryBuilder.addField("test_name", test.getTestName());
        sqlQueryBuilder.addField("difficulty", test.getDifficulty());
        String insertQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(insertQuery);
    }

    @Override
    public Optional<Test> read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("test");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<Test> test = buildTest(resultSet);
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
     * Will build Optional-User only if result set has all user fields values,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input result set parameters, taken from sql query execution
     * @return Test from resultSet
     */
    private Optional<Test> buildTest(ResultSet resultSet) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if(validator.hasAllValues(resultSet, "test_name", "difficulty", "id")) {
                Test test = Test.builder()
                        .testName(resultSet.getString("test_name"))
                        .difficulty(Integer.parseInt(resultSet.getString("difficulty")))
                        .build();
                test.setId(resultSet.getLong("id"));
                return Optional.of(test);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
