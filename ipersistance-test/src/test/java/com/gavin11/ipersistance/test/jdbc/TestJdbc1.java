package com.gavin11.ipersistance.test.jdbc;

import com.gavin11.ipersistance.test.pojo.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.study.mybatis.ipersistance.test.jdbc
 * @description TestJdbc1 <br>
 * @date 2021/4/3 15:50 <br>
 */
public class TestJdbc1 extends TestJdbcBase {

    @Test
    public void test0() throws Exception {
        ps = connection.prepareStatement("select id,username,password,birthday from user");
        rs = ps.executeQuery();

        List<User> users = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String birthday = rs.getString("birthday");

            users.add(new User(id, username, password, birthday));
        }

        users.forEach(System.out::println);
    }

    @Test
    public void test1() throws Exception {
        ps = connection.prepareStatement("select id,username,password,birthday from user where id = ? and username = ?");
        ps.setInt(1, 1);
        ps.setString(2, "lucy");

        rs = ps.executeQuery();

        List<User> users = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String birthday = rs.getString("birthday");

            users.add(new User(id, username, password, birthday));
        }

        users.forEach(System.out::println);
    }
}
