/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo_library.controller;

import com.example.demo_library.entities.Author;
import com.example.demo_library.service.AuthorService;
import com.example.demo_library.service.Impl.AuthorServiceImpl;
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
public class AuthorController {
    
    @Autowired
    private AuthorService authorService = new AuthorServiceImpl();
    
    @RequestMapping(value = "/authors/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Author get(@PathVariable("id") int id){
        return authorService.getAuthor(id);
    }
    
    @RequestMapping(value = "/authors",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Author post(@RequestBody Author author){
        return authorService.addAuthor(author);
    }
    
    @RequestMapping(value = "/authors",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Author patch(@RequestBody Author author){
        return authorService.updateAuthor(author);
    }
    
    @RequestMapping(value = "/authors/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public void delete(@PathVariable("id") int id){
        authorService.deleteAuthor(id);
    }
    
    @RequestMapping(value = "/authors",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Author> getAll(){
        return authorService.getAll();
    }
    
}
