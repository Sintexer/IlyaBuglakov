package com.ilyabuglakov.composite.bean.component;

/**
 * ComponentType is enum used to represent various types of Component's
 * Each enum value stores regex pattern, which is used to parse text and get word, sentence etc.

 */
public enum ComponentType {
    //TODO delete delimiter
    TEXT("", ""),
    PARAGRAPH("(?m)(\\n*)(\\t*[^\\t]+[.?!]+)", "\n"),
    SENTENCE("(?m)(\\s*)([^.?!]+[.?!]+)?", "."),
    LEXEME("(?m)(\\s*)(.*?)(\\s+|$)"," "),
    WORD("([\\s!?.,]*)([^\\s.,!?]+)?([\\s!?.,]*)", " "),
    SYMBOL("(?s)(.)", ""),
    DELIMITER("[!?\\-,.\\s]+", "");

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
