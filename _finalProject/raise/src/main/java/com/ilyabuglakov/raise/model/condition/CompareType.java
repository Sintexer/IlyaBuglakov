package com.ilyabuglakov.raise.model.condition;

public enum CompareType {
    EQUALS("=")
    ;

    private String value;

    CompareType(String value) {
        this.value = value;
    }
}
