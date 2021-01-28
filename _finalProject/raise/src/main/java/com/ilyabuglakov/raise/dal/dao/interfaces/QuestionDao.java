package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Question;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionDao extends Dao<Question> {
    void createAll(Collection<Question> questions) throws DaoOperationException;

    Optional<Integer> findQuestionAmount(Integer testId) throws DaoOperationException;

    Set<Question> findByTestId(Integer testId) throws DaoOperationException;

    List<String> findQuestionsNames(Integer testId) throws DaoOperationException;
}
