package com.ilyabuglakov.raise.dal.realm;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.impl.RoleDao;
import com.ilyabuglakov.raise.dal.dao.impl.UserDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.TransactionFactory;
import com.ilyabuglakov.raise.dal.transaction.factory.impl.DatabaseTransactionFactory;
import com.ilyabuglakov.raise.domain.Role;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.DaoType;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Log4j2
public class DatabaseRealm extends AuthorizingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        Transaction transaction = new DatabaseTransactionFactory().createTransaction();
        UserDao userDao = (UserDao)transaction.createDao(DaoType.USER);
        AuthenticationInfo info = null;
        try {
            Optional<User> optionalUser = userDao.findByEmail(username);
            if(!optionalUser.isPresent())
                return null;
            info = new SimpleAuthenticationInfo(optionalUser.get(), optionalUser.get().getPassword(), getName());
        } catch (DaoOperationException e) {
            throw new AuthenticationException(e);
        } finally {
            try {
                transaction.commit();
                transaction.close();
            } catch (Exception e) {
                throw new AuthenticationException(e);
            }
        }
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String>			roles			= new HashSet<String>();
        Set<String>		permissions		= new HashSet<>();
        Collection<User>	principalsList	= principals.byType(User.class);

        if (principalsList.isEmpty()) {
            throw new AuthorizationException("Empty principals list!");
        }
//        //LOADING STUFF FOR PRINCIPAL
//        for (User userPrincipal : principalsList) {
//            try {
//                User user = this.userManager.loadById(userPrincipal.getId());
//
//                Set<Role> userRoles	= user.getRoles();
//                for (Role r : userRoles) {
//                    roles.add(r.getName());
//                    Set<WildcardPermission> userPermissions	= r.getPermissions();
//                    for (WildcardPermission permission : userPermissions) {
//                        if (!permissions.contains(permission)) {
//                            permissions.add(permission);
//                        }
//                    }
//                }
//                this.userManager.commitTransaction();
//            } catch (InvalidDataException idEx) { //userManger exceptions
//                throw new AuthorizationException(idEx);
//            } catch (ResourceException rEx) {
//                throw new AuthorizationException(rEx);
//            }
//        }
//        //THIS IS THE MAIN CODE YOU NEED TO DO !!!!
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
//        info.setRoles(roles); //fill in roles
//        info.setObjectPermissions(permissions); //add permisions (MUST IMPLEMENT SHIRO PERMISSION INTERFACE)

        return new SimpleAuthorizationInfo(null);
    }
//
//    @Override
//    protected Set<String> getRoleNamesForUser(String username) throws SQLException {
//        UserDao userDao = new UserDao(conn);
//        try {
//            Optional<Long> optionalId = userDao.getUserId(username);
//            if (!optionalId.isPresent())
//                return Collections.emptySet();
//            RoleDao roleDao = new RoleDao(conn);
//            return roleDao.getRoleNames(optionalId.get());
//
//        } catch (DaoOperationException e) {
//            throw new SQLException(e);
//        }
//    }

//    @Override
//    protected Set<String> getPermissions(Connection conn, String username, Collection<String> roleNames) throws SQLException {
//        RoleDao roleDao = new RoleDao(conn);
//        Set<String> permissions = new HashSet<>();
//        try {
//            for(String roleName : roleNames) {
//                permissions.addAll(roleDao.getPermissions(roleName));
//            }
//        } catch (DaoOperationException e) {
//            throw new SQLException(e);
//        }
//
//        return super.getPermissions(conn, username, roleNames);
//    }
//
//    @Override
//    protected String getSaltForUser(String username) {
//        return username;
//    }
}
