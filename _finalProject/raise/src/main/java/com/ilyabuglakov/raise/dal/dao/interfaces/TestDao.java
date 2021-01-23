package com.ilyabuglakov.raise.dal.dao.interfaces;

import com.ilyabuglakov.raise.dal.dao.Dao;
import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.condition.SearchCondition;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TestDao extends Dao<Test> {
    List<Test> getTests(int startFrom, int itemsAmount) throws DaoOperationException;
    List<Test> getNewTests(int startFrom, int itemsAmount) throws DaoOperationException;
    Integer getTestAmount() throws DaoOperationException;
    Integer getTestAmount(Integer authorId) throws DaoOperationException;
    Integer getNewTestAmount() throws DaoOperationException;
    Integer getNewTestAmount(Integer authorId) throws DaoOperationException;
    void saveCharacteristics(Collection<Characteristic> characteristics, Integer testId) throws DaoOperationException;
    Set<Characteristic> getCharacteristics(Integer testId) throws DaoOperationException;

    void updateStatus(Integer testId, TestStatus status) throws DaoOperationException;
    void findAll(List<SearchCondition> conditions) throws DaoOperationException;
}
