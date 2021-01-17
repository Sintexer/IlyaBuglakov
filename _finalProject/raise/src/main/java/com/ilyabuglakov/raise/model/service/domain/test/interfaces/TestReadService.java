package com.ilyabuglakov.raise.model.service.domain.test.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.TestInfo;

import java.util.List;

public interface TestReadService {

    List<TestInfo> getTestInfos(int pageNumber, int testsPerPage) throws DaoOperationException;

    Integer getTestAmount() throws DaoOperationException;
}
