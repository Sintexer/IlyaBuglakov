package com.ilyabuglakov.raise.dal.transaction;

import com.ilyabuglakov.raise.dal.dao.interfaces.Dao;
import com.ilyabuglakov.raise.dal.transaction.exception.TransactionException;
import com.ilyabuglakov.raise.model.DaoType;

/**
 * Transaction is interface for classes, that let you to perform
 * atomic transaction.
 * Transaction can be committed via commit() method, or rolled back by rollback() method.
 */
public interface Transaction extends AutoCloseable {

    <T extends Dao<?>> T createDao(DaoType daoType) throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;

}
