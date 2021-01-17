package com.ilyabuglakov.raise.model.service.test;

import com.ilyabuglakov.raise.domain.Answer;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.domain.Test;

public class TestService {

    public Test createBacklinks(Test sourceTest){
        //TODO new test object
        for(Question question : sourceTest.getQuestions()){
            for(Answer answer : question.getAnswers()){
                answer.setQuestion(question);
            }
            question.setTest(sourceTest);
        }
        return sourceTest;
    }

}
