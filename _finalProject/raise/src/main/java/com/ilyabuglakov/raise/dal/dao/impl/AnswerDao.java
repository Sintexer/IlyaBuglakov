package com.ilyabuglakov.raise.dal.dao.impl;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.AnswerDaoInterface;
import com.ilyabuglakov.raise.domain.Answer;

import java.util.List;

public class AnswerDao implements AnswerDaoInterface {
    @Override
    public long create(Answer entity) throws DaoOperationException {

        return 0;
    }

    @Override
    public Answer read(long id) throws DaoOperationException {
        return null;
    }

    @Override
    public void update(Answer entity) throws DaoOperationException {

    }

    @Override
    public void delete(Answer entity) throws DaoOperationException {

    }
}
