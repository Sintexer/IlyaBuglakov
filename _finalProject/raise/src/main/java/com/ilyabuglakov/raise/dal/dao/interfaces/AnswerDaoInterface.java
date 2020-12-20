package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Answer;

import java.util.List;

public interface AnswerDaoInterface extends Dao<Answer> {
    List<Answer> readAll() throws DaoOperationException;
}
