package com.gavin11.ipersistance.test.dao;

import com.gavin11.ipersistance.io.Resources;
import com.gavin11.ipersistance.session.SqlSession;
import com.gavin11.ipersistance.session.SqlSessionFactory;
import com.gavin11.ipersistance.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.test.dao
 * @description UserMapperTest <br>
 * @date 2021/4/2 21:17 <br>
 */
@Deprecated
public class UserMapperTest {

    @Test
    public void test0() throws Exception {
        try (
                InputStream in = Resources.getResourceAsStream("db-config.xml");
                ) {
            SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build(in);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.selectOne("");
        }

    }
}
