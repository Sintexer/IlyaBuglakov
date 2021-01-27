package com.ilyabuglakov.raise.model.service.validator;

import com.ilyabuglakov.raise.domain.Answer;
import com.ilyabuglakov.raise.domain.Question;
import com.ilyabuglakov.raise.domain.Test;
import com.ilyabuglakov.raise.domain.type.Characteristic;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Set;
import java.util.regex.Pattern;

public class TestValidator {

    public static String testNamePattern = "^[^\\d',.-][^\\n_!¡?÷?¿\\/\\\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$";

    public boolean isValidTestName(String testName) {
        if (testName == null)
            return false;
        Pattern pattern = Pattern.compile(testNamePattern);
        return pattern.matcher(testName).matches();
    }

    public boolean isValidCharacteristics(Set<Characteristic> characteristics) {
        return characteristics != null
                && !characteristics.isEmpty()
                && ObjectUtils.allNotNull(characteristics);
    }

    public boolean isValidQuestion(Question question) {
        if (question == null || question.getContent() == null || question.getContent().isEmpty())
            return false;
        boolean validAnswers = question.getAnswers().stream()
                .allMatch(this::isValidAnswer);
        if (!validAnswers)
            return false;
        return question.getAnswers().size() > 1
                && question.getAnswers().stream().anyMatch(Answer::isCorrect);
    }

    public boolean isValidAnswer(Answer answer) {
        return answer != null && !answer.getContent().isEmpty();
    }

}
