package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.TestCategory;

import java.util.List;
import java.util.Map;

public interface TestCategoryService {

    Map<TestCategory, List<TestCategory>> getCategoryMap() throws PersistentException;

}
