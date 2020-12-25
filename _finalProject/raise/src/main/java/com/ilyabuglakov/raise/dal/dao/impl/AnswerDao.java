package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDaoInterface;
import com.ilyabuglakov.raise.dal.dao.interfaces.Dao;
import com.ilyabuglakov.raise.domain.Answer;
import com.ilyabuglakov.raise.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlUpdateBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * AnswerDao is the Dao implementation specifically for Answer class
 */
public class AnswerDao extends BaseDao implements AnswerDaoInterface {
    @Override
    public long create(Answer answer) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("answer");
        sqlQueryBuilder.addField("content", answer.getContent());
        sqlQueryBuilder.addField("correct", answer.isCorrect());
        sqlQueryBuilder.addField("question_id", answer.getQuestion().getId());
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
    public Answer read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("answer");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Answer answer = buildAnswer(resultSet);
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
     * @param resultSet input result set parameters, taken from sql query execution
     * @return Answer from resultSet
     */
    private Answer buildAnswer(ResultSet resultSet) throws DaoOperationException {
        try {
            Answer answer = Answer.builder()
                    .content(resultSet.getString("content"))
                    .correct(Boolean.parseBoolean(resultSet.getString("correct")))
                    .build();
            answer.setId(resultSet.getLong("id"));
            return answer;
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
