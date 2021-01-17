package com.ilyabuglakov.raise.domain.structure.columns;

public enum QuestionColumns {
    NAME,
    CONTENT,
    TEST_ID;

    String getName(){
        return name().toLowerCase();
    }
}