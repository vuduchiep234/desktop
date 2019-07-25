/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.service;

import com.example.test.dao.UserDAO;
import com.example.test.entities.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

/**
 *
 * @author vuduchiep
 */
@Service
@Transactional
public class UserService {

    private static List<User> listUser = new ArrayList<User>();
    
    @Autowired
    private UserDAO userDAO;

    public List<User> findAll() {
        return listUser;
    }

    public User findById(int id) {
        for (User user : listUser) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public boolean add(User user) {
        for (User userExist : listUser) {
            if (user.getId() == userExist.getId() || StringUtils.equals(user.getUsername(), userExist.getUsername())) {
                return false;
            }
        }
        listUser.add(user);
        return true;
    }

    public void delete(int id) {
        listUser.removeIf(user -> user.getId() == id);
    }

    public User loadUserByUsername(String username) {
        return userDAO.loadUserByUsername(username);
    }

    public boolean checkLogin(User account) {
        return userDAO.checkLogin(account);
    }

}
