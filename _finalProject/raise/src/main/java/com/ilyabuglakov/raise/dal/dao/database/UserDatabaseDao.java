package com.ilyabuglakov.raise.dal.dao.database;

import com.ilyabuglakov.raise.dal.dao.DatabaseDao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserDao;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.structure.Tables;
import com.ilyabuglakov.raise.domain.structure.columns.EntityColumns;
import com.ilyabuglakov.raise.domain.structure.columns.RoleColumns;
import com.ilyabuglakov.raise.domain.structure.columns.UserColumns;
import com.ilyabuglakov.raise.domain.structure.columns.UserRolesColumns;
import com.ilyabuglakov.raise.domain.type.Status;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlUpdateBuilder;
import com.ilyabuglakov.raise.model.service.validator.ResultSetValidator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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

    public static final String INSERT_USER = String.format(
            "INSERT INTO %s(%s, %s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, ?, ?)",
            Tables.USR.name(),
            UserColumns.EMAIL.name(), UserColumns.NAME.name(), UserColumns.SURNAME.name(),
            UserColumns.REGISTRATION_DATE.name(), UserColumns.STATUS.name(), UserColumns.PASSWORD.name());

    public static final String SELECT_BY_ID = String.format(
            "SELECT %s, %s, %s, %s, %s, %s FROM %s WHERE %s = ?",
            EntityColumns.ID.name(), UserColumns.EMAIL.name(), UserColumns.NAME.name(), UserColumns.SURNAME.name(),
            UserColumns.REGISTRATION_DATE.name(), UserColumns.STATUS.name(),
            Tables.USR.name(),
            EntityColumns.ID.name());

    public static final String UPDATE_BY_ID = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s = ?",
            Tables.USR.name(),
            UserColumns.NAME.name(), UserColumns.SURNAME.name(), UserColumns.STATUS.name(), UserColumns.PASSWORD,
            EntityColumns.ID.name());

    public static final String DELETE_BY_ID = String.format(
            "DELETE FROM %s WHERE %s=?",
            Tables.USR.name(),
            EntityColumns.ID.name());

    public static final String SELECT_BY_EMAIL = String.format(
            "SELECT %s, %s, %s, %s, %s, %s FROM %s WHERE %s = ?",
            EntityColumns.ID.name(), UserColumns.EMAIL.name(), UserColumns.NAME.name(), UserColumns.SURNAME.name(),
            UserColumns.REGISTRATION_DATE.name(), UserColumns.STATUS.name(),
            Tables.USR.name(),
            UserColumns.EMAIL.name());

    public UserDatabaseDao(Connection connection) {
        super(connection);
    }

    @Override
    public Integer create(User user) throws DaoOperationException {
        PreparedStatement statement = prepareStatementReturnKeys(INSERT_USER);
        setAllStatementParameters(user, statement);

        return executeReturnId(statement);
    }

    @Override
    public Optional<User> read(Integer id) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_BY_ID);
        setIdStatementParameters(id, statement);

        Optional<ResultSet> resultSet = unpackResultSet(createResultSet(statement));
        Optional<User> user = Optional.empty();
        if(resultSet.isPresent()) {
            user = buildUser(resultSet.get());
            closeResultSet(resultSet.get());
        }
        return user;
    }

    @Override
    public void update(User user) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(UPDATE_BY_ID);
        try {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setObject(3, user.getStatus(), Types.OTHER);
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
        } catch (SQLException e) {
            closeStatement(statement);
            throw new DaoOperationException("Can't set statement parameters", e);
        }
        executeUpdateQuery(statement);
    }

    @Override
    public void delete(User user) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(DELETE_BY_ID);
        setIdStatementParameters(user.getId(), statement);

        executeUpdateQuery(statement);
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoOperationException {
        PreparedStatement statement = prepareStatement(SELECT_BY_EMAIL);
        try {
            statement.setString(1, email);
        } catch (SQLException e) {
            closeStatement(statement);
            throw new DaoOperationException("Can't set statement parameters", e);
        }

        Optional<ResultSet> optionalResultSet = unpackResultSet(createResultSet(statement));
        if(optionalResultSet.isPresent())
            return buildUser(optionalResultSet.get());
        return Optional.empty();
    }


    private void setAllStatementParameters(User user, PreparedStatement statement) throws DaoOperationException {
        try {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setDate(4, Date.valueOf(user.getRegistrationDate()));
            statement.setObject(5, user.getStatus(), Types.OTHER);
            statement.setString(6, user.getPassword());
        } catch (SQLException e) {
            closeStatement(statement);
            throw new DaoOperationException("Can't set statement parameters", e);
        }
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
                    UserColumns.REGISTRATION_DATE.name(),
                    UserColumns.STATUS.name(),
                    EntityColumns.ID.name()) ) {
                User user = User.builder()
                        .email(resultSet.getString(UserColumns.EMAIL.name()))
                        .name(resultSet.getString(UserColumns.NAME.name()))
                        .surname(resultSet.getString(UserColumns.SURNAME.name()))
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
