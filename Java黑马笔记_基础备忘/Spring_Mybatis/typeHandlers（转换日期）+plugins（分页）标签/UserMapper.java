package com.example.mybatis_config.dao;

import com.example.mybatis_config.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserMapper {

    public void save(User user);

    public User findById(int i);
    public List<User> findAll();

}
