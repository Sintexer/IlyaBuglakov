package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.TestComment;

import java.util.List;

public interface TestCommentDao extends Dao<TestComment> {
    Integer findCommentsAmount(Integer testId) throws DaoOperationException;

    List<TestComment> findComments(Integer testId, int offset, int items) throws DaoOperationException;

    int findUserCommentAmount(String email) throws DaoOperationException;
}
