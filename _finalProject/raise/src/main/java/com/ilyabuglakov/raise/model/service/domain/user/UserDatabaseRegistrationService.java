package com.ilyabuglakov.raise.model.service.domain.user;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.database.UserDatabaseDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.RoleDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Status;
import com.ilyabuglakov.raise.domain.type.UserRole;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.service.domain.TransactionWebService;
import com.ilyabuglakov.raise.model.service.domain.user.exception.UserRegistrationServiceException;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserRegistrationService;

import java.time.LocalDate;
import java.util.Set;

/**
 * UserDatabaseRegistrationService is an implementation of UserRegistrationService, that uses database connection
 * as registration datasource.
 */
public class UserDatabaseRegistrationService extends TransactionWebService implements UserRegistrationService {

    public UserDatabaseRegistrationService(Transaction transaction) {
        super(transaction);
    }

    /**
     * @param user - User object to save
     *
     * @throws UserRegistrationServiceException when DaoOperationException happens
     */
    @Override
    public void save(User user) throws UserRegistrationServiceException {
        UserDatabaseDao dao = (UserDatabaseDao) transaction.createDao(DaoType.USER);
        RoleDao roleDao = (RoleDao) transaction.createDao(DaoType.ROLE);

//        Hash hash = new Sha256Hash(user.getPassword(), 1, 5128); //TODO Add salt, change number

        User committedUser = User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
//                .password(hash.toBase64()) TODO
                .password(user.getPassword())
                .registrationDate(LocalDate.now())
                .status(Status.ACTIVE)
                .build(); //TODO change status to непотвержденный

        try {
            Integer userId = dao.create(committedUser);
            roleDao.createUserRoles(userId, Set.of(UserRole.USER));
        } catch (DaoOperationException e) {
            throw new UserRegistrationServiceException("Error while creating user", e);
        }
    }

}
