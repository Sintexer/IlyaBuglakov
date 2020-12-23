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
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDao is the Dao implementation specifically for User class
 */
public class UserDao extends BaseDao implements UserDaoInterface {

    @Override
    public long create(User entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlInsertBuilder("usr");
        sqlQueryBuilder.addField("email", entity.getEmail());
        sqlQueryBuilder.addField("name", entity.getName());
        sqlQueryBuilder.addField("surname", entity.getSurname());
        sqlQueryBuilder.addField("role", entity.getRole());
        sqlQueryBuilder.addField("status", entity.getStatus());
        sqlQueryBuilder.addField("registration_date", entity.getRegistrationDate());
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
    public User read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("usr");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet resultSet = createResultSet(selectQuery);
        User user = buildUser(resultSet);
        closeResultSet(resultSet);
        return user;
    }

    @Override
    public List<User> readAll() throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("usr");
        String selectQuery = sqlQueryBuilder.build();

        List<User> users = new ArrayList<>();
        ResultSet resultSet = createResultSet(selectQuery);
        try {
            while (resultSet.next()) {
                User user = buildUser(resultSet);
                users.add(user);
            }
            return users;
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
     * @param resultSet input result set parameters, taken from sql query execution
     * @return User from resultSet
     */
    private User buildUser(ResultSet resultSet) throws DaoOperationException {
        try {
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
            return user;
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
