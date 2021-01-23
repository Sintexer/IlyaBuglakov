package com.ilyabuglakov.raise.model;

import lombok.Getter;

@Getter
public enum LocaleType {
    RUSSIAN("ru_RU", "Русский"),
    ENGLISH_US("en_US", "English US"),
    SWEDISH("sv_SE", "Svenska");

    private final String locale;
    private final String viewName;

    LocaleType(String locale, String viewName) {
        this.locale = locale;
        this.viewName = viewName;
    }
}
