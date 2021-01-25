package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.model.dto.TestResultDto;
import com.ilyabuglakov.raise.model.dto.UserCharacteristic;

import java.util.List;

public interface UserTestResultService extends Service{
    void saveResult(TestResultDto testResultDto, String email) throws PersistentException;
    int getResultsAmount(Integer userId) throws PersistentException;
    List<UserCharacteristic> getUserCharacteristics(Integer userId) throws PersistentException;
}
