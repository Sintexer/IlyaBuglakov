package com.ilyabuglakov.raise.service.web.user;

import com.ilyabuglakov.raise.dal.dao.impl.UserDao;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.exception.TransactionException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.service.web.TransactionWebService;
import com.ilyabuglakov.raise.service.web.user.exception.UserSearchException;
import com.ilyabuglakov.raise.service.web.user.interfaces.UserSearchService;

import java.util.Optional;

public class UserTransactionSearch extends TransactionWebService implements UserSearchService {

    public UserTransactionSearch(Transaction transaction) {
        super(transaction);
    }

    @Override
    public Optional<User> findByEmail(String email) throws UserSearchException {
        try {
            UserDao dao = transaction.createDao(DaoType.USER);
            Optional<User> user = dao.
        } catch (TransactionException e) {
            throw new UserSearchException("Search source can't be created", e);
        }
    }

}
