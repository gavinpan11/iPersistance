package com.gavin11.ipersistance.session;

import java.util.List;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.session
 * @description SqlSession <br>
 * @date 2021/4/3 10:39 <br>
 */
public interface SqlSession {

    /**
     * 根据条件查询一条记录
     * @param statementId 唯一标识, 据此可获取对应的查询语句
     * @param params 查询参数
     * @return T
     */
    <T> T selectOne(String statementId, Object... params) throws Exception;

    /**
     * 根据条件查询所有
     * @param statementId 唯一标识, 据此可获取对应的查询语句
     * @param params 查询参数
     * @return list
     */
    <T> List<T> selectList(String statementId, Object... params) throws Exception;

    /**
     * 新增操作
     * @param statementId 唯一标识, 据此可获取对应的查询语句
     * @param params 查询参数
     * @return 受影响的行
     * @throws Exception e
     */
    int insert(String statementId, Object... params) throws Exception;

    /**
     * 修改操作
     * @param statementId 唯一标识, 据此可获取对应的查询语句
     * @param params 查询参数
     * @return 受影响的行
     * @throws Exception e
     */
    int update(String statementId, Object... params) throws Exception;

    /**
     * 删除操作
     * @param statementId 唯一标识, 据此可获取对应的查询语句
     * @param params 查询参数
     * @return 受影响的行
     * @throws Exception e
     */
    int delete(String statementId, Object... params) throws Exception;

    /**
     * 获取持久层对象(使用代理生成)
     * @param clazz clazz
     * @param <T> 类型
     * @return 持久层对象
     */
    <T> T getMapper(Class<T> clazz);
}
