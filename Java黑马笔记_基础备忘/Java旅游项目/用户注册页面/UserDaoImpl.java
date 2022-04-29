package com.example.DemoWeb112233.dao.impl;

import com.example.DemoWeb112233.dao.UserDao;
import com.example.DemoWeb112233.domain.User;
import com.example.DemoWeb112233.util.JdbcUtilsAdvanced;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JdbcUtilsAdvanced.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from user where user = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
            //e.printStackTrace();
        }

        return user;
    }

    @Override
    public void save(User user) {
        //1.定义sql
        //用户注册提交的信息不完全等于数据库字段，比如注册页面的验证码，数据库的status状态等
        String sql = "insert into user(username,password,name,birthday,sex,telephone,email)" + " values(?,?,?,?,?,?,?)";
        //2.执行sql
        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),
                user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail());
    }
}
