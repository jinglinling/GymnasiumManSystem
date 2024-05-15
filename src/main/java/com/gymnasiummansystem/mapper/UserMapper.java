package com.gymnasiummansystem.mapper;

import com.gymnasiummansystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> listUsers();
    void register(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
    Boolean login(@Param("userName") String username, @Param("password") String password);
    User findByUserName(String username);
    User findById(Integer id);
}
