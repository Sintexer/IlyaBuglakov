package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.TestCategory;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.domain.type.TestStatus;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TestDao extends Dao<Test> {
    List<Test> findByNameAndCategoryAndStatus(String name, TestCategory category, TestStatus status, int limit, int from) throws DaoOperationException;

    List<Test> findByNameAndParentCategoryAndStatus(String name, TestCategory category, TestStatus status, int limit, int from) throws DaoOperationException;

    List<Test> findByNameAndStatus(String name, TestStatus status, int limit, int from) throws DaoOperationException;

    List<Test> findByCategoryAndStatus(TestCategory category, TestStatus status, int limit, int from) throws DaoOperationException;

    List<Test> findByParentCategoryAndStatus(TestCategory category, TestStatus status, int limit, int from) throws DaoOperationException;

    List<Test> findTests(TestStatus status, int limit, int from) throws DaoOperationException;

    void saveCharacteristics(Collection<Characteristic> characteristics, Integer testId) throws DaoOperationException;

    Set<Characteristic> findCharacteristics(Integer testId) throws DaoOperationException;

    int findTestAmountByStatus(TestStatus status) throws DaoOperationException;

    int findTestAmountByStatus(TestStatus status, Integer authorId) throws DaoOperationException;

    int findTestAmount(Integer authorId) throws DaoOperationException;

    void updateStatus(Integer testId, TestStatus status) throws DaoOperationException;

    int findAmountByNameAndCategoryAndStatus(String name, TestCategory category, TestStatus status) throws DaoOperationException;
    int findAmountByNameAndParentCategoryAndStatus(String name, TestCategory category, TestStatus status) throws DaoOperationException;
    int findAmountByNameAndStatus(String name, TestStatus status) throws DaoOperationException;
    int findAmountByCategoryAndStatus(TestCategory category, TestStatus status) throws DaoOperationException;
    int findAmountByParentCategoryAndStatus(TestCategory category, TestStatus status) throws DaoOperationException;
}
