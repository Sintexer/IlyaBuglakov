package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.QuestionDaoInterface;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlUpdateBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * QuestionDao is the Dao implementation specifically for Question class
 */
public class QuestionDao extends BaseDao implements QuestionDaoInterface {

    @Override
    public void create(Question question) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("question");
        sqlQueryBuilder.addField("content", question.getContent());
        sqlQueryBuilder.addField("test_id", question.getTest().getId());
        String insertQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(insertQuery);
    }

    @Override
    public Question read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("qu");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Question question = buildQuestion(resultSet);
        closeResultSet(resultSet);
        return question;
    }

    @Override
    public void update(Question question) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder("question");
        sqlQueryBuilder.addField("id", question.getId());
        sqlQueryBuilder.addField("content", question.getContent());
        sqlQueryBuilder.addField("test_id", question.getTest().getId());
        String updateQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(updateQuery);
    }

    @Override
    public void delete(Question question) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder("answer");
        sqlQueryBuilder.addWhere("id", question.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * @param resultSet input result set parameters, taken from sql query execution
     * @return Question from resultSet
     */
    private Question buildQuestion(ResultSet resultSet) throws DaoOperationException {
        try {
            Question question = Question.builder()
                    .content(resultSet.getString("content"))
                    .build();
            question.setId(resultSet.getLong("id"));
            return question;
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
