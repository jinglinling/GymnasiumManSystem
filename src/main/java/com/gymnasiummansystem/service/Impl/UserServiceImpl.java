package com.gymnasiummansystem.service.Impl;


import com.gymnasiummansystem.entity.User;
import com.gymnasiummansystem.mapper.UserMapper;
import com.gymnasiummansystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> users() {
        return userMapper.users();
    }
}
