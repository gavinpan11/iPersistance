package com.gavin11.ipersistance.test.jdbc;

import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.study.mybatis.ipersistance.test.jdbc
 * @description TestJdbcBase <br>
 * @date 2021/4/3 15:46 <br>
 */
public class TestJdbcBase {

    protected Connection connection = null;
    protected Statement statement = null;
    protected PreparedStatement ps = null;
    protected ResultSet rs = null;

    @Before
    public void init() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://192.168.0.101:3306/ipersistance?useUnicode=true&characterEncoding=utf8", "root", "123456");
    }

    @After
    public void release() throws Exception {
        if (rs != null) {
            rs.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (ps != null) {
            ps.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}
