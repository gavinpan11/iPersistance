package com.gavin11.ipersistance.test.mapper;

import com.gavin11.ipersistance.test.pojo.User;

import java.util.List;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.test.mapper
 * @description UserMapper <br>
 * @date 2021/4/2 21:04 <br>
 */
public interface UserMapper {

    List<User> findAll();

    User findByIdName(User user);

    User simpleFindByIdName(String username, int id);

    int insertOne(User user);

    int updateById(User user);

    int deleteById(User user);
}
