package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserDaoInterface;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Role;
import com.ilyabuglakov.raise.domain.type.Status;
import com.ilyabuglakov.raise.service.SqlQueryBuilder;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserDao extends BaseDao implements UserDaoInterface {

    @Override
    public long create(User entity) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder("usr");
        sqlQueryBuilder.addField("email", entity.getEmail());
        sqlQueryBuilder.addField("name", entity.getName());
        sqlQueryBuilder.addField("surname", entity.getSurname());
        sqlQueryBuilder.addField("role", entity.getRole());
        sqlQueryBuilder.addField("status", entity.getStatus());
        sqlQueryBuilder.addField("registration_date", entity.getRegistrationDate());
        String insertQuery = sqlQueryBuilder.buildInsertQuery();


        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getResultSet();
            if (resultSet.next())
                return resultSet.getLong(1);
            else
                throw new DaoOperationException("Index wasn't returned after insert into usr table operation");
        } catch (SQLException e) {
            throw new DaoOperationException(e);
        } finally {
            closeStatement(statement);
            closeResultSet(resultSet);
        }

    }

    @Override
    public User read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder("usr");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.buildSelectQuery();


        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            statement.execute(selectQuery);
            resultSet = statement.getResultSet();
            User user = null;
            if (resultSet.next()) {
                user = User.builder()
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .registrationDate(LocalDate.parse(resultSet.getString("registration_date")))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build();
                user.setId(resultSet.getLong("id"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoOperationException(e);
        } finally {
            closeStatement(statement);
            closeResultSet(resultSet);
        }

    }

    @Override
    public List<User> readAll() throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder("usr");
        String selectQuery = sqlQueryBuilder.buildSelectQuery();


        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            statement.execute(selectQuery);
            resultSet = statement.getResultSet();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
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
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoOperationException(e);
        } finally {
            closeStatement(statement);
            closeResultSet(resultSet);
        }
    }

    @Override
    public void update(User user) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder("usr");
        sqlQueryBuilder.addField("name", user.getName());
        sqlQueryBuilder.addField("surname", user.getSurname());
        sqlQueryBuilder.addField("email", user.getEmail());
        sqlQueryBuilder.addField("password", user.getPassword());
        sqlQueryBuilder.addField("role", user.getRole());
        sqlQueryBuilder.addField("status", user.getStatus());
        sqlQueryBuilder.addField("registration_date", user.getRegistrationDate());
        sqlQueryBuilder.addWhere("id", user.getId());
        String updateQuery = sqlQueryBuilder.buildUpdateQuery();


        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(updateQuery);

        } catch (SQLException e) {
            throw new DaoOperationException(e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public void delete(User user) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder("usr");
        sqlQueryBuilder.addWhere("id", user.getId());
        String deleteQuery = sqlQueryBuilder.buildDeleteQuery();


        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            throw new DaoOperationException(e);
        } finally {
            closeStatement(statement);
        }
    }
}
