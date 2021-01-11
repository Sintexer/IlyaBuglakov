package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserDaoInterface;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Role;
import com.ilyabuglakov.raise.domain.type.Status;
import com.ilyabuglakov.raise.service.sql.builder.SqlDeleteBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlInsertBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.service.sql.builder.SqlUpdateBuilder;
import com.ilyabuglakov.raise.service.validator.ResultSetValidator;

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
 */
public class UserDao extends BaseDao implements UserDaoInterface {

    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoOperationException {
        //TODO constants for table names
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("usr");
        sqlQueryBuilder.addWhere("email", email);
        String selectQuery = sqlQueryBuilder.build();

        Optional<ResultSet> optionalResultSet = unpackResultSet(createResultSet(selectQuery));

        if(optionalResultSet.isPresent())
            return buildUser(optionalResultSet.get());
        return Optional.empty();
    }

    @Override
    public void create(User entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("usr");
        sqlQueryBuilder.addField("email", entity.getEmail());
        sqlQueryBuilder.addField("name", entity.getName());
        sqlQueryBuilder.addField("password", entity.getPassword());
        sqlQueryBuilder.addField("surname", entity.getSurname());
        sqlQueryBuilder.addField("role", entity.getRole().name());
        sqlQueryBuilder.addField("status", entity.getStatus().name());
        sqlQueryBuilder.addField("registration_date", entity.getRegistrationDate());
        String insertQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(insertQuery);
    }

    @Override
    public Optional<User> read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("usr");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        Optional<User> user = buildUser(resultSet);
        try {
            long i = resultSet.getLong("id");
        } catch (SQLException e) {
            e.printStackTrace();//TODO
        }
        closeResultSet(resultSet);
        return user;
    }

    @Override
    public List<User> readAll() throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("usr");
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
        SqlQueryBuilder sqlQueryBuilder = new SqlUpdateBuilder("usr");
        sqlQueryBuilder.addField("name", user.getName());
        sqlQueryBuilder.addField("surname", user.getSurname());
        sqlQueryBuilder.addField("email", user.getEmail());
        sqlQueryBuilder.addField("password", user.getPassword());
        sqlQueryBuilder.addField("role", user.getRole());
        sqlQueryBuilder.addField("status", user.getStatus());
        sqlQueryBuilder.addField("registration_date", user.getRegistrationDate());
        sqlQueryBuilder.addWhere("id", user.getId());
        String updateQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(updateQuery);
    }

    @Override
    public void delete(User user) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlDeleteBuilder("usr");
        sqlQueryBuilder.addWhere("id", user.getId());
        String deleteQuery = sqlQueryBuilder.build();

        executeQueryWithoutResult(deleteQuery);
    }

    /**
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     *
     * Will build Optional-User only if result set has all user fields values,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input result set parameters, taken from sql query execution
     * @return User from resultSet
     */
    private Optional<User> buildUser(ResultSet resultSet) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if(validator.hasAllValues(resultSet, "email", "name", "surname",
                    "password", "registration_date", "role", "status", "id") ) {
                User user = User.builder()
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .registrationDate(LocalDate.parse(resultSet.getString("registration_date")))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build();
                user.setId(resultSet.getLong("id"));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
