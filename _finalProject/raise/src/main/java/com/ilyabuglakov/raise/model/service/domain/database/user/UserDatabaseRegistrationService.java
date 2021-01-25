package com.ilyabuglakov.raise.model.service.domain.database.user;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.domain.type.Status;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.UserRegistrationService;
import com.ilyabuglakov.raise.model.service.domain.database.DatabaseService;

import java.time.LocalDate;

public class UserDatabaseRegistrationService extends DatabaseService implements UserRegistrationService {
    public UserDatabaseRegistrationService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public ResponseEntity registerUser(User user) throws DaoOperationException {
        UserDatabaseValidationService userValidationService = new UserDatabaseValidationService(transaction);
        ResponseEntity responseEntity = userValidationService.validateUser(user);
        if(responseEntity.isErrorOccurred())
            return responseEntity;
        if(!userValidationService.isUnique(user)){
            responseEntity.setErrorOccurred(true);
            responseEntity.setAttribute("userEmailAlreadyExist", true);
            return responseEntity;
        }
        //TODO hash password
        user.setStatus(Status.ACTIVE);
        user.setRegistrationDate(LocalDate.now());
        UserDao userDao = (UserDao) transaction.createDao(DaoType.USER);
        userDao.create(user);
        return responseEntity;
    }
}
