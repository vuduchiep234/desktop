/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.Role;
import com.example.library.service.RoleService;
import java.util.List;
import java.util.Map;
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
 * @role vuduchiep
 */
@RestController
@RequestMapping("/api/v1")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/roles/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Role get(@PathVariable("id") int id) {
        
        return roleService.get(id);
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_UTF8_VALUE

            })

    @ResponseBody
    public Role post(@RequestBody Role role) {

        return roleService.add(role);
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Role patch(@RequestBody Role role) {

        return roleService.update(role);
    }

    @RequestMapping(value = "/roles/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Role delete(@PathVariable("id") int id) {

        return roleService.delete(id);
//        return "Delete successfully";
    }

    @RequestMapping(value = "/roles",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public List<Role> getAll() {

        return roleService.getAll();
    }
    
    @RequestMapping(value = "/roles/search",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Role> search(@RequestBody Map<String, Object> data){
        String roleType = data.get("roleType").toString();
        return roleService.search(roleType);
    }
}
