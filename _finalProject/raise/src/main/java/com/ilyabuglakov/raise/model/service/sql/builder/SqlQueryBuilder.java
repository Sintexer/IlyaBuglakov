package com.ilyabuglakov.raise.model.service.sql.builder;

import com.ilyabuglakov.raise.model.service.property.ApplicationProperties;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SqlQueryBuilder is an abstract class, used as base class for
 * SqlBuilders. Implements helpful for its children methods.
 */
public abstract class SqlQueryBuilder {

    protected String tableName;
    private Map<String, String> fieldValues = new LinkedHashMap<>();
    private Map<String, String> whereValues = new LinkedHashMap<>();

    private String fieldQuoteType;
    private String stringQuoteType;

    public SqlQueryBuilder(String tableName) {
        fieldQuoteType = ApplicationProperties.getProperty("db.field.quoteType");
        stringQuoteType = ApplicationProperties.getProperty("db.string.quoteType");
        this.tableName = surroundWith(tableName, fieldQuoteType);
    }

    public abstract String build();

    public void setTableName(String tableName) {
        this.tableName = surroundWith(tableName, fieldQuoteType);
    }

    public void addField(String field) {
        fieldValues.put(field, null);
    }

    public void addField(String field, Object value) {
        if (value == null)
            addField(field);
        else
            fieldValues.put(field, value.toString());
    }

    public void addWhere(String field, Object value) {
        whereValues.put(field, value.toString());
    }

    public void removeValue(String field) {
        fieldValues.remove(field);
    }

    protected String generateFieldsString(boolean brackets) {
        if (fieldValues.isEmpty())
            return "*";
        String leftBracket = "";
        String rightBracket = "";
        if (brackets) {
            leftBracket = "(";
            rightBracket = ")";
        }
        return leftBracket
                + fieldValues.keySet().stream()
                .map(str -> surroundWith(str, fieldQuoteType))
                .collect(Collectors.joining(", "))
                + rightBracket;
    }

    protected String generateValuesString() {
        return "VALUES (" +
                fieldValues.values().stream()
                        .map(str -> surroundWith(str, stringQuoteType))
                        .collect(Collectors.joining(", "))
                + ")";
    }

    protected String generateWhereString() {
        if (whereValues.isEmpty())
            return "";
        return "WHERE " + whereValues.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + surroundWith(entry.getValue(), stringQuoteType))
                .collect(Collectors.joining(", "));
    }

    protected String generateSetString() {
        return "SET " + fieldValues.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + surroundWith(entry.getValue(), stringQuoteType))
                .collect(Collectors.joining(", "));
    }

    protected String surroundWith(String source, String surrounder) {
        return surrounder + source + surrounder;
    }

}
