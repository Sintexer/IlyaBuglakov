package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.model.dto.UserParametersDto;

public interface UserService extends Service{
    public UserParametersDto getUserParameters(String email) throws PersistentException;
}
