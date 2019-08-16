/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.Publisher;
import com.example.library.service.PublisherService;
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
 * @author vuduchiep
 */

@RestController
@RequestMapping("/api/v1")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;
    
    @RequestMapping(value = "/publishers/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Publisher get(@PathVariable("id") int id){
        
        return publisherService.get(id);
    }
    
    @RequestMapping(value = "/publishers",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Publisher post(@RequestBody Publisher publisher){
        
        return publisherService.add(publisher);
    }
    
    @RequestMapping(value = "/publishers",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Publisher patch(@RequestBody Publisher publisher){
        
        return publisherService.update(publisher);
    }
    
    @RequestMapping(value = "/publishers/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Publisher delete(@PathVariable("id") int id){
        
        return publisherService.delete(id);
//        return "Delete Successfully";
    }
    
    @RequestMapping(value = "/publishers",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Publisher> getAll(){
        
        return publisherService.getAll();
    }
    
    @RequestMapping(value = "/publishers/search",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Publisher> search(@RequestBody Map<String, Object> data){
        
        String publisherName = data.get("publisherName").toString();
        return publisherService.search(publisherName);
    }
}
