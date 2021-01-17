package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.transaction.Transaction;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class TransactionWebService{

    protected final Transaction transaction;

}
