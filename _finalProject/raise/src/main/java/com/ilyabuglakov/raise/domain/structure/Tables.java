package com.ilyabuglakov.raise.domain.structure;

public enum Tables {
    USR,
    TEST,
    QUESTION,
    ANSWER,
    ROLE,
    USER_ROLES,
    ROLE_PERMISSIONS,
    USER_TEST_RESULT,
    TEST_COMMENT,
    TEST_CHARACTERISTIC;

    String getName() {
        return name().toLowerCase();
    }
}
