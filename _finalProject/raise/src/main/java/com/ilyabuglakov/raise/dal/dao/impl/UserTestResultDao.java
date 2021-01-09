package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserTestResultDaoInterface;
import com.ilyabuglakov.raise.domain.UserTestResult;
import com.ilyabuglakov.raise.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlUpdateBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserTestResultDao is the Dao implementation specifically for UserTestResult class
 */
public class UserTestResultDao extends BaseDao implements UserTestResultDaoInterface {
    @Override
    public void create(UserTestResult entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("user_test_result");
        sqlQueryBuilder.addField("user_id", entity.getUser().getId());
        sqlQueryBuilder.addField("test_id", entity.getTest().getId());
        sqlQueryBuilder.addField("result", entity.getResult());
        String insertQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(insertQuery);
    }

    @Override
    public UserTestResult read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("user_test_result");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        UserTestResult userTestResult = buildUser(resultSet);
        closeResultSet(resultSet);
        return userTestResult;
    }

    @Override
    public void update(UserTestResult entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder("user_test_result");
        sqlQueryBuilder.addField("result", entity.getResult());
        sqlQueryBuilder.addField("user_id", entity.getUser().getId());
        sqlQueryBuilder.addField("test_id", entity.getTest().getId());
        sqlQueryBuilder.addWhere("id", entity.getId());
        String updateQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(updateQuery);
    }

    @Override
    public void delete(UserTestResult entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder("user_test_result");
        sqlQueryBuilder.addWhere("id", entity.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * @param resultSet input result set parameters, taken from sql query execution
     * @return UserTestResult from resultSet
     */
    private UserTestResult buildUser(ResultSet resultSet) throws DaoOperationException {
        try {
            UserTestResult userTestResult = UserTestResult.builder()
                    .result(resultSet.getInt("result"))
                    .build();
            userTestResult.setId(resultSet.getLong("id"));
            return userTestResult;
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
