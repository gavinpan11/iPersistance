package com.gavin11;

import com.gavin11.ipersistance.session.SqlSession;
import com.gavin11.ipersistance.session.SqlSessionFactory;
import com.gavin11.ipersistance.session.SqlSessionFactoryBuilder;
import com.gavin11.ipersistance.test.mapper.UserMapper;
import com.gavin11.ipersistance.test.pojo.User;

import java.util.List;

/**
 * Hello world!
 *
 */
public class TestDemo
{
    public static void main( String[] args ) throws Exception
    {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // --------------- 传统方式开始 ---------------- //
        statementIdFindByIdName(sqlSession);
//        statementIdFindAll(sqlSession);
//        statementIdInsert(sqlSession);
//        statementIdUpdateById(sqlSession);
//        statementIdDeleteById(sqlSession);
        // --------------- 传统方式结束 ---------------- //

        // --------------- 动态代理方式开始 ---------------- //
//        findByIdName(sqlSession);
//        simpleFindByIdName(sqlSession);
//        insertOne(sqlSession);
//        updateById(sqlSession);
//        deleteById(sqlSession);
        // --------------- 动态代理方式结束 ---------------- //
    }

    /**
     * 动态代理方式删除
     */
    private static void deleteById(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int i = userMapper.deleteById(new User(4, "gavin", "skdjflsikd", "2020-01-09"));
        System.out.println(i);
    }

    /**
     * 动态代理方式修改
     */
    private static void updateById(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int i = userMapper.updateById(new User(4, "gavin", "skdjflsikd", "2020-01-09"));
        System.out.println(i);
    }

    /**
     * 动态代理方式新增
     */
    private static void insertOne(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int i = userMapper.insertOne(new User(4, "hhh", "skdjflsikd", "2020-01-09"));
        System.out.println(i);
    }

    /**
     * 动态代理方式查询单条
     */
    private static void simpleFindByIdName(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User byIdName = userMapper.simpleFindByIdName("lucy", 1);
        System.out.println(byIdName);
    }

    /**
     * 动态代理方式查询单条
     */
    private static void findByIdName(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
        User byIdName = userMapper.findByIdName(user);
        System.out.println(byIdName);
    }

    /**
     * 传统方式删除
     */
    private static void statementIdDeleteById(SqlSession sqlSession) throws Exception {
        User user = new User();
        user.setId(6);
        sqlSession.delete("com.gavin11.ipersistance.test.mapper.UserMapper.deleteById", user);
    }

    /**
     * 传统方式修改
     */
    private static void statementIdUpdateById(SqlSession sqlSession) throws Exception {
        User user = new User(6, "gavin0101", "123123skdf", "1991-01-01");
        sqlSession.update("com.gavin11.ipersistance.test.mapper.UserMapper.updateById", user);
    }

    /**
     * 传统方式新增
     */
    private static void statementIdInsert(SqlSession sqlSession) throws Exception {
        User user = new User(6, "vencent", "123123skdf", "1991-01-01");
        sqlSession.insert("com.gavin11.ipersistance.test.mapper.UserMapper.insertOne", user);
    }

    /**
     * 传统方式查询多条
     */
    private static void statementIdFindAll(SqlSession sqlSession) throws Exception {
        List<User> users = sqlSession.selectList("com.gavin11.ipersistance.test.mapper.UserMapper.findAll");
        System.out.println(users);
    }

    /**
     * 传统方式查询一条
     */
    private static void statementIdFindByIdName(SqlSession sqlSession) throws Exception {
        User user = new User();
        user.setUsername("lucy");
        user.setId(1);
        User o = sqlSession.selectOne("com.gavin11.ipersistance.test.mapper.UserMapper.findByIdName", user);
        System.out.println(o);
    }
}
