package com.example.DemoWeb112233.service.impl;

import com.example.DemoWeb112233.dao.UserDao;
import com.example.DemoWeb112233.dao.impl.UserDaoImpl;
import com.example.DemoWeb112233.domain.User;
import com.example.DemoWeb112233.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean regist(User user) {
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if(u != null){
            //用户名存在，注册失败
            return false;
        }
        //保存用户信息
        userDao.save(u);
        return true;
    }
}
