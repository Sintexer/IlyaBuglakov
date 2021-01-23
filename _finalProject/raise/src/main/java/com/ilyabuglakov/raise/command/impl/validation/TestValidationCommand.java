package com.ilyabuglakov.raise.command.impl.validation;

import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.model.service.validator.TestValidator;

import javax.servlet.http.HttpServletRequest;

public class TestValidationCommand implements ValidationCommand<Test>{
    @Override
    public boolean execute(Test test, HttpServletRequest request) {
        TestValidator validator = new TestValidator();
        if(test == null){
            request.setAttribute("wrongTestFormat", true);
            return false;
        }
        if(!validator.isValidTestName(test.getTestName())){
            request.setAttribute("invalidTestName", true);
            return false;
        }
        if(!test.getQuestions().stream().allMatch(validator::isValidQuestion) || test.getQuestions().isEmpty()){
            request.setAttribute("invalidQuestions", true);
            return false;
        }
        return true;
    }
}
