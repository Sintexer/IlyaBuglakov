package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.DatabaseDao;
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
import com.ilyabuglakov.raise.model.service.validator.ResultSetValidator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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

    public static final String INSERT_TEST = String.format(
            "INSERT INTO %s(%s, %s, %s, %s) VALUES(?, ?, ?, ?)",
            Tables.TEST.name(),
            TestColumns.TEST_NAME.name(), TestColumns.STATUS.name(),
            TestColumns.AUTHOR_ID.name(), TestColumns.DIFFICULTY.name());

    public static final String SELECT_BY_ID = String.format(
            "SELECT %s, %s, %s, %s, %s FROM %s WHERE %s = ?",
            EntityColumns.ID.name(), TestColumns.TEST_NAME.name(), TestColumns.STATUS.name(),
            TestColumns.AUTHOR_ID.name(), TestColumns.DIFFICULTY.name(),
            Tables.TEST.name(),
            EntityColumns.ID.name());

    public static final String UPDATE_BY_ID = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s = ?",
            Tables.TEST.name(),
            TestColumns.TEST_NAME.name(), TestColumns.STATUS.name(),
            TestColumns.AUTHOR_ID.name(), TestColumns.DIFFICULTY.name(),
            EntityColumns.ID.name());

    public static final String UPDATE_STATUS_BY_ID = String.format(
            "UPDATE %s SET %s=? WHERE %s = ?",
            Tables.TEST.name(),
            TestColumns.STATUS.name(),
            EntityColumns.ID.name());

    public static final String SELECT_TEST_COUNT_BY_STATUS = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s = ?",
            Tables.TEST.name(),
            TestColumns.STATUS.name());

    public static final String SELECT_TEST_COUNT = String.format(
            "SELECT COUNT(*) FROM %s",
            Tables.TEST.name());

    public static final String SELECT_NEW_TEST_COUNT = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s='%s'",
            Tables.TEST.name(),
            TestColumns.STATUS.name(),
            TestStatus.NEW.name());

    public static final String SELECT_TEST_COUNT_BY_AUTHOR = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s=?",
            Tables.TEST.name(),
            TestColumns.AUTHOR_ID.name());

    public static final String SELECT_NEW_TEST_COUNT_BY_AUTHOR = String.format(
            "SELECT COUNT(*) FROM %s WHERE %s='%s' AND %s=?",
            Tables.TEST.name(),
            TestColumns.STATUS.name(), TestStatus.NEW.name(), TestColumns.AUTHOR_ID);

    public static final String INSERT_CHARACTERISTIC = String.format(
            "INSERT INTO %s(%s, %s) VALUES(?, ?)",
            Tables.TEST_CHARACTERISTIC.name(),
            TestCharacteristicColumns.CHARACTERISTIC.name(), TestCharacteristicColumns.TEST_ID.name());

    public static final String SELECT_CHARACTERISTICS = String.format(
            "SELECT %s FROM %s WHERE %s=?",
            TestCharacteristicColumns.CHARACTERISTIC.name(),
            Tables.TEST_CHARACTERISTIC.name(),
            TestCharacteristicColumns.TEST_ID.name());

    public static final String SELECT_TESTS_BY_STATUS_LIMIT_OFFSET = String.format(
            "SELECT %s, %s, %s, %s, %s FROM %s WHERE %s = ? LIMIT ? OFFSET ?",
            EntityColumns.ID.name(), TestColumns.TEST_NAME.name(), TestColumns.STATUS.name(),
            TestColumns.AUTHOR_ID.name(), TestColumns.DIFFICULTY.name(),
            Tables.TEST.name(),
            TestColumns.STATUS.name());

    public static final String SELECT_CONFIRMED_TESTS_LIMIT_OFFSET = String.format(
            "SELECT %s, %s, %s, %s, %s FROM %s WHERE %s = '%s' LIMIT ? OFFSET ?",
            EntityColumns.ID.name(), TestColumns.TEST_NAME.name(), TestColumns.STATUS.name(),
            TestColumns.AUTHOR_ID.name(), TestColumns.DIFFICULTY.name(),
            Tables.TEST.name(),
            TestColumns.STATUS.name(), TestStatus.CONFIRMED.name());

    public static final String SELECT_NEW_TESTS_LIMIT_OFFSET = String.format(
            "SELECT %s, %s, %s, %s, %s FROM %s WHERE %s = '%s' LIMIT ? OFFSET ?",
            EntityColumns.ID.name(), TestColumns.TEST_NAME.name(), TestColumns.STATUS.name(),
            TestColumns.AUTHOR_ID.name(), TestColumns.DIFFICULTY.name(),
            Tables.TEST.name(),
            TestColumns.STATUS, TestStatus.NEW.name());


    public TestDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public Integer create(Test test) throws DaoOperationException {
        PreparedStatement statement = prepareStatementReturnKeys(INSERT_TEST);
        setAllStatementParameters(test, statement);

        return executeReturnId(statement);
    }

    @Override
    public Optional<Test> read(Integer id) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_BY_ID);
        setIdStatementParameters(id, statement);

        Optional<ResultSet> resultSet = unpackResultSet(createResultSet(statement));
        if (resultSet.isPresent()) {
            Optional<Test> test = buildTest(resultSet.get());
            closeResultSet(resultSet.get());
            return test;
        }
        return Optional.empty();
    }

    @Override
    public void update(Test test) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(UPDATE_BY_ID);
        setAllStatementParameters(test, statement);
        try {
            statement.setInt(5, test.getId());
        } catch (SQLException e) {
            closeStatement(statement);
            throw new DaoOperationException("Can't set statement parameters", e);
        }

        executeUpdateQuery(statement);
    }

    @Override
    public void delete(Test test) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(UPDATE_BY_ID);
        setIdStatementParameters(test.getId(), statement);

        executeUpdateQuery(statement);
    }

    @Override
    public void saveCharacteristics(Collection<Characteristic> characteristics, Integer testId) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(INSERT_CHARACTERISTIC);
        try {
            for (Characteristic characteristic : characteristics) {
                statement.setObject(1, characteristic, Types.OTHER);
                statement.setInt(2, testId);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.getNextException().printStackTrace();
            throw new DaoOperationException("Error during characteristic batch", e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public Set<Characteristic> getCharacteristics(Integer testId) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_CHARACTERISTICS);
        setIdStatementParameters(testId, statement);

        ResultSet resultSet = createResultSet(statement);
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
    public Integer getTestAmountByStatus(TestStatus status) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_TEST_COUNT);
        try {
            statement.setObject(1, status, Types.OTHER);
        } catch (SQLException e) {
            closeStatement(statement);
            throw new DaoOperationException("Can't set statement parameters", e);
        }
        return getCount(createResultSet(statement));
    }

    @Override
    public Integer getTestAmount() throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_TEST_COUNT);
        return getCount(createResultSet(statement));
    }

    @Override
    public Integer getTestAmount(Integer authorId) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_TEST_COUNT_BY_AUTHOR);
        setIdStatementParameters(authorId, statement);
        return getCount(createResultSet(statement));
    }

    @Override
    public Integer getNewTestAmount() throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_NEW_TEST_COUNT);
        return getCount(createResultSet(statement));
    }

    @Override
    public Integer getNewTestAmount(Integer authorId) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_NEW_TEST_COUNT_BY_AUTHOR);
        setIdStatementParameters(authorId, statement);
        return getCount(createResultSet(statement));
    }

    @Override
    public List<Test> getTestsByStatus(TestStatus status, int startFrom, int itemsAmount) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_CONFIRMED_TESTS_LIMIT_OFFSET);
        try {
            statement.setObject(1, status);
            statement.setInt(2, itemsAmount);
            statement.setInt(3, startFrom);
        } catch (SQLException e) {
            closeStatement(statement);
            throw new DaoOperationException("Can't set statement parameters", e);
        }

        ResultSet resultSet = createResultSet(statement);
        return buildTestList(resultSet);
    }

    @Override
    public List<Test> getTests(int startFrom, int itemsAmount) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_CONFIRMED_TESTS_LIMIT_OFFSET);
        setAllIntStatementParameters(statement, itemsAmount, startFrom);

        ResultSet resultSet = createResultSet(statement);
        return buildTestList(resultSet);
    }

    @Override
    public List<Test> getNewTests(int startFrom, int itemsAmount) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_NEW_TESTS_LIMIT_OFFSET);
        setAllIntStatementParameters(statement, itemsAmount, startFrom);

        ResultSet resultSet = createResultSet(statement);
        return buildTestList(resultSet);
    }

    @Override
    public void updateStatus(Integer testId, TestStatus status) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(UPDATE_STATUS_BY_ID);
        try {
            statement.setObject(1, status, Types.OTHER);
            statement.setInt(2, testId);
        } catch (SQLException e) {
            closeStatement(statement);
            throw new DaoOperationException("Can't set statement parameters", e);
        }

        executeUpdateQuery(statement);
    }

    private void setAllStatementParameters(Test test, PreparedStatement statement) throws DaoOperationException {
        try {
            statement.setString(1, test.getTestName());
            statement.setObject(2, test.getStatus(), Types.OTHER);
            statement.setInt(3, test.getAuthor().getId());
            statement.setInt(4, test.getDifficulty());
        } catch (SQLException e) {
            closeStatement(statement);
            throw new DaoOperationException("Can't set statement parameters", e);
        }
    }

    private List<Test> buildTestList(ResultSet resultSet) throws DaoOperationException {
        List<Optional<Test>> tests = new ArrayList<>();
        try {
            while (resultSet.next()) {
                tests.add(buildTest(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoOperationException("Error while reading tests from resultSet", e);
        } finally {
            closeResultSet(resultSet);
        }

        return tests.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

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
