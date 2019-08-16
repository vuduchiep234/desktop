/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.dao;

import com.example.library.entities.User;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface UserDAO {
    
    public User getUser(int id);
    
    public User addUser(User user);
    
    public User updateUser(User user);
    
    public User deleteUser(int id);
    
    public List<User> getAll();
    
    public boolean checkLogin(User user);
    
    public User loadUserByUsername(String username);
    
    public User loadUserByEmail(String email);
}
