package com.gymnasiummansystem.controller;

import com.gymnasiummansystem.entity.User;
import com.gymnasiummansystem.service.UserService;
import jakarta.annotation.Resource;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        String userName = user.getUserName();
        String password = user.getPassword();
        Boolean login = userService.login(userName, password);
        if (login) { //登陆成功
            String token = userName;
            return ResponseEntity.ok(token);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login false");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User existingUser = userService.findByUserName(user.getUserName());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed, the user already exists");
        }else {
            try {
                userService.register(user);
                return ResponseEntity.ok("Registration success");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
            }
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> listUsers(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize", defaultValue = "2")int pageSize){
        List<User> users = userService.listUsers(pageNum, pageSize);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return ResponseEntity.ok("Update success");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("id")Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Delete success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed");
        }
    }

    @PutMapping("/updateRole")
    public ResponseEntity<?> updateRole(@RequestParam("id")Integer id) {
        User existingUser = userService.findById(id);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        String role = existingUser.getRole();
        if ("用户".equals(role)) {
            existingUser.setRole("管理员");
        } else if ("管理员".equals(role)) {
            existingUser.setRole("用户");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update role failed");
        }
        try {
            userService.updateUser(existingUser);
            return ResponseEntity.ok("Update role success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update role failed");
        }
    }
}
