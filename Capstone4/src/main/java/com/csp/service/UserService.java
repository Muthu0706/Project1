package com.csp.service;

import com.csp.bean.User;
import com.csp.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean saveOrUpdateUser(User user) {
        userDao.save(user);
        return true;
    }

    public boolean deleteUserById(Long userId) {
        userDao.deleteById(userId);
        return true;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}