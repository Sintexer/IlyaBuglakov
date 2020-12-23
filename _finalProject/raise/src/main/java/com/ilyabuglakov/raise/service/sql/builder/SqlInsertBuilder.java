package com.ilyabuglakov.raise.service.sql.builder;

/**
 * SqlInsertBuilder - an implementation of SqlBuilder class, that builds INSERT query.
 * Accepts sql "VALUES" parameters and use them in a query.
 */
public class SqlInsertBuilder extends SqlQueryBuilder {

    public SqlInsertBuilder(String tableName) {
        super(tableName);
    }

    @Override
    public String build() {
        return String.join(" ", "INSERT INTO", tableName, generateFieldsString(true), generateValuesString()).trim();
    }

}
