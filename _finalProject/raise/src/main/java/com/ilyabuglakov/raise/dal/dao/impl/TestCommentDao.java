package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestCommentDaoInterface;
import com.ilyabuglakov.raise.domain.TestComment;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.EntityColumns;
import com.ilyabuglakov.raise.domain.structure.columns.TestCommentColumns;
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
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.TEST_COMMENT.name());
        sqlQueryBuilder.addField(TestCommentColumns.USER_ID.name(), testComment.getUser().getId());
        sqlQueryBuilder.addField(TestCommentColumns.TEST_ID.name(), testComment.getTest().getId());
        sqlQueryBuilder.addField(TestCommentColumns.TIMESTAMP.name(), testComment.getTimestamp());
        sqlQueryBuilder.addField(TestCommentColumns.CONTENT.name(), testComment.getContent());
        String insertQuery = sqlQueryBuilder.build();

        executeUpdateQeuery(insertQuery);
    }

    @Override
    public Optional<TestComment> read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST_COMMENT.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<TestComment> testComment = buildTestComment(resultSet);
        closeResultSet(resultSet);
        return testComment;
    }

    @Override
    public void update(TestComment testComment) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder(Tables.TEST_COMMENT.name());
        sqlQueryBuilder.addField(TestCommentColumns.USER_ID.name(), testComment.getUser().getId());
        sqlQueryBuilder.addField(TestCommentColumns.TEST_ID.name(), testComment.getTest().getId());
        sqlQueryBuilder.addField(TestCommentColumns.TIMESTAMP.name(), testComment.getTimestamp());
        sqlQueryBuilder.addField(TestCommentColumns.CONTENT.name(), testComment.getContent());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), testComment.getId());
        String updateQuery = sqlQueryBuilder.build();

        executeUpdateQeuery(updateQuery);
    }

    @Override
    public void delete(TestComment testComment) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder(Tables.TEST_COMMENT.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), testComment.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeUpdateQeuery(deleteQuery);
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
            if(validator.hasAllValues(resultSet,
                    TestCommentColumns.CONTENT.name(),
                    TestCommentColumns.TIMESTAMP.name(),
                    EntityColumns.ID.name())) {
                TestComment testComment = TestComment.builder()
                        .content(resultSet.getString(TestCommentColumns.CONTENT.name()))
                        .timestamp(LocalDateTime.parse(resultSet.getString(TestCommentColumns.TIMESTAMP.name())))
                        .build();
                testComment.setId(resultSet.getLong(EntityColumns.ID.name()));
                return Optional.of(testComment);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
