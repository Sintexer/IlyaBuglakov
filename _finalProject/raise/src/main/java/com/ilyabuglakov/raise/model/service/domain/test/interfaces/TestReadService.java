package com.ilyabuglakov.raise.model.service.domain.test.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.dto.TestInfo;

import java.util.List;
import java.util.Optional;

public interface TestReadService {

    Optional<Test> getTest(Integer testId) throws DaoOperationException;

    List<TestInfo> getTestInfos(int pageNumber, int testsPerPage) throws DaoOperationException;
    List<TestInfo> getNewTestInfos(int pageNumber, int testsPerPage) throws DaoOperationException;

    Integer getTestAmount() throws DaoOperationException;
    Integer getTestAmount(Integer authorId) throws DaoOperationException;
    Integer getNewTestAmount(Integer authorId) throws DaoOperationException;
    Integer getNewTestAmount() throws DaoOperationException;
}
