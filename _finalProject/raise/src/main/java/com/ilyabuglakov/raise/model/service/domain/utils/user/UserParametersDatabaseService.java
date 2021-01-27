package com.ilyabuglakov.raise.model.service.domain.utils.user;

import com.ilyabuglakov.raise.dal.dao.exception.DaoOperationException;
import com.ilyabuglakov.raise.dal.dao.interfaces.TestDao;
import com.ilyabuglakov.raise.dal.dao.interfaces.UserTestResultDao;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.UserTestResult;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.dto.UserCharacteristic;
import com.ilyabuglakov.raise.model.service.domain.TransactionWebService;
import com.ilyabuglakov.raise.model.service.domain.utils.user.interfaces.UserParametersService;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class UserParametersDatabaseService extends TransactionWebService implements UserParametersService {
    public UserParametersDatabaseService(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void saveResult(UserTestResult userTestResult) throws PersistentException {
        UserTestResultDao userTestResultDao = (UserTestResultDao) transaction.createDao(DaoType.USER_TEST_RESULT);
        try {
            Optional<UserTestResult> prevResult =
                    userTestResultDao.getByUserIdAndTestId(userTestResult.getUser().getId(),
                            userTestResult.getTest().getId());
            if (prevResult.isPresent()) {
                userTestResult.setId(prevResult.get().getId());
                if (prevResult.get().getResult() < userTestResult.getResult()) {
                    userTestResultDao.update(userTestResult);
                }
            } else {
                userTestResultDao.create(userTestResult);
            }
        } catch (DaoOperationException e) {
            transaction.rollback();
            throw new DaoOperationException("Couldn't save result", e);
        }
    }

    @Override
    public int getResultsAmount(Integer userId) throws PersistentException {
        UserTestResultDao userTestResultDao = (UserTestResultDao) transaction.createDao(DaoType.USER_TEST_RESULT);
        return userTestResultDao.getResultAmount(userId);
    }

    @Override
    public List<UserCharacteristic> getUserCharacteristics(Integer userId) throws DaoOperationException {
        UserTestResultDao userTestResultDao = (UserTestResultDao) transaction.createDao(DaoType.USER_TEST_RESULT);
        TestDao testDao = (TestDao) transaction.createDao(DaoType.TEST);

        List<UserTestResult> userTestResults = userTestResultDao.getUserTestResults(userId);
        Map<Characteristic, Double> characteristicResults = Stream.of(Characteristic.values())
                .collect(Collectors.toMap(Function.identity(), characteristic -> 0.0));

        for (UserTestResult utr : userTestResults) {
            testDao.getCharacteristics(utr.getTest().getId())
                    .forEach(characteristic ->
                            characteristicResults.merge(characteristic, (double) utr.getResult(), Double::sum));
        }
        log.info("User characteristics values: " + characteristicResults);

        return characteristicResults.entrySet().stream()
                .map(entry -> new UserCharacteristic(entry.getKey(), entry.getValue() / 100))
                .collect(Collectors.toList());
    }
}
