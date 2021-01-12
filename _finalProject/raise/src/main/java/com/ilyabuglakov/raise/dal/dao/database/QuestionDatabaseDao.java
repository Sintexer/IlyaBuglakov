package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.QuestionDao;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.EntityColumns;
import com.ilyabuglakov.raise.domain.structure.columns.QuestionColumns;
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
 * QuestionDao is the Dao implementation specifically for Question class
 * Based on DatabaseDao abstract class.
 * TODO add Test extraction in Question service
 */
public class QuestionDatabaseDao extends DatabaseDao implements QuestionDao {

    public QuestionDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Question question) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.QUESTION.name());
        sqlQueryBuilder.addField(QuestionColumns.CONTENT.name(), question.getContent());
        sqlQueryBuilder.addField(QuestionColumns.TEST_ID.name(), question.getTest().getId());
        String insertQuery = sqlQueryBuilder.build();

        executeUpdateQeury(insertQuery);
    }

    @Override
    public Optional<Question> read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.QUESTION.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<Question> question = buildQuestion(resultSet);
        closeResultSet(resultSet);
        return question;
    }

    @Override
    public void update(Question question) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder(Tables.QUESTION.name());
        sqlQueryBuilder.addField(EntityColumns.ID.name(), question.getId());
        sqlQueryBuilder.addField(QuestionColumns.CONTENT.name(), question.getContent());
        sqlQueryBuilder.addField(QuestionColumns.TEST_ID.name(), question.getTest().getId());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), question.getId());
        String updateQuery = sqlQueryBuilder.build();

        executeUpdateQeury(updateQuery);
    }

    @Override
    public void delete(Question question) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder(Tables.QUESTION.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), question.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeUpdateQeury(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * Will build Optional-Question only if resultSet has values of all user fields,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input resultSet parameters, taken from sql query execution
     * @return Question from resultSet
     */
    private Optional<Question> buildQuestion(ResultSet resultSet) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if(validator.hasAllValues(resultSet,
                    QuestionColumns.CONTENT.name(),
                    EntityColumns.ID.name())) {
                Question question = Question.builder()
                        .content(resultSet.getString(QuestionColumns.CONTENT.name()))
                        .build();
                question.setId(resultSet.getLong(EntityColumns.ID.name()));
                return Optional.of(question);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
