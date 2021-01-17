package com.ilyabuglakov.raise.model.service.web.test.interfaces;

import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.service.web.test.exception.TestSaveServiceException;

public interface TestSaveService {
    void save(Test test) throws TestSaveServiceException;
}
