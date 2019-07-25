/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.Publisher;
import com.example.library.service.PublisherService;
import com.example.library.service.impl.PublisherServiceImpl;
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
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService = new PublisherServiceImpl();
    
    // Post
    @RequestMapping(value = "/publishers",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Publisher add(@RequestBody Publisher publisher){
        return publisherService.add(publisher);
    }
    
    // Patch
    @RequestMapping(value = "/publishers",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Publisher update(@RequestBody Publisher publisher){
        return publisherService.update(publisher);
    }
    
    // Delete
    @RequestMapping(value = "/publishers/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public String delete(@PathVariable("id") int id){
        publisherService.delete(id);
        return "Delete Successfully";
    }
    
    // Get
    @RequestMapping(value = "/publishers/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Publisher get(@PathVariable("id") int id){
        Publisher publisher = publisherService.get(id);
        return publisher;
    }
    
    // Get All
    @RequestMapping(value = "/publishers",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Publisher> getAll(){
        List<Publisher> list = publisherService.getAll();
        return list;
    }
}
