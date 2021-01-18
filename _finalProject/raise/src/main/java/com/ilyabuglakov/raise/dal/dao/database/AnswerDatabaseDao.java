package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDao;
import com.ilyabuglakov.raise.domain.Answer;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.AnswerColumns;
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
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<Answer> findByQuestionId(Integer questionId) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.ANSWER.name());
        sqlQueryBuilder.addWhere(AnswerColumns.QUESTION_ID.name(), questionId);
        String selectQuery = sqlQueryBuilder.build();

        Set<Optional<Answer>> answers = new HashSet<>();
        ResultSet resultSet = createResultSet(selectQuery);
        try {
            while (resultSet.next()) {
                Optional<Answer> answer = buildAnswer(resultSet);
                answers.add(answer);
            }
            return answers.stream()
                    .flatMap(Optional::stream)
                    .collect(Collectors.toSet());
        } catch (SQLException e) {
            throw new DaoOperationException("Bad result set after executing query. Can't build entities", e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public Integer create(Answer answer) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.ANSWER.name());
        sqlQueryBuilder.addField(AnswerColumns.CONTENT.name(), answer.getContent());
        sqlQueryBuilder.addField(AnswerColumns.CORRECT.name(), answer.isCorrect());
        sqlQueryBuilder.addField(AnswerColumns.QUESTION_ID.name(), answer.getQuestion().getId());
        String insertQuery = sqlQueryBuilder.build();

        return executeReturnId(insertQuery);
    }

    @Override
    public void createAll(Collection<Answer> answers) throws DaoOperationException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.ANSWER.name());
            for(Answer answer : answers){
                sqlQueryBuilder.addField(AnswerColumns.CONTENT.name(), answer.getContent());
                sqlQueryBuilder.addField(AnswerColumns.CORRECT.name(), answer.isCorrect());
                sqlQueryBuilder.addField(AnswerColumns.QUESTION_ID.name(), answer.getQuestion().getId());
                String query = sqlQueryBuilder.build();
                statement.addBatch(query);
                sqlQueryBuilder.clear();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw  new DaoOperationException("Can't save batch", e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public Optional<Answer> read(Integer id) throws DaoOperationException {
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

        executeUpdateQuery(updateQuery);
    }

    @Override
    public void delete(Answer answer) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder(Tables.ANSWER.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), answer.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeUpdateQuery(deleteQuery);
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
                        .correct(resultSet.getBoolean(AnswerColumns.CORRECT.name()))
                        .build();
                answer.setId(resultSet.getInt(EntityColumns.ID.name()));
                return Optional.of(answer);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
