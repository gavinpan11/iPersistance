package com.gavin11.ipersistance.session;

import com.gavin11.ipersistance.config.Configuration;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.factory.impl
 * @description DefaultSqlSessionFactory <br>
 * @date 2021/4/2 7:43 <br>
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
