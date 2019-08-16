/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.UserDAO;
import com.example.library.entities.User;
import com.example.library.service.UserService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuduchiep
 */

@Service
@Transactional
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserDAO userDAO;
    
    
    @Override
    public User get(int id) {

        return userDAO.getUser(id);
    }

    @Override
    public User add(User user) {

        return userDAO.addUser(user);
    }

    @Override
    public User update(User user) {

        return userDAO.updateUser(user);
    }

    @Override
    public User delete(int id) {

        return userDAO.deleteUser(id);
    }

    @Override
    public List<User> getAll() {

        return userDAO.getAll();
    }
    
    @Override
    public User loadUserByUsername(String username){
        return userDAO.loadUserByUsername(username);
    }
    
    @Override
    public boolean checkLogin(User user){
        return userDAO.checkLogin(user);
    }

}
