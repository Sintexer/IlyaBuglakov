package com.ilyabuglakov.raise.dal.transaction.factory;

import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.dal.transaction.factory.exception.TransactionCreationException;

public interface TransactionFactory {
    Transaction createTransaction() throws TransactionCreationException;

    void close();
}
