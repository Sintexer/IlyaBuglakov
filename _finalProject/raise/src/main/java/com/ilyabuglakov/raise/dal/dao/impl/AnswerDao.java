package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDaoInterface;
import com.ilyabuglakov.raise.domain.Answer;
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
 * AnswerDao is the Dao implementation specifically for Answer class
 */
public class AnswerDao extends BaseDao implements AnswerDaoInterface {

    public AnswerDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Answer answer) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("answer");
        sqlQueryBuilder.addField("content", answer.getContent());
        sqlQueryBuilder.addField("correct", answer.isCorrect());
        sqlQueryBuilder.addField("question_id", answer.getQuestion().getId());
        String insertQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(insertQuery);
    }

    @Override
    public Optional<Answer> read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("answer");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<Answer> answer = buildAnswer(resultSet);
        closeResultSet(resultSet);
        return answer;
    }

    @Override
    public void update(Answer answer) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder("answer");
        sqlQueryBuilder.addField("id", answer.getId());
        sqlQueryBuilder.addField("content", answer.getContent());
        sqlQueryBuilder.addField("question_id", answer.getQuestion().getId());
        sqlQueryBuilder.addField("correct", answer.isCorrect());
        String updateQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(updateQuery);
    }

    @Override
    public void delete(Answer answer) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder("answer");
        sqlQueryBuilder.addWhere("id", answer.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(deleteQuery);
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
            if(validator.hasAllValues(resultSet, "content", "correct", "id")) {
                Answer answer = Answer.builder()
                        .content(resultSet.getString("content"))
                        .correct(Boolean.parseBoolean(resultSet.getString("correct")))
                        .build();
                answer.setId(resultSet.getLong("id"));
                return Optional.of(answer);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
