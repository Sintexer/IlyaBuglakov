package com.ilyabuglakov.raise.model.service.domain.test.interfaces;

import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.service.domain.test.exception.TestSaveServiceException;

public interface TestSaveService {
    void save(Test test) throws TestSaveServiceException;
}
