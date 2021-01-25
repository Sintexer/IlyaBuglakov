package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.dto.TestInfo;

import java.util.List;
import java.util.Optional;

public interface TestService extends Service {
    void changeTestStatus(Integer testId, TestStatus status) throws PersistentException;

    Optional<Test> getTest(Integer id) throws PersistentException;

    List<TestInfo> getNewTestInfos(int limit, int from) throws PersistentException;

    List<TestInfo> getNewTestInfosByPage(int limit, int from) throws PersistentException;

    int getTestAmount() throws PersistentException;

    int getNewTestAmount() throws PersistentException;

    int getTestAmount(Integer authorId) throws PersistentException;

    int getNewTestAmount(Integer authorId) throws PersistentException;
}
