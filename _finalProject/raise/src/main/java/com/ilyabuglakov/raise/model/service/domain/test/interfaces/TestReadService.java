package com.ilyabuglakov.raise.model.service.domain.test.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Test;

import java.util.List;

public interface TestReadService {

    List<Test> getTestsInfo(int pageNumber, int testsPerPage) throws DaoOperationException;
}
