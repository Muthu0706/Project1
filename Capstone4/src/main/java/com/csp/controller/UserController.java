package com.csp.controller;

import com.csp.bean.User;
import com.csp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/insertUsers")
    public void performInsert(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    @PutMapping("/updateUsers")
    public void performUpdate(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    @DeleteMapping("/deleteUsers/{userId}")
    public void performDelete(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping("/findAllUsers")
    public List<User> viewAllUsers() {
        return userService.getAllUsers();
    }
}