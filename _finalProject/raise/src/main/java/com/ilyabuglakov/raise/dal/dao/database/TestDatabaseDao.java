package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.EntityColumns;
import com.ilyabuglakov.raise.domain.structure.columns.TestCharacteristicColumns;
import com.ilyabuglakov.raise.domain.structure.columns.TestColumns;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.condition.SearchCondition;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TestDao is the Dao implementation specifically for Test class
 * Based on DatabaseDao abstract class.
 */
public class TestDatabaseDao extends DatabaseDao implements TestDao {

    public TestDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public void findAll(List<SearchCondition> conditions) throws DaoOperationException {

    }

    //    @Override
//    public Optional<Integer> getTestId(String testName) throws DaoOperationException {
//        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
//        sqlQueryBuilder.addField(EntityColumns.ID.name());
//        sqlQueryBuilder.addWhere(TestColumns.TEST_NAME.name(), testName);
//        String selectQuery = sqlQueryBuilder.build();
//
//        ResultSet resultSet = createResultSet(selectQuery);
//        Optional<Integer> id = Optional.empty();
//        try {
//            if(resultSet.next()) {
//                id = Optional.ofNullable(resultSet.getInt(EntityColumns.ID.name()));
//                if (resultSet.wasNull())
//                    id = Optional.empty();
//            }
//        } catch (SQLException e) {
//            throw new DaoOperationException("Error while reading index", e);
//        } finally {
//            closeResultSet(resultSet);
//        }
//        return id;
//    }


