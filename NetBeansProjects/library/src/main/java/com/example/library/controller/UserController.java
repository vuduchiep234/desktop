/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.User;
import com.example.library.service.UserService;
import com.example.library.service.impl.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vuduchiep
 */

@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    @Autowired
    private UserService userService = new UserServiceImpl();
    
    // Get
    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public User get(@PathVariable("id") int id){
        return userService.get(id);
    }
    
     // Post
    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public User add(@RequestBody User user){
        return userService.addUser(user);
    }
    
    // Patch
    @RequestMapping(value = "/users",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public User update(@RequestBody User user){
        return userService.updateUser(user);
    }
    
    // Delete
    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public String delete(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "Delete Successfully";
    }
    
    // Get All
    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<User> getAll(){
        return userService.getAll();
    }
}
