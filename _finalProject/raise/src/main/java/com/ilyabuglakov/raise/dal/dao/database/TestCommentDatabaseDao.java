package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestCommentDao;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.TestComment;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.EntityColumns;
import com.ilyabuglakov.raise.domain.structure.columns.TestColumns;
import com.ilyabuglakov.raise.domain.structure.columns.TestCommentColumns;
import com.ilyabuglakov.raise.domain.type.TestStatus;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TestCommentDatabaseDao is the Dao implementation specifically for TestComment class
 * Based on DatabaseDao abstract class.
 */
public class TestCommentDatabaseDao extends DatabaseDao implements TestCommentDao {

    public TestCommentDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public Integer getCommentsAmount(Integer testId) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST_COMMENT.name());
        sqlQueryBuilder.addWhere(TestCommentColumns.TEST_ID.name(), testId);
        sqlQueryBuilder.returnCount();
        String query = sqlQueryBuilder.build();

        return getCount(createResultSet(query));
    }

    @Override
    public List<TestComment> getComments(Integer testId, int offset, int items) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST_COMMENT.name());
        sqlQueryBuilder.addWhere(TestCommentColumns.TEST_ID.name(), testId);
        sqlQueryBuilder.addLimit(offset, items);
        String query = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(query);
        List<Optional<TestComment>> testComments = new ArrayList<>();

        try {
            while (resultSet.next()) {
                testComments.add(buildTestComment(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoOperationException("Error while reading tests from resultSet", e);
        }
        closeResultSet(resultSet);
        return testComments.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Integer create(TestComment testComment) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.TEST_COMMENT.name());
        sqlQueryBuilder.addField(TestCommentColumns.USER_ID.name(), testComment.getUser().getId());
        sqlQueryBuilder.addField(TestCommentColumns.TEST_ID.name(), testComment.getTest().getId());
        sqlQueryBuilder.addField(TestCommentColumns.TIMESTAMP.name(), testComment.getTimestamp());
        sqlQueryBuilder.addField(TestCommentColumns.CONTENT.name(), testComment.getContent());
        String insertQuery = sqlQueryBuilder.build();

        return executeReturnId(insertQuery);
    }

    @Override
    public Optional<TestComment> read(Integer id) throws DaoOperationException {
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

        executeUpdateQuery(updateQuery);
    }

    @Override
    public void delete(TestComment testComment) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder(Tables.TEST_COMMENT.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), testComment.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeUpdateQuery(deleteQuery);
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
                    TestCommentColumns.USER_ID.name(),
                    TestCommentColumns.TEST_ID.name(),
                    TestCommentColumns.TIMESTAMP.name(),
                    EntityColumns.ID.name())) {
                TestComment testComment = TestComment.builder()
                        .content(resultSet.getString(TestCommentColumns.CONTENT.name()))
                        .user(User.builder().id(resultSet.getInt(TestCommentColumns.USER_ID.name())).build())
                        .test(Test.builder().id(resultSet.getInt(TestCommentColumns.TEST_ID.name())).build())
                        .timestamp(resultSet.getTimestamp(TestCommentColumns.TIMESTAMP.name()).toLocalDateTime())
                        .build();
                testComment.setId(resultSet.getInt(EntityColumns.ID.name()));
                return Optional.of(testComment);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
