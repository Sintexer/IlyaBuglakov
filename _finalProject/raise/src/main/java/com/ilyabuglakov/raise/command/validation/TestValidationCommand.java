package com.ilyabuglakov.raise.command.validation;

import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.service.validator.TestValidator;

import javax.servlet.http.HttpServletRequest;

public class TestValidationCommand implements ValidationCommand<Test>{
    @Override
    public boolean execute(Test test, HttpServletRequest request) {
        TestValidator validator = new TestValidator();
        String errorAttributeName = "testError";
        if(test == null){
            request.setAttribute(errorAttributeName, "Wrong test format");
            return false;
        }
        if(!validator.isValidTestName(test.getTestName())){
            request.setAttribute(errorAttributeName, "Invalid testName.Shouldn't be empty, or contain any special characters. Must start with a letter");
            return false;
        }
        if(!test.getQuestions().stream().allMatch(validator::isValidQuestion)){
            request.setAttribute(errorAttributeName, "Some of test questions are invalid");
            return false;
        }
        return true;
    }
}
