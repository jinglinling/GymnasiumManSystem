package com.gymnasiummansystem.service;



import com.gymnasiummansystem.entity.User;

import java.util.List;

public interface UserService {
    List<User> listUsers(int pageNum, int pageSize);
    void register(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
    Boolean login(String username, String password);
    User findByUserName(String username);
}
