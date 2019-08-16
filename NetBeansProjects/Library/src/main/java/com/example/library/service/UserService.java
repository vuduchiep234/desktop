/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.service;

import com.example.library.entities.User;
import java.util.List;

/**
 *
 * @author vuduchiep
 */
public interface UserService {
    
    public User get(int id);
    
    public User add(User user);
    
    public User update(User user);
    
    public User delete(int id);
    
    public List<User> getAll();
    
    public boolean checkLogin(User user);
    
    public User loadUserByUsername(String username);
    
}
