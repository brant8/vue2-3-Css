package com.example.mybatis_config.test;

import com.example.mybatis_config.dao.UserMapper;
import com.example.mybatis_config.domain.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    //分页插件
    @Test
    public void test3() throws IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //设置分页相关参数,当前页+煤业显示的条数
        PageHelper.startPage(6,3);
        List<User> all = mapper.findAll();
        for(User user : all) {
            System.out.println(user);
        }
        //获得与分页相关的参数，如总页数，总结果条数，当前页面
        PageInfo<User> pageInfo = new PageInfo<User>(all);
        System.out.println("当前页："+pageInfo.getPageNum());
        System.out.println("每页显示条数："+pageInfo.getPageSize());
        System.out.println("总条数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        System.out.println("上一页："+pageInfo.getPrePage());
        System.out.println("下一页："+pageInfo.getNextPage());
        System.out.println("是否第一页："+pageInfo.isIsFirstPage());
        System.out.println("是否最后一页："+pageInfo.isIsLastPage());

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test2() throws IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //执行保存操作
        User user = mapper.findById(8);
        System.out.println(user);
        System.out.println(user.getBirthday());

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test1() throws IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //创建User
        User user = new User();
        user.setUsername("ceshi");
        user.setPassword("abc");
        user.setBirthday(new Date());//此处是时间戳格式
        //数据库birthday是设置为bigint格式，无法存入时间格式，需要自定义转换器

        //执行保存操作
        mapper.save(user);

        sqlSession.commit();
        sqlSession.close();
    }
}
