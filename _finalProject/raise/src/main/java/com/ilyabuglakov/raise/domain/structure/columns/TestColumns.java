package com.ilyabuglakov.raise.domain.structure.columns;

public enum TestColumns {
    TEST_NAME,
    DIFFICULTY;

    String getName(){
        return name().toLowerCase();
    }
}