package com.example.mybatis.test;

import com.example.mybatis.dao.UserMapper;
import com.example.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//测试类
public class MyBatisTest {

    @Test
    public void test7() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //模拟ids数据
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        //相当于传入id=1 和id=2的搜索结果
        List<User> userList = mapper.findByIds(ids);
        System.out.println(userList);
    }

    @Test
    public void test6() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //模拟条件user
        User condition = new User();
        condition.setId(1);
        condition.setUsername("zhangsan");
        //condition.setPassword("123");

        List<User> userList = mapper.findByCondition(condition);
        System.out.println(userList);
    }

    @Test
    public void test2() throws IOException {
        //模拟User对象
        User user = new User();
        user.setUsername("tom");
        user.setPassword("123");

        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作
        int insert = sqlSession.insert("userMapper.save", user);
        //mybatis执行更新操作->提交事务，数据才会持久化到数据库
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Test
    public void test1() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数：UserMapper.xml里的namespace.id
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        //打印数据
        System.out.println(userList);
        //释放资源
        sqlSession.close();
    }

    //修改操作
    @Test
    public void test3() throws IOException {
        User user = new User();
        user.setUsername("tom");
        user.setPassword("321");
        user.setId(7);

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        System.out.println(resourceAsStream);
        System.out.println(resourceAsStream.toString());
        //测试输出sqlmapconfig
//            StringBuilder sb = new StringBuilder();
//            for (int ch; (ch = resourceAsStream.read()) != -1; ) {
//                sb.append((char) ch);
//            }
//            System.out.println(sb.toString());
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        sqlSession.update("userMapper.update",user);
        sqlSession.commit();
        sqlSession.close();
    }

    //测试删除操作
    @Test
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("userMapper.delete",7);
        sqlSession.commit();
        //sqlSession.rollback();
        sqlSession.close();
    }

    //查询单个对象
    @Test
    public void test5() throws IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();
        User user = sqlSession.selectOne("userMapper.selectById", 3);
        System.out.println(user);
        sqlSession.close();
    }
}
