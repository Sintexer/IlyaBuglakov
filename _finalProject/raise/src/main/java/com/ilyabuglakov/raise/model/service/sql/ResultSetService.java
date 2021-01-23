package com.ilyabuglakov.raise.model.service.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetService {

    public List<String> getAllStringsByName(ResultSet resultSet, String columnName) throws SQLException {
        List<String> strings = new ArrayList<>();
        while (resultSet.next()){
            strings.add(resultSet.getString(columnName));
        }
        return strings;
    }

}
