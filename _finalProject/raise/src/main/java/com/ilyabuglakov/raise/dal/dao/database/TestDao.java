package com.ilyabuglakov.raise.dal.dao.database;

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

    void saveCharacteristics(Collection<Characteristic> characteristics, Integer testId) throws DaoOperationException;

    Set<Characteristic> getCharacteristics(Integer testId) throws DaoOperationException;

    Integer getTestAmountByStatus(TestStatus status) throws DaoOperationException;

    Integer getTestAmount() throws DaoOperationException;

    Integer getTestAmountByStatus(TestStatus status, Integer authorId) throws DaoOperationException;

    Integer getTestAmount(Integer authorId) throws DaoOperationException;

    Integer getNewTestAmount() throws DaoOperationException;

    Integer getNewTestAmount(Integer authorId) throws DaoOperationException;

    List<Test> getTestsByStatus(TestStatus status, int startFrom, int itemsAmount) throws DaoOperationException;

    List<Test> getTests(int startFrom, int itemsAmount) throws DaoOperationException;

    List<Test> getNewTests(int startFrom, int itemsAmount) throws DaoOperationException;

    void updateStatus(Integer testId, TestStatus status) throws DaoOperationException;
}
