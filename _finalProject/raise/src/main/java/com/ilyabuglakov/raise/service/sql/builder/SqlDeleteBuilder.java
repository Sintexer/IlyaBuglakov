package com.ilyabuglakov.raise.service.sql.builder;

/**
 * SqlDeleteBuilder - an implementation of SqlBuilder class, that builds DELETE query.
 * Can accept sql "WHERE" parameters and use them in a query.
 */
public class SqlDeleteBuilder extends SqlQueryBuilder {
    public SqlDeleteBuilder(String tableName) {
        super(tableName);
    }

    @Override
    public String build() {
        return String.join(" ", "DELETE FROM", tableName, generateWhereString()).trim();
    }
}
