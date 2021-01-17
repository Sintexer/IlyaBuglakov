package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserDao;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.EntityColumns;
import com.ilyabuglakov.raise.domain.structure.columns.UserColumns;
import com.ilyabuglakov.raise.domain.type.Status;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlUpdateBuilder;
import com.ilyabuglakov.raise.model.service.validator.ResultSetValidator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserDao is the Dao implementation specifically for User class
 * Based on DatabaseDao abstract class.
 */
public class UserDatabaseDao extends DatabaseDao implements UserDao {

    public UserDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.USR.name());
        sqlQueryBuilder.addWhere(UserColumns.EMAIL.name(), email);
        String selectQuery = sqlQueryBuilder.build();

        Optional<ResultSet> optionalResultSet = unpackResultSet(createResultSet(selectQuery));

        if(optionalResultSet.isPresent())
            return buildUser(optionalResultSet.get());
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getUserId(String email) throws SQLException, DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.USR.name());
        sqlQueryBuilder.addField(EntityColumns.ID.name());
        sqlQueryBuilder.addWhere(UserColumns.EMAIL.name(), email);
        String selectQuery = sqlQueryBuilder.build();

        Optional<ResultSet> optionalResultSet = unpackResultSet(createResultSet(selectQuery));

        if(optionalResultSet.isPresent()) {
            return Optional.of(optionalResultSet.get().getInt(EntityColumns.ID.name()));
        }
        return Optional.empty();
    }

    @Override
    public Integer create(User entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder(Tables.USR.name());
        sqlQueryBuilder.addField(UserColumns.EMAIL.name(), entity.getEmail());
        sqlQueryBuilder.addField(UserColumns.NAME.name(), entity.getName());
        sqlQueryBuilder.addField(UserColumns.PASSWORD.name(), entity.getPassword());
        sqlQueryBuilder.addField(UserColumns.SURNAME.name(), entity.getSurname());
        sqlQueryBuilder.addField(UserColumns.STATUS.name(), entity.getStatus().name());
        sqlQueryBuilder.addField(UserColumns.REGISTRATION_DATE.name(), entity.getRegistrationDate());
        String insertQuery = sqlQueryBuilder.build();

        return executeReturnId(insertQuery);
    }

    @Override
    public Optional<User> read(Integer id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.USR.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<User> user = buildUser(resultSet);
        closeResultSet(resultSet);
        return user;
    }

    @Override
    public List<User> readAll() throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder(Tables.USR.name());
        String selectQuery = sqlQueryBuilder.build();

        List<Optional<User>> users = new ArrayList<>();
        ResultSet resultSet = createResultSet(selectQuery);
        try {
            while (resultSet.next()) {
                Optional<User> user = buildUser(resultSet);
                users.add(user);
            }
            return users.stream()
                    .flatMap(Optional::stream)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new DaoOperationException("Bad result set after executing query. Can't build entities", e);
        } finally {
            closeResultSet(resultSet);
        }
    }

    @Override
    public void update(User user) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder(Tables.USR.name());
        sqlQueryBuilder.addField(UserColumns.EMAIL.name(), user.getEmail());
        sqlQueryBuilder.addField(UserColumns.NAME.name(), user.getName());
        sqlQueryBuilder.addField(UserColumns.SURNAME.name(), user.getSurname());
        sqlQueryBuilder.addField(UserColumns.PASSWORD.name(), user.getPassword());
        sqlQueryBuilder.addField(UserColumns.STATUS.name(), user.getStatus());
        sqlQueryBuilder.addField(UserColumns.REGISTRATION_DATE.name(), user.getRegistrationDate());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), user.getId());
        String updateQuery = sqlQueryBuilder.build();

        executeUpdateQuery(updateQuery);
    }

    @Override
    public void delete(User user) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder(Tables.USR.name());
        sqlQueryBuilder.addWhere(EntityColumns.ID.name(), user.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeUpdateQuery(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * Will build Optional-User only if resultSet has values of all User fields,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input resultSet parameters, taken from sql query execution
     * @return User from resultSet
     */
    private Optional<User> buildUser(ResultSet resultSet) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if(validator.hasAllValues(resultSet, UserColumns.EMAIL.name(),
                    UserColumns.NAME.name(),
                    UserColumns.SURNAME.name(),
                    UserColumns.PASSWORD.name(),
                    UserColumns.REGISTRATION_DATE.name(),
                    UserColumns.STATUS.name(),
                    EntityColumns.ID.name()) ) {
                User user = User.builder()
                        .email(resultSet.getString(UserColumns.EMAIL.name()))
                        .name(resultSet.getString(UserColumns.NAME.name()))
                        .surname(resultSet.getString(UserColumns.SURNAME.name()))
                        .password(resultSet.getString(UserColumns.PASSWORD.name()))
                        .registrationDate(LocalDate.parse(resultSet.getString(UserColumns.REGISTRATION_DATE.name())))
                        .status(Status.valueOf(resultSet.getString(UserColumns.STATUS.name())))
                        .build();
                user.setId(resultSet.getInt(EntityColumns.ID.name()));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
