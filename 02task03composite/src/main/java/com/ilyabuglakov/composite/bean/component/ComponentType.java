package com.ilyabuglakov.composite.bean.component;

public enum ComponentType {
    PARAGRAPH("\n", "\n"),
    SENTENCE(".", ".");

    private final String delimiterPattern;
    private final String delimiter;

    ComponentType(String delimiterPattern, String delimiter) {
        this.delimiterPattern = delimiterPattern;
        this.delimiter = delimiter;
    }

    public String getDelimiterPattern() {
        return delimiterPattern;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
