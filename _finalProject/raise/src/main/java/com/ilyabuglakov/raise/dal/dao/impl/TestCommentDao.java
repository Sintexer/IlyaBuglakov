package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.Dao;
import com.ilyabuglakov.raise.domain.TestComment;
import com.ilyabuglakov.raise.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlUpdateBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class TestCommentDao extends BaseDao implements Dao<TestComment> {

    @Override
    public long create(TestComment testComment) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("test_comment");
        sqlQueryBuilder.addField("user_id", testComment.getUser().getId());
        sqlQueryBuilder.addField("test_id", testComment.getTest().getId());
        sqlQueryBuilder.addField("timestamp", testComment.getTimestamp());
        sqlQueryBuilder.addField("content", testComment.getContent());
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
    public TestComment read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("test_comment");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        TestComment testComment = buildTestComment(resultSet);
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
     * @param resultSet input result set parameters, taken from sql query execution
     * @return TestComment from resultSet
     */
    private TestComment buildTestComment(ResultSet resultSet) throws DaoOperationException {
        try {
            TestComment testComment = TestComment.builder()
                    .timestamp(LocalDateTime.parse(resultSet.getString("timestamp")))
                    .content(resultSet.getString("content"))
                    .build();
            testComment.setId(resultSet.getLong("id"));
            return testComment;
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
