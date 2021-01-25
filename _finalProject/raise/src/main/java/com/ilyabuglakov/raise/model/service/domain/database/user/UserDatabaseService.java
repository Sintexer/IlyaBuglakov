package com.ilyabuglakov.raise.model.service.domain.database.user;

import com.ilyabuglakov.raise.dal.dao.interfaces.UserDao;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.dto.UserCharacteristic;
import com.ilyabuglakov.raise.model.dto.UserParametersDto;
import com.ilyabuglakov.raise.model.service.domain.UserService;
import com.ilyabuglakov.raise.model.service.domain.database.DatabaseService;
import com.ilyabuglakov.raise.model.service.domain.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.test.interfaces.TestReadService;
import com.ilyabuglakov.raise.model.service.domain.user.UserParametersDatabaseService;
import com.ilyabuglakov.raise.model.service.domain.user.UserTransactionSearch;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserParametersService;
import com.ilyabuglakov.raise.model.service.domain.user.interfaces.UserSearchService;

import java.util.List;
import java.util.Optional;

public class UserDatabaseService extends DatabaseService implements UserService {
    public UserDatabaseService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public Optional<User> getUser(String email) throws PersistentException {
        UserSearchService userSearchService = new UserTransactionSearch(transaction);
        return userSearchService.findByEmail(email);
    }

    @Override
    public void updateUser(User user) throws PersistentException {
        UserDao userDao = (UserDao) transaction.createDao(DaoType.USER);
        userDao.update(user);
        transaction.commit();
    }

    @Override
    public UserParametersDto getUserParameters(Integer userId) throws PersistentException {
        UserSearchService userSearchService = new UserTransactionSearch(transaction);

        Optional<User> userOptional = userSearchService.findById(userId);
        if (!userOptional.isPresent())
            return null;

        return createUserParameters(userOptional.get());
    }

    @Override
    public UserParametersDto getUserParameters(String email) throws PersistentException {
        UserSearchService userSearchService = new UserTransactionSearch(transaction);

        Optional<User> userOptional = userSearchService.findByEmail(email);
        if (!userOptional.isPresent())
            return null;

        return createUserParameters(userOptional.get());
    }

    private UserParametersDto createUserParameters(User user) throws PersistentException {
        UserParametersDto userParametersDto = null;
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
