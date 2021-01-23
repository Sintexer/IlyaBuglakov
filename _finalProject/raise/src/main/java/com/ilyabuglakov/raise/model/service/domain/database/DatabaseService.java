package com.ilyabuglakov.raise.model.service.domain.database;

import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.model.service.domain.Service;

public abstract class DatabaseService implements Service {
    protected final Transaction transaction;

    public DatabaseService(Transaction transaction) {
        this.transaction = transaction;
    }
}
