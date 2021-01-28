package com.ilyabuglakov.raise.model.service.domain.utils.test.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.dto.TestInfo;

import java.util.List;
import java.util.Optional;

public interface TestReadService {

    Integer getTestAmount(Integer authorId) throws DaoOperationException;

}
