package com.ilyabuglakov.raise.service.web.user;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.impl.UserDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.service.web.TransactionWebService;
import com.ilyabuglakov.raise.service.web.user.exception.UserSearchServiceException;
import com.ilyabuglakov.raise.service.web.user.interfaces.UserSearchService;

import java.util.Optional;

public class UserTransactionSearch extends TransactionWebService implements UserSearchService {

    public UserTransactionSearch(Transaction transaction) {
        super(transaction);
    }

    @Override
    public Optional<User> findByEmail(String email) throws UserSearchServiceException {
        try {
            UserDao dao = (UserDao)transaction.createDao(DaoType.USER);
            return dao.findByEmail(email);
        } catch (DaoOperationException e) {
            throw new UserSearchServiceException("Search source can't be created", e);
        }
    }

}
