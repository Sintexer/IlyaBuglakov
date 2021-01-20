package com.ilyabuglakov.raise.model.service.domain.user;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserTestResultDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.exception.TransactionException;
import com.ilyabuglakov.raise.domain.UserTestResult;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.service.domain.TransactionWebService;
import com.ilyabuglakov.raise.model.service.domain.user.exception.UserParametersServiceException;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserParametersService;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
public class UserParametersDatabaseService extends TransactionWebService implements UserParametersService {
    public UserParametersDatabaseService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void saveResult(UserTestResult userTestResult) throws UserParametersServiceException {
        UserTestResultDao userTestResultDao = (UserTestResultDao) transaction.createDao(DaoType.USER_TEST_RESULT);
        try {
            Optional<UserTestResult> prevResult =
                    userTestResultDao.getByUserIdAndTestId(userTestResult.getUser().getId(),
                            userTestResult.getTest().getId());
            if(prevResult.isPresent())
                if(prevResult.get().getResult() < userTestResult.getResult()) {
                    userTestResult.setId(prevResult.get().getId());
                    userTestResultDao.update(userTestResult);
                }
            else
                userTestResultDao.create(userTestResult);
        } catch (DaoOperationException e) {
            try {
                transaction.rollback();
            } catch (TransactionException transactionException) {
                log.error("Can't rollback transaction");
            }
            throw new UserParametersServiceException("Couldn't save result", e);
        }
    }
}
