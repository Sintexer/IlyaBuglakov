package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDao;
import com.ilyabuglakov.raise.domain.Answer;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.AnswerColumns;
import com.ilyabuglakov.raise.domain.structure.columns.EntityColumns;
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
 * AnswerDao is the Dao implementation specifically for Answer class.
 * Based on DatabaseDao abstract class.
 * TODO add Question extraction in Answer service
 */
public class AnswerDatabaseDao extends DatabaseDao implements AnswerDao {

    public AnswerDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Answer answer) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.ANSWER.name());
        sqlQueryBuilder.addField(AnswerColumns.CONTENT.name(), answer.getContent());
        sqlQueryBuilder.addField(AnswerColumns.CORRECT.name(), answer.isCorrect());
        sqlQueryBuilder.addField(AnswerColumns.QUESTION_ID.name(), answer.getQuestion().getId());
        String insertQuery = sqlQueryBuilder.build();

        executeUpdateQeury(insertQuery);
    }

    @Override
    public Optional<Answer> read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.ANSWER.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<Answer> answer = buildAnswer(resultSet);
        closeResultSet(resultSet);
        return answer;
    }

    @Override
    public void update(Answer answer) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder(Tables.ANSWER.name());
        sqlQueryBuilder.addField(EntityColumns.ID.name(), answer.getId());
        sqlQueryBuilder.addField(AnswerColumns.CONTENT.name(), answer.getContent());
        sqlQueryBuilder.addField(AnswerColumns.QUESTION_ID.name(), answer.getQuestion().getId());
        sqlQueryBuilder.addField(AnswerColumns.CORRECT.name(), answer.isCorrect());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), answer.getId());
        String updateQuery = sqlQueryBuilder.build();

        executeUpdateQeury(updateQuery);
    }

    @Override
    public void delete(Answer answer) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder(Tables.ANSWER.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), answer.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeUpdateQeury(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * Will build Optional-Answer only if resultSet has values of all user fields,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input result set parameters, taken from sql query execution
     * @return Answer from resultSet
     */
    private Optional<Answer> buildAnswer(ResultSet resultSet) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if(validator.hasAllValues(resultSet,
                    AnswerColumns.CONTENT.name(),
                    AnswerColumns.CORRECT.name(),
                    EntityColumns.ID.name())) {
                Answer answer = Answer.builder()
                        .content(resultSet.getString(AnswerColumns.CONTENT.name()))
                        .correct(Boolean.parseBoolean(resultSet.getString(AnswerColumns.CORRECT.name())))
                        .build();
                answer.setId(resultSet.getLong(EntityColumns.ID.name()));
                return Optional.of(answer);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
