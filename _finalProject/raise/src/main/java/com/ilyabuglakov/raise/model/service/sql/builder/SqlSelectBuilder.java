package com.ilyabuglakov.raise.model.service.sql.builder;

/**
 * SqlSelectBuilder - an implementation of SqlBuilder class, that builds SELECT query.
 * Can accept sql "WHERE" parameters and use them in a query.
 * If passed limit attributes to addLimit() - will also add LIMIT to  query.
 */
public class SqlSelectBuilder extends SqlQueryBuilder {

    public SqlSelectBuilder(String tableName) {
        super(tableName);
    }

    @Override
    public String build() {
        return String.join(" ",
                "SELECT",
                generateFieldsString(false),
                "FROM",
                tableName,
                generateWhereString(),
                generateLimit())
                .trim();
    }

}
