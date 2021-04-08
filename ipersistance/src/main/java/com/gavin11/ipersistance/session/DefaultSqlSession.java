package com.gavin11.ipersistance.session;

import com.gavin11.ipersistance.exception.NonSupportedOperationException;
import com.gavin11.ipersistance.exception.WrongResultException;
import com.gavin11.ipersistance.executor.Executor;
import com.gavin11.ipersistance.executor.SimpleExecutor;
import com.gavin11.ipersistance.config.Configuration;
import com.gavin11.ipersistance.config.MappedStatement;
import com.gavin11.ipersistance.enums.SqlCommandType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.session
 * @description DefaultSqlSession <br>
 * @date 2021/4/3 10:40 <br>
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private final Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new SimpleExecutor();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> results = selectList(statementId, params);
        int size = results.size();
        if (size > 1) {
            throw new WrongResultException("too many results!");
        }
        return size == 1 ? (T) results.get(0) : null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object... params) throws Exception {
        return executor.query(configuration, configuration.getStatements().get(statementId), params);
    }

    @Override
    public int insert(String statementId, Object... params) throws Exception {
        return executor.execute(configuration, configuration.getStatements().get(statementId), params);
    }

    @Override
    public int update(String statementId, Object... params) throws Exception {
        return executor.execute(configuration, configuration.getStatements().get(statementId), params);
    }

    @Override
    public int delete(String statementId, Object... params) throws Exception {
        return executor.execute(configuration, configuration.getStatements().get(statementId), params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                DefaultSqlSession.class.getClassLoader(),
                new Class[]{clazz},
                (proxy, method, args) -> {
                    String className = method.getDeclaringClass().getName();
                    String methodName = method.getName();
                    String  statementId = className + "." + methodName;
                    MappedStatement mappedStatement = configuration.getStatements().get(statementId);
                    SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
                    switch (sqlCommandType) {
                        case SELECT:
                            Type grt = method.getGenericReturnType();
                            if (grt instanceof ParameterizedType) {  // 返回类型是否参数化, 以此来简单判断返回值是集合或者简单的对象
                                // 返回值是集合, 调用selectList方法
                                return selectList(statementId, args);
                            } else {
                                // 返回值是简单对象, 调用selectOne方法
                                return selectOne(statementId, args);
                            }
                        case INSERT:
                            return insert(statementId, args);
                        case UPDATE:
                            return update(statementId, args);
                        case DELETE:
                            return delete(statementId, args);
                        default:
                            throw new NonSupportedOperationException("operation not support");
                    }
                }
        );
    }
}
