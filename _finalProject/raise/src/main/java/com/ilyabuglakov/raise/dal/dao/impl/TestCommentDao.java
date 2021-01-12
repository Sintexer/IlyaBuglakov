package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestCommentDaoInterface;
import com.ilyabuglakov.raise.domain.TestComment;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlUpdateBuilder;
import com.ilyabuglakov.raise.model.service.validator.ResultSetValidator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class TestCommentDao extends BaseDao implements TestCommentDaoInterface {

    public TestCommentDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(TestComment testComment) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("test_comment");
        sqlQueryBuilder.addField("user_id", testComment.getUser().getId());
        sqlQueryBuilder.addField("test_id", testComment.getTest().getId());
        sqlQueryBuilder.addField("timestamp", testComment.getTimestamp());
        sqlQueryBuilder.addField("content", testComment.getContent());
        String insertQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(insertQuery);
    }

    @Override
    public Optional<TestComment> read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("test_comment");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<TestComment> testComment = buildTestComment(resultSet);
        closeResultSet(resultSet);
        return testComment;
    }

    @Override
    public void update(TestComment testComment) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder("test_comment");
        sqlQueryBuilder.addField("user_id", testComment.getUser().getId());
        sqlQueryBuilder.addField("test_id", testComment.getTest().getId());
        sqlQueryBuilder.addField("timestamp", testComment.getTimestamp());
        sqlQueryBuilder.addField("content", testComment.getContent());
        String updateQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(updateQuery);
    }

    @Override
    public void delete(TestComment testComment) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder("test_comment");
        sqlQueryBuilder.addWhere("id", testComment.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * Will build Optional-TestComment only if resultSet has values of all user fields,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input resultSet parameters, taken from sql query execution
     * @return TestComment from resultSet
     */
    private Optional<TestComment> buildTestComment(ResultSet resultSet) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if(validator.hasAllValues(resultSet, "timestamp", "content", "id")) {
                TestComment testComment = TestComment.builder()
                        .timestamp(LocalDateTime.parse(resultSet.getString("timestamp")))
                        .content(resultSet.getString("content"))
                        .build();
                testComment.setId(resultSet.getLong("id"));
                return Optional.of(testComment);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
