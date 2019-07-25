/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.Role;
import com.example.library.service.RoleService;
import com.example.library.service.impl.RoleServiceImpl;
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
public class RoleController {
    
    @Autowired 
    private RoleService roleService = new RoleServiceImpl();
    
    // Get
    @RequestMapping(value = "/roles/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Role get(@PathVariable("id") int id){
        Role role = roleService.getRole(id);
        return role;
    }
    
    // Post
    @RequestMapping(value = "/roles",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Role add(@RequestBody Role role){
       
        return roleService.addRole(role);
    }
    
    // Patch
    @RequestMapping(value = "/roles",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Role update(@RequestBody Role role){
        return roleService.updateRole(role);
    }
    
    // Delete
    @RequestMapping(value = "/roles/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public String delete(@PathVariable("id") int id){
        roleService.deleteRole(id);
        return "Delete Successfully";
    }
    
    // Get All
    @RequestMapping(value = "/roles",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Role> getAll(){
        List<Role> list = roleService.getAll();
        return list;
    }
}
