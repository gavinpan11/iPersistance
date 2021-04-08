package com.gavin11.ipersistance.session;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.factory
 * @description SqlSessionFactory <br>
 * @date 2021/4/2 7:43 <br>
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
