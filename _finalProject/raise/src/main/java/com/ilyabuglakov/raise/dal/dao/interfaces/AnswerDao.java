package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Answer;
import com.ilyabuglakov.raise.domain.Question;

import java.util.Collection;

public interface AnswerDao extends Dao<Answer> {
    void createAll(Collection<Answer> answers) throws DaoOperationException;
}
