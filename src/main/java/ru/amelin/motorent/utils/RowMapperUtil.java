package ru.amelin.motorent.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperUtil {

    public static Integer getIntValue(ResultSet rs, String fieldName) throws SQLException {
        rs.getObject(fieldName);
        if (!rs.wasNull()) {
            return rs.getInt(fieldName);
        } else {
            return null;
        }
    }
}
