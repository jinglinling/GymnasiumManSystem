package com.gymnasiummansystem;

import com.gymnasiummansystem.entity.User;
import com.gymnasiummansystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTests {
    @Autowired
    private UserService userService;

    @Test
    public void listUsersTest(){
        List<User> users = userService.listUsers(1,2);
        for (User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void addUserTest(){
        User user = new User(null,"李四","1234567890",'男',"用户","lisi","11451411","110@qq.com","12123");
        userService.register(user);
    }

    @Test
    public void updateUserTest(){
        User user = new User(4,"王五","",null,"","wangwu","","119@qq.com","13132");
        userService.updateUser(user);
    }

    @Test
    public void deleteUserTest(){
        userService.deleteUser(5);
    }
}
