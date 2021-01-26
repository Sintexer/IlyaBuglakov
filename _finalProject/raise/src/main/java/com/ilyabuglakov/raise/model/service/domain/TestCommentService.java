package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.exception.TransactionException;
import com.ilyabuglakov.raise.domain.TestComment;

import java.util.List;

public interface TestCommentService extends Service {
    void saveComment(String comment, Integer testId, String authorEmail) throws DaoOperationException, TransactionException;

    Integer getCommentsAmount(Integer testId) throws DaoOperationException;

    List<TestComment> getComments(Integer testId, int pageStart, int itemsPerPage) throws PersistentException;

    int getCommentsAmount(String email) throws DaoOperationException;

    void deleteComment(Integer commentId) throws PersistentException;
}
