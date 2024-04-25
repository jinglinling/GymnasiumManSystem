package com.gymnasiummansystem.service;



import com.gymnasiummansystem.entity.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
}
