package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.EntityColumns;
import com.ilyabuglakov.raise.domain.structure.columns.TestColumns;
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
 * Based on DatabaseDao abstract class.
 */
public class TestDatabaseDao extends DatabaseDao implements TestDao {

    public TestDatabaseDao(Connection connection) {
        super(connection);
    }

//    @Override
//    public Optional<Integer> getTestId(String testName) throws DaoOperationException {
//        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
//        sqlQueryBuilder.addField(EntityColumns.ID.name());
//        sqlQueryBuilder.addWhere(TestColumns.TEST_NAME.name(), testName);
//        String selectQuery = sqlQueryBuilder.build();
//
//        ResultSet resultSet = createResultSet(selectQuery);
//        Optional<Integer> id = Optional.empty();
//        try {
//            if(resultSet.next()) {
//                id = Optional.ofNullable(resultSet.getInt(EntityColumns.ID.name()));
//                if (resultSet.wasNull())
//                    id = Optional.empty();
//            }
//        } catch (SQLException e) {
//            throw new DaoOperationException("Error while reading index", e);
//        } finally {
//            closeResultSet(resultSet);
//        }
//        return id;
//    }

    @Override
    public Integer create(Test test) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.TEST.name());
        sqlQueryBuilder.addField(TestColumns.TEST_NAME.name(), test.getTestName());
        sqlQueryBuilder.addField(TestColumns.DIFFICULTY.name(), test.getDifficulty());
        String insertQuery = sqlQueryBuilder.build();

        return executeReturnId(insertQuery);
    }

    @Override
    public Optional<Test> read(Integer id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<Test> test = buildTest(resultSet);
        closeResultSet(resultSet);
        return test;
    }

    @Override
    public void update(Test test) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder(Tables.TEST.name());
        sqlQueryBuilder.addField(EntityColumns.ID.name(), test.getId());
        sqlQueryBuilder.addField(TestColumns.TEST_NAME.name(), test.getTestName());
        sqlQueryBuilder.addField(TestColumns.DIFFICULTY.name(), test.getDifficulty());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), test.getId());
        String updateQuery = sqlQueryBuilder.build();

        executeUpdateQuery(updateQuery);
    }

    @Override
    public void delete(Test test) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder(Tables.TEST.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), test.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeUpdateQuery(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * Will build Optional-Test only if resultSet has values of all test fields,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input resultSet parameters, taken from sql query execution
     * @return Test from resultSet
     */
    private Optional<Test> buildTest(ResultSet resultSet) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if(validator.hasAllValues(resultSet, TestColumns.TEST_NAME.name(),
                    TestColumns.TEST_NAME.name(),
                    EntityColumns.ID.name())) {
                Test test = Test.builder()
                        .testName(resultSet.getString(TestColumns.TEST_NAME.name()))
                        .difficulty(Integer.parseInt(resultSet.getString(TestColumns.DIFFICULTY.name())))
                        .build();
                test.setId(resultSet.getInt(EntityColumns.ID.name()));
                return Optional.of(test);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
