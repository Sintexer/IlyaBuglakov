package com.ilyabuglakov.raise.model.service.domain.database;

import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.dto.UserCharacteristic;
import com.ilyabuglakov.raise.model.dto.UserParametersDto;
import com.ilyabuglakov.raise.model.service.domain.Service;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import com.ilyabuglakov.raise.model.service.domain.user.UserParametersDatabaseService;
import com.ilyabuglakov.raise.model.service.domain.user.UserTransactionSearch;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserParametersService;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserSearchService;

import javax.persistence.PersistenceException;
import java.util.List;

public class UserDatabaseService extends DatabaseService implements Service {
    public UserDatabaseService(Transaction transaction) {
        super(transaction);
    }

    public UserParametersDto getUserParameters(String email) throws PersistentException {
        UserParametersDto userParametersDto = null;
        UserSearchService userSearchService = new UserTransactionSearch(transaction);

        User user = userSearchService.findByEmail(email).orElseThrow(() ->
                new PersistenceException("Authorized user not found"));

        UserParametersService userParametersService = new UserParametersDatabaseService(transaction);
        int answeredTestsAmount = userParametersService.getResultsAmount(user.getId());
        TestReadService testReadService = new TestDatabaseReadService(transaction);
        int postedTestsAmount = testReadService.getTestAmount(user.getId());
        //TODO comments amount
        int commentsAmount = 0;
        List<UserCharacteristic> characteristics = userParametersService.getUserCharacteristics(user.getId());

        userParametersDto = UserParametersDto.builder()
                .user(user)
                .userCharacteristics(characteristics)
                .testsSolved(answeredTestsAmount)
                .testsCreated(postedTestsAmount)
                .commentsPosted(commentsAmount)
                .build();
        return userParametersDto;
    }

}
