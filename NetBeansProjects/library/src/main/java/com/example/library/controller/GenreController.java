/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.Genre;
import com.example.library.service.GenreService;
import com.example.library.service.impl.GenreServiceImpl;
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
public class GenreController {
    
    @Autowired
    private GenreService genreService = new GenreServiceImpl();
    
    // Post
    @RequestMapping(value = "/genres",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Genre add(@RequestBody Genre genre){
        return genreService.add(genre);
    }
    
    // Patch
    @RequestMapping(value = "/genres",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Genre update(@RequestBody Genre genre){
        return genreService.update(genre);
    }
    
    // Delete
    @RequestMapping(value  = "/genres/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public String delete(@PathVariable("id") int id){
        genreService.delete(id);
        return "Delete Successfully";
    }
    
    // Get
    @RequestMapping(value = "/genres/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Genre get(@PathVariable("id") int id){
        Genre genre = genreService.get(id);
        return genre;
    }
    
    // Get All
    @RequestMapping(value = "/genres",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Genre> getAll(){
        List<Genre> list =  genreService.getAll();
        return list;
    }
}
