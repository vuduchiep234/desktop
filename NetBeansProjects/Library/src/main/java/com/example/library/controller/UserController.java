/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.User;
import com.example.library.service.JwtService;
import com.example.library.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
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

    public static String token = "";
    
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;
    
    
    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public User post(@RequestBody User user){
        user.setRole_id(2);
        return userService.add(user);
    }
    @RequestMapping(value = "/users",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public User patch(@RequestBody User user){
        return userService.update(user);
    }
    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public User delete(@PathVariable("id") int id){
        return userService.delete(id);
    }
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

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, @RequestBody User user) {
        String result = "";
        HttpStatus httpStatus = null;
        
        try {
            if (userService.checkLogin(user)) {
                
                result = jwtService.generateTokenLogin(user.getUsername());
                httpStatus = HttpStatus.OK;
            } else {
                result = "Wrong username and password";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            result = "Serve error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        UserController.token = result;
        
        Map<String, Object> d = new HashMap<>();
        
        User u = loadUserByUsername(user.getUsername());
        
        d.put("user", u);
        d.put("token", result);
        
        
        return d;
    }
    
    @RequestMapping(value = "/users/loadUser",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public User loadUserByUsername(String username){
        return userService.loadUserByUsername(username);
    }
}
