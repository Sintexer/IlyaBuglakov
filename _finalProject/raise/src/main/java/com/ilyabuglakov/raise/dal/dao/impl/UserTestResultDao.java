package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserTestResultDaoInterface;
import com.ilyabuglakov.raise.domain.UserTestResult;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.EntityColumns;
import com.ilyabuglakov.raise.domain.structure.columns.UserTestResultColumns;
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
 * UserTestResultDao is the Dao implementation specifically for UserTestResult class
 * TODO add User and Test extraction in UserTestResult service
 */
public class UserTestResultDao extends BaseDao implements UserTestResultDaoInterface {

    public UserTestResultDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(UserTestResult entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.USER_TEST_RESULT.name());
        sqlQueryBuilder.addField(UserTestResultColumns.USER_ID.name(), entity.getUser().getId());
        sqlQueryBuilder.addField(UserTestResultColumns.TEST_ID.name(), entity.getTest().getId());
        sqlQueryBuilder.addField(UserTestResultColumns.RESULT.name(), entity.getResult());
        String insertQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(insertQuery);
    }

    @Override
    public Optional<UserTestResult> read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.USER_TEST_RESULT.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<UserTestResult> userTestResult = buildUserTestResult(resultSet);
        closeResultSet(resultSet);
        return userTestResult;
    }

    @Override
    public void update(UserTestResult entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder(Tables.USER_TEST_RESULT.name());
        sqlQueryBuilder.addField(UserTestResultColumns.USER_ID.name(), entity.getUser().getId());
        sqlQueryBuilder.addField(UserTestResultColumns.TEST_ID.name(), entity.getTest().getId());
        sqlQueryBuilder.addField(UserTestResultColumns.RESULT.name(), entity.getResult());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), entity.getId());
        String updateQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(updateQuery);
    }

    @Override
    public void delete(UserTestResult entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder(Tables.USER_TEST_RESULT.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), entity.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * Will build Optional-UserTestResult only if resultSet has values of all UserTestResult fields,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input resultSet parameters, taken from sql query execution
     * @return Optional UserTestResult from resultSet
     */
    private Optional<UserTestResult> buildUserTestResult(ResultSet resultSet) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if(validator.hasAllValues(resultSet,
                    UserTestResultColumns.RESULT.name(),
                    EntityColumns.ID.name())) {
                UserTestResult userTestResult = UserTestResult.builder()
                        .result(resultSet.getInt(UserTestResultColumns.RESULT.name()))
                        .build();
                userTestResult.setId(resultSet.getLong(EntityColumns.ID.name()));
                return Optional.of(userTestResult);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
