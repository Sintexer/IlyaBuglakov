package com.ilyabuglakov.raise.service;

import com.ilyabuglakov.raise.service.property.ApplicationProperties;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlQueryBuilder {

    private String tableName;
    private Map<String, String> fieldValues = new LinkedHashMap<>();
    private Map<String, String> whereValues = new LinkedHashMap<>();

    private String fieldQuoteType;
    private String stringQuoteType;

    public SqlQueryBuilder(String tableName) {
        fieldQuoteType = ApplicationProperties.getInstance().getProperty("db.field.quoteType");
        stringQuoteType = ApplicationProperties.getInstance().getProperty("db.string.quoteType");
        this.tableName = surroundWith(tableName, fieldQuoteType);
    }

    public void setTableName(String tableName) {
        this.tableName = surroundWith(tableName, fieldQuoteType);
    }

    public void addField(String field){
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

    public String buildInsertQuery() {
        return String.join(" ", "INSERT INTO", tableName, generateFieldsString(true), generateValuesString()).trim();
    }

    public String buildSelectQuery() {
        return String.join(" ", "SELECT", generateFieldsString(true), "FROM", tableName, generateWhereString()).trim();
    }

    public String buildUpdateQuery(){
        return String.join(" ", "UPDATE", tableName, generateSetString(), generateWhereString()).trim();
    }

    public String buildDeleteQuery(){
        return String.join(" ", "DELETE FROM", tableName, generateWhereString()).trim();
    }

    private String generateFieldsString(boolean brackets) {
        if (fieldValues.isEmpty())
            return "*";
        String leftBracket="";
        String rightBracket="";
        if(brackets){
            leftBracket = "(";
            rightBracket = ")";
        }
        return leftBracket
                + fieldValues.keySet().stream()
                .map(str -> surroundWith(str, fieldQuoteType))
                .collect(Collectors.joining(", "))
                + rightBracket;
    }

    private String generateValuesString() {
        return "VALUES (" +
                fieldValues.values().stream()
                        .map(str -> surroundWith(str, stringQuoteType))
                        .collect(Collectors.joining(", "))
                + ")";
    }

    private String generateWhereString() {
        if (whereValues.isEmpty())
            return "";
        return "WHERE " + whereValues.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + surroundWith(entry.getValue(), stringQuoteType))
                .collect(Collectors.joining(", "));
    }

    private String generateSetString(){
        return "SET " + fieldValues.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + surroundWith(entry.getValue(), stringQuoteType))
                .collect(Collectors.joining(", "));
    }

    private String surroundWith(String source, String surrounder) {
        return surrounder + source + surrounder;
    }


}
