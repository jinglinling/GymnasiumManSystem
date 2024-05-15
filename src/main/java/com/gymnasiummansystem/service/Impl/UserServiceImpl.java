package com.gymnasiummansystem.service.Impl;


import com.github.pagehelper.PageHelper;
import com.gymnasiummansystem.entity.User;
import com.gymnasiummansystem.mapper.UserMapper;
import com.gymnasiummansystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.listUsers();
        return users;
    }

    @Transactional
    @Override
    public void register(User user) {
        userMapper.register(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public Boolean login(String username, String password) {
        return userMapper.login(username,password);
    }

    @Override
    @Transactional
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
