package com.gavin11.ipersistance.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.utils
 * @description JdbcResourceUtils <br>
 * @date 2021/4/3 11:33 <br>
 */
public class JdbcResourceUtils {

    private JdbcResourceUtils() {}

    public static void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public static void close(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet rs) throws SQLException {
        close(rs);
        close(statement);
        close(connection);
    }
}
