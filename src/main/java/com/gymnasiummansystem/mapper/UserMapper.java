package com.gymnasiummansystem.mapper;

import com.gymnasiummansystem.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> listUsers();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
}
