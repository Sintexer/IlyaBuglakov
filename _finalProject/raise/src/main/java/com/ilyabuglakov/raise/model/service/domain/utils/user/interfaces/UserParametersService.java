package com.ilyabuglakov.raise.model.service.domain.utils.user.interfaces;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.UserTestResult;
import com.ilyabuglakov.raise.model.dto.UserCharacteristic;

import java.util.List;

public interface UserParametersService {
    void saveResult(UserTestResult userTestResult) throws PersistentException;

    int getResultsAmount(Integer userId) throws PersistentException;

    List<UserCharacteristic> getUserCharacteristics(Integer userId) throws DaoOperationException;
}
