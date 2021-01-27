package com.ilyabuglakov.raise.model.service.domain.utils.user;

import com.ilyabuglakov.raise.dal.dao.database.UserDatabaseDao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.service.domain.TransactionWebService;
import com.ilyabuglakov.raise.model.service.domain.utils.user.interfaces.UserSearchService;

import java.util.Optional;

public class UserTransactionSearch extends TransactionWebService implements UserSearchService {

    public UserTransactionSearch(Transaction transaction) {
        super(transaction);
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoOperationException {
        UserDatabaseDao dao = (UserDatabaseDao) transaction.createDao(DaoType.USER);
        return dao.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Integer userId) throws DaoOperationException {
        UserDatabaseDao dao = (UserDatabaseDao) transaction.createDao(DaoType.USER);
        return dao.read(userId);
    }
}
