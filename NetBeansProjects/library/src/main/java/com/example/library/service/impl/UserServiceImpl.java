/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service.impl;

import com.example.library.dao.UserDAO;
import com.example.library.dao.impl.UserDAOImpl;
import com.example.library.entities.User;
import com.example.library.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vuduchiep
 */

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO = new UserDAOImpl();
    
    @Override
    public User get(int id) {
        return userDAO.get(id);
    }

    @Override
    public User addUser(User user) {

        return userDAO.addUser(user);
    }

    @Override
    public User updateUser(User user) {

        return userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {

        userDAO.deleteUser(id);
    }

    @Override
    public List<User> getAll() {

        return userDAO.getAll();
    }
    
}
