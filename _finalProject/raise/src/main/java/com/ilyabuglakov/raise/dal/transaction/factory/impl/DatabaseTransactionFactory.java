package com.ilyabuglakov.raise.dal.transaction.factory.impl;

import com.ilyabuglakov.raise.dal.connection.pool.ConnectionPoolFactory;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.TransactionFactory;
import com.ilyabuglakov.raise.dal.transaction.factory.exception.TransactionCreationException;
import com.ilyabuglakov.raise.dal.transaction.impl.DatabaseTransaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DatabaseTransactionFactory uses ConnectionPool to create DatabaseTransaction object
 */
public class DatabaseTransactionFactory implements TransactionFactory {
    @Override
    public Transaction createTransaction() throws TransactionCreationException {
        Connection connection = ConnectionPoolFactory.getConnectionPool().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new TransactionCreationException("Can't turn connection autocommit off", e);
        }
        return new DatabaseTransaction(connection);
    }
}