    @Override
    public void updateStatus(Integer testId, TestStatus status) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder(Tables.TEST.name());
        sqlQueryBuilder.addField(TestColumns.STATUS.name(), status.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), testId);
        String updateQuery = sqlQueryBuilder.build();

        executeUpdateQuery(updateQuery);
    }

    @Override
    public Integer getTestAmount() throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
        sqlQueryBuilder.addWhere(TestColumns.STATUS.name(), TestStatus.CONFIRMED.name());
        sqlQueryBuilder.returnCount();
        String query = sqlQueryBuilder.build();

        return getCount(createResultSet(query));
    }

    @Override
    public Integer getTestAmount(Integer authorId) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
        sqlQueryBuilder.addWhere(TestColumns.AUTHOR_ID.name(), authorId);
        sqlQueryBuilder.returnCount();
        String query = sqlQueryBuilder.build();

        return getCount(createResultSet(query));
    }

    @Override
    public Integer getNewTestAmount() throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
        sqlQueryBuilder.addWhere(TestColumns.STATUS.name(), TestStatus.NEW.name());
        sqlQueryBuilder.returnCount();
        String query = sqlQueryBuilder.build();

        return getCount(createResultSet(query));
    }

    @Override
    public Integer getNewTestAmount(Integer authorId) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
        sqlQueryBuilder.addWhere(TestColumns.STATUS.name(), TestStatus.NEW.name());
        sqlQueryBuilder.addWhere(TestColumns.AUTHOR_ID.name(), authorId);
        sqlQueryBuilder.returnCount();
        String query = sqlQueryBuilder.build();

        return getCount(createResultSet(query));
    }

    @Override
    public void saveCharacteristics(Collection<Characteristic> characteristics, Integer testId) throws DaoOperationException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.TEST_CHARACTERISTIC.name());
            for (Characteristic characteristic : characteristics) {
                sqlQueryBuilder.addField(TestCharacteristicColumns.CHARACTERISTIC.name(), characteristic.name());
                sqlQueryBuilder.addField(TestCharacteristicColumns.TEST_ID.name(), testId);
                String insertQuery = sqlQueryBuilder.build();
                statement.addBatch(insertQuery);
                sqlQueryBuilder.clear();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new DaoOperationException("Error during characteristic batch", e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public Set<Characteristic> getCharacteristics(Integer testId) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST_CHARACTERISTIC.name());
        sqlQueryBuilder.addField(TestCharacteristicColumns.CHARACTERISTIC.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), testId);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Set<Characteristic> characteristics = new HashSet<>();
        try {
            while (resultSet.next()) {
                characteristics.add(Characteristic.valueOf(
                        resultSet.getString(TestCharacteristicColumns.CHARACTERISTIC.name())));
            }
        } catch (SQLException e) {
            throw new DaoOperationException("Error while reading test characteristics", e);
        } finally {
            closeResultSet(resultSet);
        }
        return characteristics;
    }

    @Override
    public List<Test> getTests(int startFrom, int itemsAmount) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
        sqlQueryBuilder.addField(EntityColumns.ID.name());
        sqlQueryBuilder.addField(TestColumns.TEST_NAME.name());
        sqlQueryBuilder.addField(TestColumns.STATUS.name());
        sqlQueryBuilder.addField(TestColumns.AUTHOR_ID.name());
        sqlQueryBuilder.addField(TestColumns.DIFFICULTY.name());
        sqlQueryBuilder.addWhere(TestColumns.STATUS.name(), TestStatus.CONFIRMED.name());
        sqlQueryBuilder.addLimit(startFrom, itemsAmount);
        String query = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(query);
        List<Optional<Test>> tests = new ArrayList<>();

        try {
            while (resultSet.next()) {
                tests.add(buildTest(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoOperationException("Error while reading tests from resultSet", e);
        }
        closeResultSet(resultSet);
        return tests.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Test> getNewTests(int startFrom, int itemsAmount) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
        sqlQueryBuilder.addField(EntityColumns.ID.name());
        sqlQueryBuilder.addField(TestColumns.TEST_NAME.name());
        sqlQueryBuilder.addField(TestColumns.STATUS.name());
        sqlQueryBuilder.addField(TestColumns.AUTHOR_ID.name());
        sqlQueryBuilder.addField(TestColumns.DIFFICULTY.name());
        sqlQueryBuilder.addWhere(TestColumns.STATUS.name(), TestStatus.NEW.name());
        sqlQueryBuilder.addLimit(startFrom, itemsAmount);
        String query = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(query);
        List<Optional<Test>> tests = new ArrayList<>();

        try {
            while (resultSet.next()) {
                tests.add(buildTest(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoOperationException("Error while reading tests from resultSet", e);
        }
        closeResultSet(resultSet);
        return tests.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Integer create(Test test) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.TEST.name());
        sqlQueryBuilder.addField(TestColumns.TEST_NAME.name(), test.getTestName());
        sqlQueryBuilder.addField(TestColumns.STATUS.name(), test.getStatus());
        sqlQueryBuilder.addField(TestColumns.AUTHOR_ID.name(), test.getAuthor().getId());
        sqlQueryBuilder.addField(TestColumns.DIFFICULTY.name(), test.getDifficulty());
        String insertQuery = sqlQueryBuilder.build();

        return executeReturnId(insertQuery);
    }

    @Override
    public Optional<Test> read(Integer id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.TEST.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), id);
        String selectQuery = sqlQueryBuilder.build();

        Optional<ResultSet> resultSet = unpackResultSet(createResultSet(selectQuery));
        if (resultSet.isPresent()) {
            Optional<Test> test = buildTest(resultSet.get());
            closeResultSet(resultSet.get());
            return test;
        }
        return Optional.empty();
    }

    @Override
    public void update(Test test) throws DaoOperationException {
        //TODO add characteristics and comments subqueries
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder(Tables.TEST.name());
        sqlQueryBuilder.addField(TestColumns.TEST_NAME.name(), test.getTestName());
        sqlQueryBuilder.addField(TestColumns.STATUS.name(), test.getStatus());
        sqlQueryBuilder.addField(TestColumns.DIFFICULTY.name(), test.getDifficulty());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), test.getId());
        String updateQuery = sqlQueryBuilder.build();

        executeUpdateQuery(updateQuery);
    }

    @Override
    public void delete(Test test) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder(Tables.TEST_CHARACTERISTIC.name());
        sqlQueryBuilder.addWhere(TestCharacteristicColumns.TEST_ID.name(), test.getId());
        String subQuery = sqlQueryBuilder.build();
        sqlQueryBuilder = new SqlDeleteBuilder(Tables.TEST.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), test.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeUpdateQuery(subQuery);
        executeUpdateQuery(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     * <p>
     * Will build Optional-Test only if resultSet has values of all test fields,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input resultSet parameters, taken from sql query execution
     * @return Test from resultSet
     */
    private Optional<Test> buildTest(ResultSet resultSet) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if (validator.hasAllValues(resultSet,
                    TestColumns.TEST_NAME.name(),
                    TestColumns.STATUS.name(),
                    TestColumns.AUTHOR_ID.name(),
                    TestColumns.DIFFICULTY.name(),
                    EntityColumns.ID.name())) {
                User user = new User();
                user.setId(resultSet.getInt(TestColumns.AUTHOR_ID.name()));
                Test test = Test.builder()
                        .testName(resultSet.getString(TestColumns.TEST_NAME.name()))
                        .status(TestStatus.valueOf(resultSet.getString(TestColumns.STATUS.name())))
                        .author(user)
                        .difficulty(Integer.parseInt(resultSet.getString(TestColumns.DIFFICULTY.name())))
                        .build();
                test.setId(resultSet.getInt(EntityColumns.ID.name()));
                return Optional.of(test);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
