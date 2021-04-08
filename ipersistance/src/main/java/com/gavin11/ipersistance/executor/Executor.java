package com.gavin11.ipersistance.executor;

import com.gavin11.ipersistance.config.Configuration;
import com.gavin11.ipersistance.config.MappedStatement;

import java.util.List;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.executor
 * @description Executor <br>
 * @date 2021/4/3 10:56 <br>
 */
public interface Executor {

    /**
     * 读操作
     * @param configuration 配置信息
     * @param mappedStatement sql信息
     * @param params 查询条件
     */
    <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    /**
     * 写操作
     * @param configuration 配置信息
     * @param mappedStatement sql信息
     * @param params 查询条件
     * @return 受影响的行
     */
    int execute(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
