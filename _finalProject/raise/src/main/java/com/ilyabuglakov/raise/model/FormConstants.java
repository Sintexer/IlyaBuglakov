package com.ilyabuglakov.raise.model;

import lombok.Getter;

public enum FormConstants {
    EMAIL_LENGTH(256),
    NAME_LENGTH(40),
    SURNAME_LENGTH(80),
    PASSWORD_MIN(5),
    PASSWORD_MAX(256);
    @Getter
    private final int value;

    FormConstants(int value){
        this.value = value;
    }
}
