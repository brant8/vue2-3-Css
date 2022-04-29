package com.example.DemoWeb112233.dao;

import com.example.DemoWeb112233.domain.User;

public interface UserDao {

        public User findByUsername(String username);

        public void save(User user);
}
