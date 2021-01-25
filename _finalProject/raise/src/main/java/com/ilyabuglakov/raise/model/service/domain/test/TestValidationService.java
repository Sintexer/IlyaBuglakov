package com.ilyabuglakov.raise.model.service.domain.test;

import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.response.ResponseEntity;
import com.ilyabuglakov.raise.model.service.validator.TestValidator;

public class TestValidationService {

    public ResponseEntity validateTest(Test test){
        TestValidator validator = new TestValidator();
        ResponseEntity responseEntity = new ResponseEntity();

        if (test == null) {
            responseEntity.setAttribute("wrongTestFormat", true);
            responseEntity.setErrorOccurred(true);
        } else if (!validator.isValidTestName(test.getTestName())) {
            responseEntity.setAttribute("invalidTestName", true);
            responseEntity.setErrorOccurred(true);
        } else if (test.getQuestions() == null
                || !test.getQuestions().stream().allMatch(validator::isValidQuestion)
                || test.getQuestions().isEmpty()) {
            responseEntity.setAttribute("invalidQuestions", true);
            responseEntity.setErrorOccurred(true);
        }
        return responseEntity;
    }

}
