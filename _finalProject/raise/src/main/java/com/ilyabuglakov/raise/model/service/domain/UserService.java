package com.ilyabuglakov.raise.model.service.domain;

import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.dto.UserInfoDto;
import com.ilyabuglakov.raise.model.dto.UserParametersDto;
import com.ilyabuglakov.raise.model.response.ResponseEntity;

import java.util.Optional;

public interface UserService extends Service {
    UserParametersDto getUserParameters(Integer userId) throws PersistentException;

    UserParametersDto getUserParameters(String email) throws PersistentException;

    Optional<User> getUser(String email) throws PersistentException;

    void updateUser(User user) throws PersistentException;

    ResponseEntity changeUserInfo(UserInfoDto userInfoDto) throws PersistentException;
}
