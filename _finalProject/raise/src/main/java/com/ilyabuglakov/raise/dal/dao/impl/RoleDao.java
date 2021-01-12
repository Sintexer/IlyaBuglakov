package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.RoleDaoInterface;
import com.ilyabuglakov.raise.domain.Role;
import com.ilyabuglakov.raise.model.service.sql.ResultSetService;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlQueryBuilder;
import com.ilyabuglakov.raise.model.service.sql.builder.SqlSelectBuilder;
import com.ilyabuglakov.raise.model.service.validator.ResultSetValidator;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * RoleDao is the Dao implementation specifically for Role class
 */
@Log4j2
public class RoleDao extends BaseDao implements RoleDaoInterface {

    public RoleDao(Connection connection) {
        super(connection);
    }

    @Override
    public Set<String> getRoleNames(Long userId) throws SQLException, DaoOperationException {

        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("user_roles");
        sqlQueryBuilder.addField("role_id");
        sqlQueryBuilder.addWhere("user_id", userId);
        String subQuery = sqlQueryBuilder.build();
        sqlQueryBuilder = new SqlSelectBuilder("role");
        sqlQueryBuilder.addField("name");
        sqlQueryBuilder.addWhere("id", "(" + subQuery + ")");
        String selectQuery = sqlQueryBuilder.build();

        Set<Optional<String>> roleNames = new HashSet<>();
        ResultSet resultSet = null;

        try {
            resultSet = createResultSet(selectQuery);
            while(resultSet.next())
                roleNames.add(Optional.ofNullable(resultSet.getString("name")));
        } finally {
            closeResultSet(resultSet);
        }
        return roleNames.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getPermissions(Long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("role_permissions");
        sqlQueryBuilder.addField("permission");
        sqlQueryBuilder.addWhere("role_id", id);
        String selectQuery = sqlQueryBuilder.build();

        ResultSet permissionResultSet = createResultSet(selectQuery);
        Set<String> permissions = new HashSet<>();

        try {
            permissions.addAll(new ResultSetService().getAllStringsByName(permissionResultSet, "permission"));
        } catch (SQLException e) {
            throw new DaoOperationException("Can't read role's permissions");
        } finally {
            closeResultSet(permissionResultSet);
        }
        return permissions;
    }

    @Override
    public Set<String> getPermissions(String name) throws DaoOperationException, SQLException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("role");
        sqlQueryBuilder.addField("id");
        sqlQueryBuilder.addWhere("name", name);
        String subQuery = sqlQueryBuilder.build();
        Optional<ResultSet> optionalResultSet = unpackResultSet(createResultSet(subQuery));

        if (optionalResultSet.isPresent()) {
            ResultSet resultSet = optionalResultSet.get();
            Optional<Long> id = Optional.ofNullable(resultSet.getLong("id"));
            if (id.isPresent()) {
                return getPermissions(id.get());
            }
        }

        return Collections.emptySet();
    }

    @Override
    public void create(Role entity) throws DaoOperationException {
        log.error("Operation \"save role\" not implemented");
    }

    @Override
    public Optional<Role> read(long id) throws DaoOperationException {
        SqlQueryBuilder sqlQueryBuilder = new SqlSelectBuilder("role");
        sqlQueryBuilder.addField("name");
        sqlQueryBuilder.addWhere("id", id);
        String selectQuery = sqlQueryBuilder.build();

        Optional<ResultSet> optionalResultSet = unpackResultSet(createResultSet(selectQuery));
        Optional<Role> optionalRole = Optional.empty();
        if (optionalResultSet.isPresent()) {
            ResultSet resultSet = optionalResultSet.get();

            Set<String> permissions = getPermissions(id);
            optionalRole = buildRole(resultSet, permissions);
        }
        return optionalRole;
    }

    @Override
    public void update(Role entity) throws DaoOperationException {
        log.error("Operation \"update role\" not implemented");
    }

    @Override
    public void delete(Role entity) throws DaoOperationException {
        log.error("Operation \"delete role\" not implemented");
    }

    /**
     * TODO
     * This operation won't close resultSet in success case, but will
     * in case of exception thrown
     * <p>
     * Will build Optional-Role only if resultSet has values of all user fields,
     * otherwise will return Optional.empty()
     *
     * @param resultSet input resultSet parameters, taken from sql query execution
     * @return Role from resultSet
     */
    private Optional<Role> buildRole(ResultSet resultSet, Set<String> permissions) throws DaoOperationException {
        try {
            ResultSetValidator validator = new ResultSetValidator();
            if (validator.hasAllValues(resultSet, "name", "id")) {
                Role role = Role.builder()
                        .name(resultSet.getString("name"))
                        .build();
                role.setId(resultSet.getLong("id"));
                return Optional.of(role);
            }
            return Optional.empty();
        } catch (SQLException e) {
            closeResultSet(resultSet);
            throw createBadResultSetException(e);
        }
    }
}
