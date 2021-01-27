package com.ilyabuglakov.raise.model.service.validator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetValidator {

    public boolean hasAllValues(ResultSet resultSet, String... values) throws SQLException {
        for (String value : values) {
            resultSet.getObject(value);
            if (resultSet.wasNull())
                return false;
        }
        return true;
    }

}
