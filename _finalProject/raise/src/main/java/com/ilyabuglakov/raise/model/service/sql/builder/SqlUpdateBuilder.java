package com.ilyabuglakov.raise.model.service.sql.builder;

/**
 * SqlUpdateBuilder - an implementation of SqlBuilder class, that builds UPDATE query.
 * Can accept sql "WHERE" parameters and use them in a query.
 */
public class SqlUpdateBuilder extends SqlQueryBuilder {
    public SqlUpdateBuilder(String tableName) {
        super(tableName);
    }

    @Override
    public String build() {
        return String.join(" ", "UPDATE", tableName, generateSetString(), generateWhereString()).trim();
    }
}
