package com.ilyabuglakov.raise.service.web;

import com.ilyabuglakov.raise.dal.transaction.Transaction;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class TransactionWebService{

    protected final Transaction transaction;

}
