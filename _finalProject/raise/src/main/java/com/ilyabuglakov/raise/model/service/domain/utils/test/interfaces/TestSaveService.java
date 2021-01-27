package com.ilyabuglakov.raise.model.service.domain.utils.test.interfaces;

import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.service.domain.utils.test.exception.TestSaveServiceException;

public interface TestSaveService {
    void save(Test test) throws TestSaveServiceException;
}
