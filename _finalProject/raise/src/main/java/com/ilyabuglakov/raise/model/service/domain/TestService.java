package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.TestStatus;
import com.ilyabuglakov.raise.model.dto.AdvancedTestInfo;
import com.ilyabuglakov.raise.model.dto.TestDto;
import com.ilyabuglakov.raise.model.dto.TestInfo;
import com.ilyabuglakov.raise.model.dto.TestResultDto;
import com.ilyabuglakov.raise.model.dto.TestSearchParametersDto;
import com.ilyabuglakov.raise.model.response.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TestService extends Service {
    ResponseEntity createResult(TestDto testDto) throws PersistentException;

    Optional<Test> createFromJson(String json);

    Optional<TestDto> createDtoFromJson(String json);

    void save(Test test, String authorEmail) throws PersistentException;

    void changeTestStatus(Integer testId, TestStatus status) throws PersistentException;

    Optional<Test> getTest(Integer id) throws PersistentException;

    List<TestInfo> findBySearchParameters(TestSearchParametersDto searchParametersDto) throws PersistentException;

    List<TestInfo> getTestInfosByStatus(TestStatus status, int limit, int from) throws PersistentException;

    List<TestInfo> getTestInfosByStatusAndPage(TestStatus status, int limit, int from) throws PersistentException;

    List<AdvancedTestInfo> getAdvancedTestInfosByStatus(TestStatus status, int limit, int from) throws PersistentException;

    List<AdvancedTestInfo> getAdvancedTestInfosByStatusAndPage(TestStatus status, int limit, int from) throws PersistentException;

    int getTestAmountByStatus(TestStatus status) throws PersistentException;

}
