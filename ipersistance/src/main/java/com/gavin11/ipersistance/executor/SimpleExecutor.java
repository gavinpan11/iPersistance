package com.gavin11.ipersistance.executor;

import com.gavin11.ipersistance.reflection.ClassBuilder;
import com.gavin11.ipersistance.config.BoundSql;
import com.gavin11.ipersistance.config.Configuration;
import com.gavin11.ipersistance.config.MappedStatement;
import com.gavin11.ipersistance.config.ParameterMapping;
import com.gavin11.ipersistance.exception.MappedException;
import com.gavin11.ipersistance.exception.SqlParamException;
import com.gavin11.ipersistance.parser.ParamMappingTokenHandler;
import com.gavin11.ipersistance.parser.SimpleTokenParser;
import com.gavin11.ipersistance.utils.JdbcResourceUtils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.executor
 * @description SimpleExecutor <br>
 * @date 2021/4/3 10:58 <br>
 */
public class SimpleExecutor implements Executor {

    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 获取连接
        Connection connection = configuration.getDs().getConnection();
        // 获取sql语句
        String sql = mappedStatement.getSql();
        // 对sql进行解析
        BoundSql boundSql = getBoundSql(sql);

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> results;
        try {
            ps = connection.prepareStatement(boundSql.getParsedSql());
            // fixme 设置查询参数
            setQueryArgs(ps, mappedStatement, boundSql, params);

            rs = ps.executeQuery();

            // fixme 遍历结果集
            results = getResults(rs, mappedStatement);

        } finally {
            JdbcResourceUtils.close(null, ps, rs);
        }
        return results;
    }

    @Override
    public int execute(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 获取连接
        Connection connection = configuration.getDs().getConnection();
        // 获取sql语句
        String sql = mappedStatement.getSql();
        // 对sql进行解析
        BoundSql boundSql = getBoundSql(sql);

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(boundSql.getParsedSql());
            // fixme 设置查询参数
            setQueryArgs(ps, mappedStatement, boundSql, params);

            boolean executed = ps.execute();
            if (executed) {
                rs = ps.getResultSet();
                return rs.getFetchSize();
            } else {
                return ps.getUpdateCount();
            }
        } finally {
            JdbcResourceUtils.close(null, ps, rs);
        }
    }

    /**
     * 将mapper文件sql中的占位符#{} 替换为jdbc中的占位符?
     * @param sql sql
     * @return boundSql
     */
    private BoundSql getBoundSql(String sql) {
        ParamMappingTokenHandler ptk = new ParamMappingTokenHandler();
        // fixme 此处的openToken和closeToken暂时写死为#{和}
        SimpleTokenParser stp = new SimpleTokenParser("#{", "}", ptk);
        String parsedSql = stp.parse(sql);
        return new BoundSql(parsedSql, ptk.getParameterMappings());
    }

    /**
     * 为PrepareStatement对象设置查询条件
     * @param ps ps
     * @param mappedStatement mappedStatement
     * @param boundSql 解析后的sql信息
     * @param params 查询条件
     * @throws Exception e
     */
    private void setQueryArgs(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object... params) throws Exception {
        List<ParameterMapping> paramMappings = boundSql.getParameterMappings();
        String paramType = mappedStatement.getParamType();
        if (paramType == null) {    // fixme 标签中未设置paramType属性时，认为可能是传入了多个值，只进行了简单的一一对应
            if (paramMappings.size() != params.length) {
                throw new SqlParamException("expected args: " + paramMappings + ", actual args: " + Arrays.toString(params));
            }

            for (int i = 0; i < paramMappings.size(); i++) {
                ps.setObject(i + 1, params[i]);
            }
        } else {    // fixme 传参为paramType中设置的类型时(暂未考虑基本数据类型, 如:java.lang.Integer, java.lang.String等)
            ClassBuilder cb = ClassBuilder.getClassBuilder(paramType);

            Object pojo = params[0];
            for (int i = 0; i < paramMappings.size(); i++) {
                ParameterMapping pm = paramMappings.get(i);

                String fieldName = pm.getContent();
                Method method;
                // fixme 没有做出严谨的判断， 当前只是根据是否有相应的get方法，来判断是否可以为该属性(fieldName)赋值;
                // fixme 同时, 也不支持自动驼峰映射, 此处只是简单的进行了一一对应的处理
                if ((method = cb.getGetMethods().get(fieldName)) == null) {
                    throw new MappedException("no such get method for field: " + fieldName);
                }

                ps.setObject(i + 1, method.invoke(pojo));
            }
        }
    }

    /**
     * 将查询获取的结果集封装到相应的对象中
     * @param rs 查询结果集
     * @param mappedStatement 该条查询对应的参数信息
     * @return 结果
     * @throws Exception e
     */
    @SuppressWarnings("unchecked")
    private <T> List<T> getResults(ResultSet rs, MappedStatement mappedStatement) throws Exception {
        String resultType = mappedStatement.getResultType();
        ClassBuilder cb = ClassBuilder.getClassBuilder(resultType);
        // 获取当前类的所有set方法
        Map<String, Method> setMethods = cb.getSetMethods();

        List<Object> results = new ArrayList<>();
        while (rs.next()) {
            Object obj = cb.newInstance();
            ResultSetMetaData metaData = rs.getMetaData();
            // 获取字段数目
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                // 获取字段名
                String columnName = metaData.getColumnName(i);
                Method method;
                // fixme 没有做出严谨的判断， 当前只是根据是否有相应的set方法，来判断是否可以为该属性(columnName)赋值;
                // fixme 同时, 也不支持自动驼峰映射, 此处只是简单的进行了一一对应的处理
                if ((method = setMethods.get(columnName)) == null) {
                    throw new MappedException("no such set name for field: " + columnName);
                }
                method.invoke(obj, rs.getObject(i));
            }
            results.add(obj);
        }
        return (List<T>) results;
    }
}
