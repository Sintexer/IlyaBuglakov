package com.ilyabuglakov.raise.domain.structure.columns;

public enum AnswerColumns {
    QUESTION_ID,
    CORRECT,
    CONTENT;

    String getName(){
        return name().toLowerCase();
    }
}