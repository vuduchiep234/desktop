/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.library.controller;

import com.example.library.entities.Genre;
import com.example.library.service.GenreService;
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
 * @genre vuduchiep
 */

@RestController
@RequestMapping("/api/v1")
public class GenreController {
    
    @Autowired
    private GenreService genreService;
    
    @RequestMapping(value = "/genres/{id}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Genre get(@PathVariable("id") int id){
        
        return genreService.get(id);
    }
    
    @RequestMapping(value = "/genres",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Genre post(@RequestBody Genre genre){
        
        return genreService.add(genre);
    }
    
    @RequestMapping(value = "/genres",
            method = RequestMethod.PATCH,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Genre patch(@RequestBody Genre genre){
        
        return genreService.update(genre);
    }
    
    @RequestMapping(value = "/genres/{id}",
            method = RequestMethod.DELETE,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public Genre delete(@PathVariable("id") int id){
        
        return genreService.delete(id);
//        return "Delete successfully";
    }
    
    @RequestMapping(value = "/genres",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Genre> getAll(){
        
        return genreService.getAll();
    }
    
    @RequestMapping(value = "/genres/search",
            method = RequestMethod.POST,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            })
    
    @ResponseBody
    public List<Genre> search(@RequestBody Map<String, Object> data){
        
        String genreType = data.get("genreType").toString();
        return genreService.search(genreType);
    }
}
