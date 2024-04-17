package com.gymnasiummansystem;

import com.gymnasiummansystem.entity.User;
import com.gymnasiummansystem.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GymnasiumManSystemApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void usersTest(){
        List<User> users = userService.users();
        for (User user : users){
            System.out.println(user);
        }
    }
}
