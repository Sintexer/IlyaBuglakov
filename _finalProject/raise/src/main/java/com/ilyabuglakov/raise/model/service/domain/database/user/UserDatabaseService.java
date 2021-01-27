package com.ilyabuglakov.raise.model.service.domain.database.user;

import com.ilyabuglakov.raise.dal.dao.interfaces.UserDao;
import com.ilyabuglakov.raise.dal.exception.PersistentException;
import com.ilyabuglakov.raise.dal.transaction.Transaction;
import com.ilyabuglakov.raise.domain.User;
import com.ilyabuglakov.raise.model.DaoType;
import com.ilyabuglakov.raise.model.dto.UserCharacteristic;
import com.ilyabuglakov.raise.model.dto.UserInfoDto;
import com.ilyabuglakov.raise.model.dto.UserParametersDto;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.domain.TestCommentService;
import com.ilyabuglakov.raise.model.service.domain.UserService;
import com.ilyabuglakov.raise.model.service.domain.database.DatabaseService;
import com.ilyabuglakov.raise.model.service.domain.database.TestCommentDatabaseService;
import com.ilyabuglakov.raise.model.service.domain.utils.test.TestDatabaseReadService;
import com.ilyabuglakov.raise.model.service.domain.utils.test.interfaces.TestReadService;
import com.ilyabuglakov.raise.model.service.domain.utils.user.UserParametersDatabaseService;
import com.ilyabuglakov.raise.model.service.domain.utils.user.UserTransactionSearch;
import com.ilyabuglakov.raise.model.service.domain.utils.user.interfaces.UserParametersService;
import com.ilyabuglakov.raise.model.service.domain.utils.user.interfaces.UserSearchService;
import com.ilyabuglakov.raise.model.service.user.UserInfoChangeService;
import com.ilyabuglakov.raise.model.service.validator.UserCredentialsValidator;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
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
        TestCommentService testCommentService = new TestCommentDatabaseService(transaction);
        int commentsAmount = testCommentService.getCommentsAmount(user.getEmail());
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

    @Override
    public ResponseEntity changeUserInfo(UserInfoDto userInfoDto) throws PersistentException {
        UserInfoChangeService userInfoChangeService = new UserInfoChangeService();
        ResponseEntity responseEntity = new ResponseEntity();
        boolean somethingChanged = false;
        boolean somethingWrong = false;
        if (!userInfoDto.getName().isEmpty()
                && !userInfoDto.getUser().getName().equals(userInfoDto.getName())) {
            responseEntity.setAttribute("nameChanged",
                    userInfoChangeService.changeName(userInfoDto.getUser(), userInfoDto.getName()));
            somethingChanged = true;
        }
        if (!userInfoDto.getSurname().isEmpty()
                && !userInfoDto.getUser().getSurname().equals(userInfoDto.getSurname())) {
            responseEntity.setAttribute("surnameChanged",
                    userInfoChangeService.changeSurname(userInfoDto.getUser(), userInfoDto.getSurname()));
            somethingChanged = true;
        }

        if (!userInfoDto.getOldPassword().isEmpty()
                && !userInfoDto.getNewPassword().isEmpty()
                && !userInfoDto.getNewPasswordRepeat().isEmpty()) {
            UserCredentialsValidator userCredentialsValidator = new UserCredentialsValidator();
            if (userCredentialsValidator.isCorrectOldPassword(userInfoDto.getUser(), userInfoDto.getOldPassword())) {
                responseEntity.setAttribute(
                        "passwordChanged",
                        userInfoChangeService.changePassword(
                                userInfoDto.getUser(),
                                userInfoDto.getNewPassword(),
                                userInfoDto.getNewPasswordRepeat()));
                somethingChanged = true;
                log.debug(() -> "Password changed :" + userInfoDto.getUser().getPassword());
            } else {
                somethingWrong = true;
                responseEntity.setAttribute("incorrectOldPassword", true);
            }
        } else {
            responseEntity.setAttribute("incorrectOldPassword", true);
        }

        if (somethingChanged) {
            updateUser(userInfoDto.getUser());
            transaction.commit();
        }

        responseEntity.setAttribute("userParameters", getUserParameters(userInfoDto.getUser().getId()));
        responseEntity.setAttribute("somethingChanged", somethingChanged);
        responseEntity.setAttribute("somethingWrong", somethingWrong);
        return responseEntity;
    }
}
