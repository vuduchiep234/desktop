/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.Author;
import com.example.library.service.AuthorService;
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
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/authors/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Author get(@PathVariable("id") int id) {
        System.out.println("get Author");
        return authorService.get(id);
    }

    @RequestMapping(value = "/authors",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_UTF8_VALUE

            })

    @ResponseBody
    public Author post(@RequestBody Author author) {

        return authorService.add(author);
    }

    @RequestMapping(value = "/authors",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Author patch(@RequestBody Author author) {

        return authorService.update(author);
    }

    @RequestMapping(value = "/authors/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public Author delete(@PathVariable("id") int id) {

        return authorService.delete(id);
//        return "Delete successfully";
    }

    @RequestMapping(value = "/authors",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })

    @ResponseBody
    public List<Author> getAll() {

        return authorService.getAll();
    }
    
    @RequestMapping(value = "/authors/search",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Author> search(@RequestBody Map<String, Object> data){
        String name = data.get("name").toString();
        return authorService.search(name);
    }
}
