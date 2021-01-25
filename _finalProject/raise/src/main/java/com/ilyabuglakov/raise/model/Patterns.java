package com.ilyabuglakov.raise.model;

import lombok.Getter;

@Getter
public enum Patterns {
    PASSWORD(".{" + FormConstants.PASSWORD_MIN.getValue() + "," + FormConstants.PASSWORD_MAX.getValue() + "}"),

    ;


    private String pattern;

    Patterns(String pattern) {
        this.pattern = pattern;
    }
}
